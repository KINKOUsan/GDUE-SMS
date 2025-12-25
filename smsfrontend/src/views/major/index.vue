<script setup>
import { ref, watch, onMounted } from "vue";
import {
  queryPageApi,
  addApi,
  queryByIdApi,
  updateApi,
  deleteApi,
} from "@/api/major";
import { ElMessage, ElMessageBox } from "element-plus";
import { EditPen, Delete } from "@element-plus/icons-vue";


onMounted(() => {
  search();
})
/**
 *----------------------查询操作------------------------
 */

const searchMajor = ref({name: ""});// 查询条件
const currentPage = ref(1); //页码
const pageSize = ref(10); //每页展示记录数
const majorList = ref([]);
const total = ref(0); //总记录数


// 查询专业列表
const search = async () => {
  const result = await queryPageApi(
    searchMajor.value.name,
    currentPage.value,
    pageSize.value
  );
  if (result.code) {
    majorList.value = result.data.rows;
    total.value = result.data.total;
  }
}

// 清空
const clear = () => {
  searchMajor.value = {name: ""};
  search();
}


/**
 * -------------------新增/修改触发------------------
 */

// 新增专业
const addMajor = () => {
  dialogTitle.value = "新增专业";
  dialogVisible.value = true;
  majorForm.value = {id: null, name: ""};
  //重置表单的校验规则-提示信息
  if (majorFormRef.value) {
    majorFormRef.value.resetFields();
  }
}

// 编辑专业 - 根据ID查询回显数据
const handleEdit = async (id) => {
  console.log(`Edit item with ID ${id}`);
  dialogTitle.value = "修改专业";
  dialogVisible.value = true;
  majorForm.value = { id: null, name: "" };

  const result = await queryByIdApi(id);
  if (result.code) {
    majorForm.value = result.data;
  }
};

/**
 *---------------------对话框--------------------
 */
const formRules = ref({
  name: [
    { required: true, message: "请输入部门名称", trigger: "change" }
  ],
});

const dialogVisible = ref(false);
const dialogTitle = ref("新增专业");
const majorFormRef = ref();
const majorForm = ref({name: ""});
// 重置表单
const resetForm = () => {
  majorFormRef.value.resetFields();
};

const save = async () => {
  await majorFormRef.value.validate(async (valid) => {
    if (!valid)
      return;

    // 提交表单
    let result = null;
    if (majorForm.value.id) {
      result = await updateApi(majorForm.value); // 修改
    } else {
      result = await addApi(majorForm.value); // 新增
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
      ElMessage.error(result.msg);
    }
  });
};


/**
 *------------------删除触发------------------
 */

const handleDelete = (id) => {
  console.log(`Delete item with ID ${id}`);
  //删除部门时, 需要弹出一个确认框, 如果是确认, 则删除部门
  ElMessageBox.confirm("此操作将永久删除该专业, 是否继续?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(async () => {
    // 删除部门
    const result = await deleteApi(id);
    if (result.code) {
      ElMessage.success("删除成功");
      search();
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
  <h1>专业管理</h1>

<!-- 查询操作与新增按钮 -->
  <div class="container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="专业">
        <el-input v-model="searchMajor.name" placeholder="请输入专业名称" />
      </el-form-item>

      <el-form-item>
        <el-button type="success" @click="search()">查询</el-button>
        <el-button type="info" @click="clear()">清空</el-button>
      </el-form-item>

      <el-button type="success" @click="addMajor()" style="float: right">   + 新增专业
      </el-button>
    </el-form>
  </div>


<!-- 表格 -->
  <div class="container">
    <el-table :data="majorList" stripe border style="width: 100%">
      <el-table-column prop="id" label="ID" width="50" align="center"/>
      <el-table-column prop="name" label="专业名称" />
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
    <el-form :model="majorForm" :rules="formRules" ref="majorFormRef">
      <el-form-item label="专业名称" prop="name" label-width="80px">
        <el-input v-model="majorForm.name" autocomplete="off"></el-input>
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