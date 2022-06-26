<template>
  <div style="height: 100%">
    <el-container>
      <el-header>
        <div style="float: left; margin-top: 10px; padding: 0px">
          <el-button
            class="buttonCss"
            v-if="isCollapse"
            @click="changeCollapse"
            icon="el-icon-s-unfold"
          ></el-button
          >>
          <el-button
            class="buttonCss"
            v-if="!isCollapse"
            @click="changeCollapse"
            icon="el-icon-s-fold"
          ></el-button
          >>
        </div>
       <!-- <div style="float: right;margin-right: 50px;margin-top: 15px">
          <el-avatar  :size="30" :src="require('D://avatar//'+avatar+'.png')"></el-avatar>
        </div>-->
      </el-header>
      <el-container>
        <el-menu
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :collapse="isCollapse"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :default-active="$route.path"
          router
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-user-solid"></i>
              <span slot="title">我的任务</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/TaskMain/assignment">创建任务</el-menu-item>
              <el-menu-item index="/TaskMain/taskList">个人信息</el-menu-item>
            </el-menu-item-group>
            <el-menu-item index="/TaskMain/taskprogress">任务进度</el-menu-item>
            <el-menu-item index="/TaskMain/assignmanage">任务管理</el-menu-item>
          </el-submenu>
          <el-menu-item index="/TaskMain/taskListcopy">
            <i class="el-icon-menu"></i>
            <span slot="title" >导航二</span>
          </el-menu-item>
          <el-menu-item index="/TaskMain/taskListcopycopy">
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="/TaskMain/taskListcopycopycopy">
            <i class="el-icon-setting"></i>
            <span slot="title">系统设置</span>
          </el-menu-item>
        </el-menu>

        <el-main
          v-loading="loadingChange"
          element-loading-text="正在提交中...请稍等..."
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgb(214, 215, 216)"
        >
          <router-view
        /></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
    import {decrypt,encrypt} from '@/common/crypto';
export default {
  name: "TaskMain",
  data() {
    return {
      loading: this.$store.state.storeData.loading,
      isCollapse: true,
      avatar:"03b0d39583f48206768a7534e55bcpng",
      aes:encrypt("123456"),
      aes2:decrypt("0FA00516C7FA7350DED84B110C438F04")
    };
  },
  created() {
    console.log("aes加密",this.aes)
    console.log("aes解密",this.aes2)
  },
  computed: {
    loadingChange() {
      return this.$store.state.storeData.loading;
    },
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    changeCollapse() {
      if (this.isCollapse) {
        this.isCollapse = false;
      } else {
        this.isCollapse = true;
      }
    },
  },
};
</script>
<style lang="less" scoped>
.el-header {
  background-color: rgb(62, 67, 70);
}
.el-aside {
  background-color: rgb(58, 61, 63);
  height: 100vh;
}
.el-main {
  background-color: rgb(214, 215, 216);
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  height: 100vh;
}
.el-menu-vertical-demo {
  width: 100px;
  min-height: 100vh;
}
.buttonCss {
  background: rgb(62, 67, 70);
  border-color: rgb(62, 67, 70);
}
/deep/.el-button :hover {
  color: rgb(207, 163, 42);
}
/deep/.el-button i {
  color: rgb(207, 163, 42);
}

/deep/.el-loading-spinner .el-loading-text {
  color: rgb(207, 163, 42);
  margin: 3px 0;
  font-size: 19px;
}

/deep/.el-loading-spinner i,
.el-loading-spinner {
  color: rgb(207, 163, 42);
  font-size: 30px;
}
</style>
