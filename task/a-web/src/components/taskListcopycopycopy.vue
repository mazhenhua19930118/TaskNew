<template>
  <div>
    <el-popover
      placement="top-start"
      width="150"
      :content="input">
      <el-input slot="reference" v-model="input">hover 激活</el-input>
    </el-popover>

  </div>



</template>
<script>
export default {
  name: "taskList",
  data() {
    return {
      name:"",
      input:"质检样式调试"
    };
  },
  methods: {},
};
</script>
<style lang="less" scoped>
.box-card{
  background-color: rgb(214, 215, 216) ;
  color: rgb(104, 104, 100);
}
.rt-span {
  font-size: 14px;
}

</style>
