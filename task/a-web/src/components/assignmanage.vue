<template>
  <div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="所有任务" name="first">
        <el-table
          :data="tableData"
          max-height="600px"
          border
          style="width: 100%"
        >
          <el-table-column
            prop="taskNumber"
            label="任务序列号"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="startTime"
            label="开始日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="endTime"
            label="结束日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="userName"
            label="接单人"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column prop="money" label="结算金额" align="center">
          </el-table-column>
          <el-table-column
            prop="status"
            label="任务状态"
            align="center"
            :formatter="statusFormatter"
          >
          </el-table-column>
          <el-table-column label="操作" width="100" align="center">
            <template slot-scope="scope">
              <el-button
                @click="handleRowClick(scope.row)"
                type="text"
                size="small"
                color="black"
                >{{ scope.row.status == 0 ? "待审核" : "审核完成" }}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="未审核任务" name="second">
        <el-table
          :data="tableData"
          empty-text="暂无数据"
          max-height="600px"
          border
          style="width: 100%"
        >
          <el-table-column
            prop="taskNumber"
            label="任务序列号"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="startTime"
            label="开始日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="endTime"
            label="结束日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="userName"
            label="接单人"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column prop="money" label="结算金额" align="center">
          </el-table-column>
          <el-table-column
            prop="status"
            label="任务状态"
            align="center"
            :formatter="statusFormatter"
          >
          </el-table-column>
          <el-table-column label="操作" width="100" align="center">
            <template slot-scope="scope">
              <el-button
                @click="handleRowClick(scope.row)"
                type="text"
                size="small"
                color="black"
                >{{ scope.row.status == 0 ? "待审核" : "审核完成" }}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="已审核任务" name="third">
        <el-table
          :data="tableData"
          empty-text="暂无数据"
          max-height="600px"
          border
          style="width: 100%"
        >
          <el-table-column
            prop="taskNumber"
            label="任务序列号"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="startTime"
            label="开始日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="endTime"
            label="结束日期"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="userName"
            label="接单人"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column prop="money" label="结算金额" align="center">
          </el-table-column>
          <el-table-column
            prop="status"
            label="任务状态"
            align="center"
            :formatter="statusFormatter"
          >
          </el-table-column>
          <el-table-column label="操作" width="100" align="center">
            <template slot-scope="scope">
              <el-button
                @click="handleRowClick(scope.row)"
                type="text"
                size="small"
                color="black"
                >{{ scope.row.status == 0 ? "待审核" : "审核完成" }}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>

    <el-dialog
      title="任务单"
      :visible.sync="dialogVisible"
      width="800px"
      :modal-append-to-body="false"
    >
      <div><span>系统分析后此单合格结果如下：</span></div>
      <div>
        <table style="margin-left: 100px; margin-top: 30px">
          <tr>
            <td>接单人:</td>
            <td>{{ currentTask.userName }}</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>接单次数：</td>
            <td>188次</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>接单总评：</td>
            <td>4.8分</td>
          </tr>
          <tr>
            <td>本单段位:</td>
            <td>{{ currentTask.startRank }}</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>晋升段位：</td>
            <td>{{ currentTask.endRank }}</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>用时：</td>
            <td>{{ time }}天</td>
          </tr>
          <tr>
            <td>此单金额:</td>
            <td>{{ currentTask.money }}</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>通过率：</td>
            <td>99.3%</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>系统建议：</td>
            <td ><span :style="this.advice=='仔细审核'?'color:red':this.advice=='审核通过'?'color:green':'color:red'">{{advice}}</span></td>
          </tr>
        </table>
        <el-button
          class="btn"
          type="primary"
          @click="pass('1')"
          v-if="this.currentTask.status == 0"
          >审核通过</el-button
        >
        <el-button
          class="btn"
          type="primary"
          @click="pass('0')"
          v-if="this.currentTask.status == 1"
          >驳回</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "assignmanage",
  data() {
    return {
      advice:"",
      currentTask: [],
      tabsName: "first",
      dialogVisible: false,
      activeName: "first",
      time: "",
      tableData: [],
    };
  },
  mounted() {},
  created() {
    this.init("first");
  },
  methods: {
    init(tabs) {
      var that = this;
      this.$axios({
        url: "/task/findAll",
        method: "get",
        params: { status: tabs },
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      })
        .then(function (res) {
          if (res.data.code == 200) {
            that.tableData = res.data.data;
          } else {
            that.tableData = [];
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    statusFormatter(row, column) {
      let status = row.status;
      if (status == "0") {
        return "待审核";
      } else {
        return "已审核";
      }
    },
    handleClick(tab, event) {
      this.init(tab.name);
      this.tabsName = tab.name;
    },
    useTime(startTime, endTime) {
      let date1 = new Date(startTime);
      let date2 = new Date(endTime);
      this.time = (date2.getTime() - date1.getTime()) / 1000 / 24 / 60 / 60+1;
    },
    handleRowClick(row) {
      this.dialogVisible = true;
      this.currentTask = row;
       this.useTime(row.startTime, row.endTime);
       if(row.status==0){
           if(row.money>=500||this.time>=14){
        this.advice="仔细审核"
      }else{
        this.advice="审核通过"
      }
       }else{
             if(row.money>=500||this.time>=14){
        this.advice="存疑"
      }else{
        this.advice="审核通过"
      }
       }
     
     
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    pass(flag) {
      var that = this;
      this.$axios({
        url: "/task/pass",
        method: "post",
        params: { status: flag, taskNumber: this.currentTask.taskNumber },
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      })
        .then(function (res) {
          if (res.data.code == 200) {
            that.$message({
              message: res.data.message,
              type: "success",
              duration: 1000,
            });
            that.dialogVisible = false;
            that.init(that.tabsName);
          } else {
            that.$message({
              message: res.data.message,
              type: "error",
              duration: 1000,
            });
            that.dialogVisible = false;
            that.init(that.tabsName);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
<style lang="less" scoped>
/deep/.el-tabs__item.is-active {
  color: rgb(207, 163, 42);
}
/deep/.el-tabs__item:hover {
  color: rgb(207, 163, 42);
  cursor: pointer;
}
//表格高亮颜色选择
/deep/ .el-table tbody tr:hover > td {
  background-color: rgb(190, 192, 182) !important;
  color: rgb(104, 104, 100);
}
/deep/.el-table {
  background-color: rgb(214, 215, 216);
}
/deep/.el-table th {
  background-color: rgb(214, 215, 216);
}
/deep/.el-table tr {
  background-color: rgb(214, 215, 216);
}
/deep/ .el-table td {
  background-color: rgb(214, 215, 216);
}

/deep/.el-button--text {
  color: #99113e;
  background: 0 0;
  padding-left: 0;
  padding-right: 0;
}
/deep/.el-dialog__header {
  background: rgb(197, 202, 191);
}
/deep/.el-dialog__body {
  background: rgb(197, 202, 191);
  height: 200px;
}
/deep/.el-button--primary {
  width: 100px;
  margin-top: 30px;
  color: rgb(87, 85, 85);
  background-color: rgb(207, 163, 42);
  border-color: rgb(207, 163, 42);
}
/deep/.el-button--primary:hover span {
  color: rgb(199, 202, 163);
}
</style>