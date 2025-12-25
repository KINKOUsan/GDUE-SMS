<script setup>
import { ref, watch, onMounted } from "vue";
import {
  queryPageApi,
  addApi,
  queryByIdApi,
  updateApi,
  deleteApi,
} from "@/api/student";
import { queryPageApi as queryMajorApi } from "@/api/major";
import { ElMessage, ElMessageBox } from "element-plus";
import { EditPen, Delete } from "@element-plus/icons-vue";

onMounted(() => {
  loadMajors();
  search();
})

/**
 *----------------------查询操作------------------------
 */

const searchStudent = ref({name: "", majorId: null});// 查询条件
const currentPage = ref(1); //页码
const pageSize = ref(10); //每页展示记录数
const studentList = ref([]);
const total = ref(0); //总记录数
const majorList = ref([]); // 专业列表，用于下拉选择

// 加载专业列表
const loadMajors = async () => {
  try {
    const result = await queryMajorApi("", 1, 100);
    if (result.code) {
      majorList.value = result.data.rows;
    } else {
      ElMessage.error(`加载专业列表失败: ${result.msg || '未知错误'}`);
    }
  } catch (error) {
    ElMessage.error(`加载专业列表失败: ${error.message || '网络错误'}`);
  }
}

// 查询学生列表
const search = async () => {
  try {
    const result = await queryPageApi(
      searchStudent.value.name,
      searchStudent.value.majorId,
      currentPage.value,
      pageSize.value
    );
    if (result.code) {
      studentList.value = result.data.rows;
      total.value = result.data.total;
    } else {
      ElMessage.error(`查询学生列表失败: ${result.msg || '未知错误'}`);
      studentList.value = [];
      total.value = 0;
    }
  } catch (error) {
    ElMessage.error(`查询学生列表失败: ${error.message || '网络错误'}`);
    studentList.value = [];
    total.value = 0;
  }
}

// 清空
const clear = () => {
  searchStudent.value = {name: "", majorId: null};
  search();
}

/**
 * -------------------新增/修改触发------------------
 */

// 新增学生
const addStudent = () => {
  dialogTitle.value = "新增学生";
  dialogVisible.value = true;
  studentForm.value = { 
    id: null, 
    name: "", 
    studentId: "",
    gender: null, 
    age: null, 
    majorId: null, 
    score: null 
  };
  //重置表单的校验规则-提示信息
  if (studentFormRef.value) {
    studentFormRef.value.resetFields();
  }
}

// 编辑学生 - 根据ID查询回显数据
const handleEdit = async (id) => {
  try {
    dialogTitle.value = "修改学生";
    dialogVisible.value = true;
    studentForm.value = { 
      id: null, 
      name: "",
      studentId: "",
      gender: null, 
      age: null, 
      majorId: null, 
      score: null 
    };

    const result = await queryByIdApi(id);
    if (result.code) {
      studentForm.value = result.data;
    } else {
      ElMessage.error(`查询学生详情失败: ${result.msg || '未知错误'}`);
      dialogVisible.value = false;
    }
  } catch (error) {
    ElMessage.error(`查询学生详情失败: ${error.message || '网络错误'}`);
    dialogVisible.value = false;
  }
};

/**
 *---------------------对话框--------------------
 */
const formRules = ref({
  name: [
    { required: true, message: "请输入学生姓名", trigger: "blur" }
  ],
  studentId: [
    { required: true, message: "请输入学号", trigger: "blur" }
  ],
  gender: [
    { required: true, message: "请选择性别", trigger: "change" }
  ],
  age: [
    { required: true, message: "请输入年龄", trigger: "blur" },
    { type: 'number', min: 1, max: 100, message: "年龄必须在1-100之间", trigger: "blur" }
  ],
  majorId: [
    { required: true, message: "请选择专业", trigger: "change" }
  ],
  score: [
    { required: true, message: "请输入分数", trigger: "blur" },
    { type: 'number', min: 0, max: 100, message: "分数必须在0-100之间", trigger: "blur" }
  ],
});

const dialogVisible = ref(false);
const dialogTitle = ref("新增学生");
const studentFormRef = ref();
const studentForm = ref({ 
  name: "",
  studentId: "",
  gender: null, 
  age: null, 
  majorId: null, 
  score: null 
});
// 重置表单
const resetForm = () => {
  studentFormRef.value.resetFields();
};

const save = async () => {
  await studentFormRef.value.validate(async (valid) => {
    if (!valid)
      return;

    // 提交表单
    try {
      let result = null;
      if (studentForm.value.id) {
        result = await updateApi(studentForm.value); // 修改
      } else {
        result = await addApi(studentForm.value); // 新增
      }

      if (result.code) {
        ElMessage.success("操作成功");
        // 关闭对话框
        dialogVisible.value = false;
        // 重置表单
        resetForm();
        // 重新加载数据
        search();
      } else {
        ElMessage.error(result.msg || "操作失败");
      }
    } catch (error) {
      ElMessage.error(`操作失败: ${error.message || '网络错误'}`);
    }
  });
};


/**
 *------------------删除触发------------------
 */

const handleDelete = (id) => {
  //删除学生时, 需要弹出一个确认框, 如果是确认, 则删除学生
  ElMessageBox.confirm("此操作将永久删除该学生, 是否继续?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(async () => {
    // 删除学生
    try {
      const result = await deleteApi(id);
      if (result.code) {
        ElMessage.success("删除成功");
        search();
      } else {
        ElMessage.error(`删除失败: ${result.msg || '未知错误'}`);
      }
    } catch (error) {
      ElMessage.error(`删除失败: ${error.message || '网络错误'}`);
    }
  });
};




/**
 *--------------------分页条部分-----------------------
 */

const background = ref(true); //背景色

//每页展示记录数变化
const handleSizeChange = () => {
  search();
};
//页码变化时触发
const handleCurrentChange = () => {
  search();
};


//========================  Script部分结束 ===========================
</script>



<template>
  <h1>学生管理</h1>

<!-- 查询操作与新增按钮 -->
  <div class="container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="searchStudent.name" placeholder="请输入学生姓名" />
      </el-form-item>

      <el-form-item label="专业">
        <el-select v-model="searchStudent.majorId" placeholder="请选择专业" style="width: 180px;">
          <el-option
            v-for="major in majorList"
            :key="major.id"
            :label="major.name"
            :value="major.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="success" @click="search()">查询</el-button>
        <el-button type="info" @click="clear()">清空</el-button>
      </el-form-item>

      <el-button type="success" @click="addStudent()" style="float: right">   + 新增学生
      </el-button>
    </el-form>
  </div>


<!-- 表格 -->
  <div class="container">
    <el-table :data="studentList" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="50" align="center"/>
      <el-table-column prop="name" label="学生姓名" />
      <el-table-column prop="studentId" label="学号" />
      <el-table-column prop="gender" label="性别" width="80" align="center">
        <template #default="scope">
          {{ scope.row.gender === '男' ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="80" align="center" />
      <el-table-column prop="majorName" label="专业名称" />
      <el-table-column prop="score" label="分数" width="80" align="center" />
      <el-table-column label="操作" width="200" align="center">
        <template #default="scope">
          <el-button type="success" size="small" @click="handleEdit(scope.row.id)">
            <el-icon><EditPen /></el-icon> 编辑
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">
            <el-icon><Delete /></el-icon> 删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>



<!-- 分页条 -->
  <div class="container">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 30, 50, 75, 100]"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>



<!-- 对话框 -->
  <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="30%"
      @close="resetForm"
  >
    <el-form :model="studentForm" :rules="formRules" ref="studentFormRef">
      <el-form-item label="学生姓名" prop="name" label-width="80px">
        <el-input v-model="studentForm.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学号" prop="studentId" label-width="80px">
        <el-input v-model="studentForm.studentId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender" label-width="80px">
        <el-select v-model="studentForm.gender" placeholder="请选择性别" style="width: 100%;">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" prop="age" label-width="80px">
        <el-input v-model.number="studentForm.age" type="number" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="majorId" label-width="80px">
        <el-select v-model="studentForm.majorId" placeholder="请选择专业" style="width: 100%;">
          <el-option
            v-for="major in majorList"
            :key="major.id"
            :label="major.name"
            :value="major.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分数" prop="score" label-width="80px">
        <el-input v-model.number="studentForm.score" type="number" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
    </template>
  </el-dialog>

</template>





<style scoped>
.container {
  margin: 10px 0;
}
</style>