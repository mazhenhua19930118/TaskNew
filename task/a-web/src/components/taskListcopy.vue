<template>
  <div>
    <el-card class="box-card">
      待开发
    </el-card>
   <!-- <video :src="videoSrc" :poster="videoImg" :autoplay="playStatus" height="421" width="700" :muted="muteStatus">
      your browser does not support the video tag
    </video>-->
   <!-- <button @click="playClick" :class="{hide: isPlay}">点击播放</button> &ndash;&gt;-->
    <!--class="video-js vjs-default-skin vjs-big-play-centered" -->
    <video   style="margin-top: 20px" :preload="preload"
              :poster="videoImg" :height="height" :width="width" align="center" :controls="controls"  :autoplay="autoplay">
      <source :src="videoSrc" type="video/mp4">
    </video>
  </div>
</template>
<script>
export default {
  name: "taskList",
  data() {
    return {
      videoSrc: 'https://hbq-sit.cpic.com.cn/files/knowImmedOperGuide/hbqzhtqmv.mp4',
      videoImg: 'https://hbq-sit.cpic.com.cn/files/knowImmedOperGuide/hbqzhtqmv_fm.png',
      playStatus: '',
      muteStatus: '',
      isMute: true,
      isPlay: false,
      width: '100%', // 设置视频播放器的显示宽度（以像素为单位）
      height: '500', // 设置视频播放器的显示高度（以像素为单位）
      preload: 'auto', //  建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
      controls: true, // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
      autoplay: '',// 自动播放属性,muted:静音播放
      name:""
    };
  },
  methods: {
    playClick(){
      this.isPlay = true;
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
</style>
