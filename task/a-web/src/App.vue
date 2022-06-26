<template>
  <div id="app">
    <div
      :style="backgroundDiv"
      v-loading="loginLoading"
      element-loading-text="正在登录中..."
      element-loading-spinner="el-icon-loading"
    >
      <div>
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      //设置背景图片
      backgroundDiv: {
        backgroundImage: "url(" + require("./assets/img.jpg") + ")",
        width: "100%",
        height: "100%",
        backgroundRepeat: "no-repeat",
        backgroundSize: "100% 100%",
      },
    };
  },
  computed: {
    loginLoading() {
      return this.$store.state.storeData.loginLoading;
    },
  },
};
</script>
<!--移动端屏幕显示宽度比设置-->
<!--<!DOCTYPE HTML>
<html>
<head>
  <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scalea1.5" charset="utf-8"/>
  <title></title>
  <link href=" ../../base/css/main.css" rel="stylesheet" type="text/css"/><link href="../ ../base/css/other.css" rel="stylesheet" type="text/css"/>
  <link href-="./css/mobiscroll .custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
</head>
<body>

</body>
</html>-->

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  position: fixed;
  background-color: #909091;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
/*全局修改日期组件颜色*/ /*datePicker组件无法在局部修改样式 需将scope去掉 或者在index主页全局修改*/
.el-date-table td.today span {
  color: rgb(207, 163, 42);
  font-weight: 700;
}
.el-popover{
  max-height: 50px;
  overflow: auto;
}
/*全局修改日期组件颜色*/
.el-picker-panel {
  background-color: rgb(214, 215, 216);
}
/*全局修改日期组件颜色*/
.el-date-table td.current:not(.disabled) span {
  color: #fff;
  background-color: rgb(207, 163, 42);
}

/*全局修改日期组件颜色鼠标选中颜色*/
.el-date-table tbody tr > td :hover span {
  color: #fff;
  background-color: rgb(207, 163, 42);
}
/*全局修改日期组件颜色鼠标选中颜色*/
.el-date-table tbody tr td:hover div {
  color: rgb(207, 163, 42);
}

/*全局修改下拉框选中颜色*/
.el-select-dropdown__item.selected {
  color: rgb(207, 163, 42);
}
/*修改下拉框的背景颜色*/
.el-select-dropdown {
  background-color: rgb(214, 215, 216);
}
/*全局滚动条样式*/
::-webkit-scrollbar {
  width: 8px;
  height: 10px;
}

::-webkit-scrollbar-thumb {
  /*滑块部分*/
  border-radius: 5px;
  background-color: #b6b6b6;
}

::-webkit-scrollbar-track {
  /*轨道部分*/
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 5px;
}
.el-loading-spinner .el-loading-text {
  color: white;
  margin: 3px 0;
  font-size: 20px;
}

.el-loading-spinner i,
.el-loading-spinner {
  color: white;
  font-size: 35px;
}
.el-loading-mask {
  background: transparent;
}
</style>
