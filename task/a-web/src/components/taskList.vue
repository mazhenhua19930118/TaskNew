<template>
  <div>
    <el-card class="box-card">
      <div style="padding: 0px">
        <span style="display: inline-block; float: left; margin-top: -10px"
          >个人信息</span
        >
        <el-button
          style="
            float: right;
            padding: 3px 0;
            margin-top: -10px;
            color: rgb(207, 163, 42);
          "
          type="text"
          >修改信息</el-button
        >
      </div>
      <div>
        <el-row :gutter="20" style="margin-top: 30px">
          <el-col :span="4" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: {{ name }}</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">出生年月:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯:</span>
          </el-col>
          <el-col
            :span="5"
            style="padding-left: 0px; padding-right: 0px; margin-left: -5px"
          >
            <span class="rt-span">爱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好:</span>
          </el-col>
        </el-row>
         <el-row :gutter="20" style="margin-top: 30px">
          <el-col :span="4" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">游戏大区: {{ name }}</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">现处段位:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">最高段位:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">擅长位置:</span>
          </el-col>
          <el-col
            :span="5"
            style="padding-left: 0px; padding-right: 0px; margin-left: -5px"
          >
            <span class="rt-span">赛季经历:</span>
          </el-col>
        </el-row>
         <el-row :gutter="20" style="margin-top: 30px">
          <el-col :span="4" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">擅长英雄: {{ name }}</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">出生年月:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">现居城市:</span>
          </el-col>
          <el-col :span="5" style="padding-left: 0px; padding-right: 0px">
            <span class="rt-span">紧急联系人:</span>
          </el-col>
          <el-col
            :span="5"
            style="padding-left: 0px; padding-right: 0px; margin-left: -5px"
          >
            <span class="rt-span">紧急联系号码:</span>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <el-upload
      class="avatar-uploader"
      action=""
      :show-file-list="false"
      :http-request="upload"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      >
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>
<script>
export default {
  name: "taskList",
  data() {
    return {
      name:"",
      imageUrl: ''
    };
  },
  methods: {

   upload(f){
        console.log(f);
      let formdata = new FormData();
      formdata.append('file', f.file)
      this.$axios({
        url: "/upload/uploadFile",
        method: "post",
        data: formdata,
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      })
        .then(function (json) {

        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  },
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

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
