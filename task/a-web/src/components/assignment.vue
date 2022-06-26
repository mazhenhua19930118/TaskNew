<template>
  <div>
    <el-row :gutter="20" style="margin-top: 10px">
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">游戏名称:</span
        ><el-input
          class="rt-input"
          size="mini"
          v-model="taskName"
          placeholder="请输入名称"
          :clearable="true"
        ></el-input>
      </el-col>
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">开始时间:</span>
        <el-date-picker
          class="rt-date-picker"
          size="mini"
          v-model="startTime"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-col>
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">结束时间:</span>
        <el-date-picker
          class="rt-date-picker"
          size="mini"
          v-model="endTime"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-col>
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">接单人:</span>
        <el-select
          class="rt-select"
          v-model="userName"
          clearable
          placeholder="请选择"
          size="mini"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col
        :span="4"
        style="padding-left: 0px; padding-right: 0px; margin-left: -5px"
      >
        <span class="rt-span">任务类型:</span>
        <el-select
          class="rt-select"
          v-model="taskKind"
          clearable
          placeholder="请选择"
          size="mini"
        >
          <el-option
            v-for="item in tasks"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top: 30px">
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">初始段位:</span>
        <el-select
          class="rt-select"
          v-model="startRank"
          clearable
          placeholder="请选择"
          size="mini"
        >
          <el-option
            v-for="item in kinds"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">预期段位:</span>
        <el-select
          class="rt-select"
          v-model="endRank"
          clearable
          placeholder="请选择"
          size="mini"
        >
          <el-option
            v-for="item in kinds"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
        <span class="rt-span">结算金额:</span
        ><el-input
          class="rt-input"
          size="mini"
          v-model="money"
          placeholder="请输入金额"
          :clearable="true"
          @change="changeMoney"
          @keyup.native="reg"
          maxlength="10"
        ></el-input>
      </el-col>
       <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
         <el-radio v-model="radio" label="1" @change="changeRadio" style="margin-right:5px"><span  class="rt-span" >电信区</span></el-radio>
          <el-radio v-model="radio" label="2" @change="changeRadio" style="margin-right:5px"><span  class="rt-span">网通区</span></el-radio>
           <el-radio v-model="radio" label="3" @change="changeRadio"><span  class="rt-span">其他区</span></el-radio>
      </el-col>
       <el-col :span="4" style="padding-left: 0px; padding-right: 0px;margin-left:-5px;margin-top:-5px ">
         <span class="rt-span">游戏大区:</span>
        <el-select
          class="rt-select"
          v-model="place"
          clearable
          placeholder="请选择"
          size="mini"
        >
          <el-option
            v-for="item in places"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row  :gutter="20" style="margin-top: 30px">
      <el-col :span="20" style="padding-left: 0px; padding-right: 0px;margin-left:90px">
        <el-button type="primary" @click="create">开始创建</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: "assignment",
  data() {
    return {
      radio:"1",
      taskName: "",
      startTime: "",
      endTime: "",
      userName: "",
      taskKind: "",
      startRank: "",
      endRank: "",
      place:"",
      money: "",
       places: [
        {
          value: "1",
          label: "艾欧尼亚",
        },
        {
          value: "2",
          label: "祖安",
        },
        {
          value: "3",
          label: "诺克萨斯",
        },
        {
          value: "4",
          label: "班德尔城",
        },
        {
          value: "5",
          label: "皮尔特沃夫",
        },
         {
          value: "6",
          label: "战争学院",
        },
         {
          value: "7",
          label: "巨神峰",
        },
         {
          value: "8",
          label: "雷瑟守备",
        },
         {
          value: "9",
          label: "裁决之地",
        },
         {
          value: "10",
          label: "黑色玫瑰",
        },
         {
          value: "11",
          label: "暗影岛",
        },
         {
          value: "12",
          label: "钢铁烈阳",
        },
         {
          value: "13",
          label: "水晶之痕",
        },
         {
          value: "14",
          label: "均衡教派",
        },
         {
          value: "15",
          label: "影流",
        },
         {
          value: "16",
          label: "守望之海",
        },
         {
          value: "17",
          label: "征服之海",
        },
         {
          value: "18",
          label: "卡拉曼达",
        },
         {
          value: "19",
          label: "皮城警备",
        },
      ],
      options: [
        {
          value: "1",
          label: "周聪",
        },
        {
          value: "2",
          label: "周耀武",
        },
        {
          value: "3",
          label: "曾蓝",
        },
        {
          value: "4",
          label: "刘勇",
        },
        {
          value: "5",
          label: "李自立",
        },
      ],
      tasks: [
        {
          value: "1",
          label: "英雄联盟",
        },
      ],
      kinds: [
        {
          value: "1",
          label: "黑铁",
        },
        {
          value: "2",
          label: "白银",
        },
        {
          value: "3",
          label: "黄金",
        },
        {
          value: "4",
          label: "白金",
        },
        {
          value: "5",
          label: "钻石",
        },
        {
          value: "6",
          label: "超凡大师",
        },
        {
          value: "7",
          label: "傲视宗师",
        },
        {
          value: "8",
          label: "最强王者",
        },
      ],
    };
  },
  methods: {
    reg(){
        var reg = /([\u4E00-\u9FA5]|[A-Za-z])+/;//只要包含中文或者字母就提示
        if(reg.test(this.money)){
            this.money=""     
}
    },
    changeRadio(){
      if(this.radio==2){
            this.places=[
        {
          value: "1",
          label: "比尔吉沃特",
        },
        {
          value: "2",
          label: "德玛西亚",
        },
        {
          value: "3",
          label: "弗雷尔卓德",
        },
         {
          value: "4",
          label: "无畏先锋",
        },
         {
          value: "5",
          label: "弗雷尔卓德",
        }
      ]
      }
      if(this.radio==1){
          this.places=[
        {
          value: "1",
          label: "艾欧尼亚",
        },
        {
          value: "2",
          label: "祖安",
        },
        {
          value: "3",
          label: "诺克萨斯",
        },
        {
          value: "4",
          label: "班德尔城",
        },
        {
          value: "5",
          label: "皮尔特沃夫",
        },
         {
          value: "6",
          label: "战争学院",
        },
         {
          value: "7",
          label: "巨神峰",
        },
         {
          value: "8",
          label: "雷瑟守备",
        },
         {
          value: "9",
          label: "裁决之地",
        },
         {
          value: "10",
          label: "黑色玫瑰",
        },
         {
          value: "11",
          label: "暗影岛",
        },
         {
          value: "12",
          label: "钢铁烈阳",
        },
         {
          value: "13",
          label: "水晶之痕",
        },
         {
          value: "14",
          label: "均衡教派",
        },
         {
          value: "15",
          label: "影流",
        },
         {
          value: "16",
          label: "守望之海",
        },
         {
          value: "17",
          label: "征服之海",
        },
         {
          value: "18",
          label: "卡拉曼达",
        },
         {
          value: "19",
          label: "皮城警备",
        }
      ]
      }
      if(this.radio==3){
             this.places=[
        {
          value: "1",
          label: "男爵领域",
        },
        {
          value: "2",
          label: "教育网",
        },
      ]
      }
    },
    changeMoney() {
      this.money = this.money.replace(/[^\d.]/g, ""); 
      this.money = this.money.replace(/\.{2,}/g, ".");
      this.money = this.money.replace(/[\.]$/, '');
     this.money = this.money.replace(/(.*)\.([\d]{2})(\d*)/g,'$1.$2');
     this.money = Number(this.money).toFixed(2);
   
     if(this.money=="0.00"){
       this.money=""
     }
    },
    create() {
      let date1 = new Date(this.startTime);
      let date2 = new Date(this.endTime);
      if (this.taskName == "") {
        this.$message({
          showClose: true,
          message: "任务名称不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (this.startTime == "" && this.endTime == "") {
        this.$message({
          showClose: true,
          message: "日期不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (this.userName == "") {
        this.$message({
          showClose: true,
          message: "接单人不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (this.taskKind == "") {
        this.$message({
          showClose: true,
          message: "任务类型不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (this.startRank == "" && this.endRank == "") {
        this.$message({
          showClose: true,
          message: "段位不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (this.money == "") {
        this.$message({
          showClose: true,
          message: "结算金额不可为空",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      if (date1 - date2 > 0) {
        this.$message({
          showClose: true,
          message: "结束日期必须大于开始日期",
          type: "warning",
          duration: 2000,
        });
        return;
      }
      const formData = {
        taskName: this.taskName,
        startTime: this.startTime,
        endTime: this.endTime,
        userName: this.userName,
        taskKind: this.taskKind,
        startRank: this.startRank,
        endRank: this.endRank,
        money: this.money,
      };
      var that = this;
      this.$axios({
        url: "/task/add",
        method: "post",
        data: { ...formData },
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      })
        .then(function (res) {
          if (res.data.code == 200) {
                that.taskName="";
                that.startTime="";
                that.endTime="";
                that.userName="";
                that.taskKind="";
                that.startRank="";
                that.endRank="";
                that.money="";
            that.$store.commit("setValue", true);
            if (that.$store.state.storeData.loading) {
              setTimeout(() => {
                that.$store.commit("setValue", false);
                that.$message({
                  showClose: true,
                  message: res.data.message,
                  type: "success",
                  duration: 1000,
                });
              }, 2000);
            }
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
.rt-input {
  width: 150px;
  margin-left: 5px;
}
.rt-date-picker {
  width: 150px;
}
.rt-select {
  width: 150px;
}
/deep/ .el-input__inner,
.el-input__inner:focus {
  border-color: rgb(207, 163, 42);
}
/deep/ .el-select .el-input__inner:focus {
  border-color: rgb(207, 163, 42);
}
/deep/ .el-select .el-input.is-focus .el-input__inner {
  border-color: rgb(207, 163, 42);
}
/deep/ .el-button--primary {
  width: 100px;
  height: 28px;
  color: rgb(68, 65, 65);
  background-color: rgb(240, 188, 46);
  border-color: rgb(255, 208, 75);
  line-height: 2px;
  font-size: 13px;
}

.rt-span {
  font-size: 13px;
}

/deep/ .el-radio__input.is-checked+.el-radio__label {
    color: rgb(226, 169, 13)
}
/deep/.el-radio__input.is-checked .el-radio__inner {
    border-color: rgb(226, 169, 13);
    background: rgb(226, 169, 13)
}

/deep/.el-radio__inner:hover {
    border-color: rgb(226, 169, 13);
   
}
</style>