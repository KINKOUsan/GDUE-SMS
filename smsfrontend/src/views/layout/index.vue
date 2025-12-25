<script setup>

</script>

<template>
  <div class="commen-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">二师学生管理系统 &nbsp; v0.1-mini</span>

      </el-header>

      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px" class="aside">
          <el-menu router>

            <!-- 首页菜单 -->
            <el-menu-item index="/index">
              <el-icon><Promotion /></el-icon> 首页
            </el-menu-item>

            <!-- 班级管理菜单 -->
            <el-sub-menu index="/manage">
              <template #title>
                <el-icon><Menu /></el-icon> 学生与专业管理
              </template>
              <el-menu-item index="/student">
                <el-icon><UserFilled /></el-icon>学生管理
              </el-menu-item>
              <el-menu-item index="/major">
                <el-icon><HomeFilled /></el-icon>专业管理
              </el-menu-item>
            </el-sub-menu>


          </el-menu>
        </el-aside>

        <!-- 主展示区域 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<style scoped>
.header {
  height: 100px;
  background-image: linear-gradient(
      to right,
      #659F31,
      #a8eb12
  );
}

.title {
  display: block;
  margin: 16px auto 0;
  color: white;
  font-size: 40px;
  font-family: "Microsoft YaHei";
  line-height: 60px;
  font-weight: bolder;
}

.right_tool {
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  width: 220px;
  border-right: 1px solid #ccc;
  height: 730px;
}
</style>