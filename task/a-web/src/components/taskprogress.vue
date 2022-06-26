<template>
  <div>
   <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane label="所有任务" name="first">
       <el-table
    :data="tableData"
    max-height="600px"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址">
    </el-table-column>
  </el-table>
    </el-tab-pane>
    <el-tab-pane label="未完成任务" name="second">
       <el-table
    :data="tableData1"
    empty-text = "暂无数据"
    max-height="600px"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址">
    </el-table-column>
  </el-table>
    </el-tab-pane>
    <el-tab-pane label="已完成任务" name="third">
       <el-table
    :data="tableData1"
    empty-text = "暂无数据"
    max-height="600px"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址">
    </el-table-column>
  </el-table>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>
<script>
export default {
  name: "taskprogress",
  data() {
    return {
      tableData1:[],
      activeName: "",
        tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, 
         {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
         {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
         {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
         {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },{
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-07',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }]
    };
  },
  mounted(){
   
  },
  methods: {
    handleClick(){

    }
  },
};
</script>
<style lang="less" scoped>

/deep/.el-tabs__item.is-active{
    color: rgb(207, 163, 42);
}
/deep/.el-tabs__item:hover {
  color: rgb(207, 163, 42);
      cursor: pointer;
}
//表格高亮颜色选择
/deep/ .el-table tbody tr:hover>td {
  background-color:rgb(190, 192, 182) !important;
  color: rgb(104, 104, 100)
}
/deep/.el-table {
 
 background-color:  rgb(214, 215, 216);
}
/deep/.el-table th{
 
 background-color:  rgb(214, 215, 216);
}
/deep/.el-table tr{
 
 background-color:  rgb(214, 215, 216);
}
/deep/ .el-table td{

 background-color:  rgb(214, 215, 216);
}

</style>