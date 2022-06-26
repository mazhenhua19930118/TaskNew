<template>
  <div>

  </div>
</template>
<script>
export default {
  name: "assignment",
  data() {
    return {
      hostNames :{
        "bdgj.cpic.com.cn":"prod",
        "bdgj-sit.cpic.com.cn":"sit",
        "bdgj-dev.cpic.com.cn":"dev",
        "localhost":"local",
        "localhost:8080":"local"
      }
    }
  },
  methods: {
    // 前端判断生产环境还是测试环境的方法
    check(){
      var host =location.href.match(/(http[s]?\:\/\/)(.*?)\//);
      host = host ? host[2]:"";
      return this.hostNames[host] || "sit";
    }
  }

};


</script>
