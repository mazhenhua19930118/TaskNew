<template>
  <div v-if="flag">
    <!--flex弹性盒子模型，justify-content：主抽 -->
    <div style="display: flex; justify-content: center; padding-top: 200px">
      <el-card style="width: 420px; height: 260px; background: transparent">
        <div slot="header" class="clearfix">
          <span style="color: white">登录</span>
        </div>
        <table>
          <tr>
            <td style="color: white">用户名:</td>
            <td>
              <el-input
                class="rt-input"
                v-model="user.username"
                placeholder="请输入用户名"
              ></el-input>
            </td>
          </tr>
          <tr>
            <td style="color: white">&nbsp;&nbsp;&nbsp;密码:</td>
            <td>
              <el-input
                class="rt-input"
                type="password"
                v-model="user.password"
                placeholder="请输入密码"
                @keydown.enter.native="doLogin"
              ></el-input>
              <!-- @keydown.enter.native="doLogin"当按下enter键的时候也会执行doLogin方法-->
            </td>
          </tr>
          <tr v-if="yzFlag">
            <td style="color: white"></td>
            <td>
              <el-input
                class="rt-input-yzm"
                type="password"
                v-model="yzcode"
                placeholder="请输入验证码"
                @keydown.enter.native="doLogin"
              ></el-input>
              <!-- @keydown.enter.native="doLogin"当按下enter键的时候也会执行doLogin方法-->
            </td>
            <td><span class="yzm" @click="createCode"><p style="margin-top: 7px">{{yzcheck}}</p></span></td>
          </tr>
          <tr>
            <!-- 占两行-->
            <td>&nbsp;</td>
            <td colspan="2">
              <!-- 点击事件的两种不同的写法v-on:click和 @click-->
              <!--<el-button style="width: 300px" type="primary" v-on:click="doLogin">登录</el-button>-->
              <el-button
                class="btn"
                style="width: 300px"
                type="primary"
                @click="doLogin"
              >登录
              </el-button
              >
            </td>
          </tr>
        </table>
      </el-card>
    </div>
    <div style="margin-top: 20px;color: #FFFFFF"><a style="cursor:pointer" @click="showReg">点我去注册~</a></div>
    <el-dialog
      title="请注册~"
      :visible.sync="showRegisiter"
      width="800px"
      :modal-append-to-body="false"
    >
      <div>
        <span>用户名：</span><input v-model="regUsername"></input>
      </div>
      <div style="margin-top: 20px;">
        <span>&nbsp;&nbsp;&nbsp;&nbsp;密码：</span><input v-model="regPassworld"></input>
      </div>
      <el-button
        class="btnCss"
        style="width: 100px;margin-top: 30px"
        type="primary"
        @click="doRegister"
      >注册
      </el-button
      >
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "HelloWorld",
    //单页面中不支持前面的data:{}方式
    data() {
      //相当于以前的function data(){},这是es5之前的写法，新版本可以省略掉function
      return {
        //之前是在里面直接写username，password等等，但是这里要写return
        //因为一个组件不管要不要被其他组件用，只要这样定义了，它就会认为可能这个组件会在其他的组件中使用
        //比如说在这里定义了一个变量，然后把这个组件引入到A组件中，A组件中修改了这个变量
        //同时这个组件也在B组件中引用了，这时候A里面一修改，B里面也变了，它们用的是一个值
        //可是一般来说可能希望在不同的组件中引用的时候，使用不同的值，所以这里要用return
        //这样在A组件和B组件分别引用这个变量的时候是不会互相影响的
        user: {
          username: "",
          password: "",
          //为了登录方便，可以直接在这里写好用户名和密码的值
        },
        flag: true,
        counts: 0,
        yzcode: "",
        yzFlag: false,
        yzcheck: "",
        showRegisiter:false,
        regPassworld:'',
        regUsername:''
      };
    },

    methods: {
      showReg(){
        this.showRegisiter = true;
      },
      doRegister(){
        let _this = this;
        if(!_this.regUsername || !_this.regPassworld){
          _this.$message({
            showClose: true,
            message: "请输入用户名或者密码",
            type: "warning",
            duration: 1000,
          });
          return
        }
        let user = {
          name:_this.regUsername,
          password: _this.regPassworld
        }
        this.$axios({
          url: "/user/register",
          method: "post",
          data: user,
          headers: {
            "Content-Type": "application/json;charset=UTF-8",
          },
        }).then(function (res){
          if(res.data.code=='200'){
            _this.$message({
              showClose: true,
              message: "注册成功",
              type: "success",
              duration: 1000,
            });
            _this.showRegisiter = false;
          }else{
            if(res.data.message == '用户已经存在'){
              _this.$message({
                showClose: true,
                message: "用户已经存在",
                type: "warning",
                duration: 1000,
              });
            }else{
              _this.$message({
                showClose: true,
                message: "注册失败",
                type: "error",
                duration: 1000,
              });
              _this.showRegisiter = false;
            }
          }
        }).catch(function (error) {
          _this.$message({
            showClose: true,
            message: error,
            type: "error",
            duration: 1000,
          });
          _this.showRegisiter = true;
        })
      },
      doLogin() {
        let self = this; //为了在内部函数能使用外部函数的this对象，要给它赋值了一个名叫self的变量。
        let result = [];
        if (this.counts >= 3) {
          this.yzFlag = true;
        }
        this.$axios({
          url: "/user/login",
          method: "post",
          data: {name: self.user.username},
          headers: {
            "Content-Type": "application/json;charset=UTF-8",
          },
        })
          .then(function (json) {
            if (!self.user.username || !self.user.password) {
              self.$message({
                message: "请输入用户名或密码",
                type: "warning",
                duration: 1000,
              });
              return;
            }
            if (self.yzFlag && self.yzcode == "") {
              self.$message({
                message: "请输入验证码",
                type: "warning",
                duration: 1000,
              });
              return;
            }
            if (!self.yzFlag) {
              if (json.data.code == "200") {
                result = json.data.data;
                if (
                  result.name == self.user.username &&
                  result.password == self.user.password
                ) {
                  self.flag = false;
                  self.$store.commit("set", true);
                  if (self.$store.state.storeData.loginLoading) {
                    setTimeout(() => {
                      self.$store.commit("set", false);
                      self.$message({
                        showClose: true,
                        message: "登录成功",
                        type: "success",
                        duration: 1000,
                      });
                      self.$router.push({
                        path: "/TaskMain",
                      });
                    }, 2000);
                  }
                  self.flag = false;
                } else {
                  self.$message({
                    message: "用户名或密码错误",
                    type: "warning",
                    duration: 1000,
                  });
                  self.counts++;
                }
              } else {
                self.$message.error({
                  message: "用户名不存在",
                  type: "error",
                  duration: 1000,
                });
              }
            } else {
              if (json.data.code == "200") {
                result = json.data.data;
                if (
                  result.name == self.user.username &&
                  result.password == self.user.password
                  && self.yzcode.toUpperCase() == self.yzcheck) {
                  self.flag = false;
                  self.$store.commit("set", true);
                  if (self.$store.state.storeData.loginLoading) {
                    setTimeout(() => {
                      self.$store.commit("set", false);
                      self.$message({
                        showClose: true,
                        message: "登录成功",
                        type: "success",
                        duration: 1000,
                      });
                      self.$router.push({
                        path: "/TaskMain",
                      });
                    }, 2000);
                  }
                  self.flag = false;
                } else {
                  if (self.yzcheck != self.yzcode.toUpperCase()) {
                    self.$message({
                      message: "请输入正确的验证码",
                      type: "warning",
                      duration: 1000,
                    });
                    self.createCode();
                  } else {
                    self.$message({
                      message: "用户名或密码错误",
                      type: "warning",
                      duration: 1000,
                    });
                  }
                }
              } else {
                self.$message.error({
                  message: "用户名不存在",
                  type: "error",
                  duration: 1000,
                });
              }
            }

          })
          .catch(function (error) {
            console.log(error);
          });
        // //一点击登录按钮，这个方法就会执行
        // alert(JSON.stringify(this.user)); //可以直接把this.user对象传给后端进行校验用户名和密码
      },
      createCode() {
        let code = "";
        let codeLength = 4;//验证码长度
        let random = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        for (let i = 0; i < codeLength; i++) {
          //取得随机数的索引（0~35）
          let index = Math.floor(Math.random() * 36);
          //根据索引取得随机数加到code上
          code += random[index];
        }
        //把code值赋给验证码
        this.yzcheck = code;
      }
    },
    created() {
      this.createCode();
    }

  };
</script>
<style lang="less" scoped>
  .rt-input /deep/ .el-textarea__inner,
  .rt-input /deep/ .el-input__inner {
    background: transparent;
    color: white;
    outline-color: #ff69b4;
  }

  .rt-input /deep/ .el-input__inner:focus {
    border-color: #ff69b4;
  }

  .rt-input-yzm /deep/ .el-textarea__inner,
  .rt-input-yzm /deep/ .el-input__inner {
    background: transparent;
    color: white;
    outline-color: #ff69b4;
    width: 120px;
    margin-right: 180px;
  }

  .rt-input-yzm /deep/ .el-input__inner:focus {
    border-color: #ff69b4;
  }

  .el-button--primary {
    color: #fff;
    background-color: #ff69b4;
    border-color: #ff69b4;
  }

  .yzm {
    display: inline-block;
    margin-left: -200px;
    width: 100px;
    height: 35px;
    color: #fff;
    text-align: center;
  }
  /deep/ .el-dialog{
    background-color: #b3aaaa;
  }

  .btnCss {
    color: #fff;
    background-color: #8e858a;
    border-color: #a19199;
  }
</style>
