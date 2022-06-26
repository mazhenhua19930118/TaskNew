// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router';//打包部署router放最后CSS样式问题
import less from 'less'
import axios from 'axios';
import store from './store';
/*import crypto from './common';*/
import Video from 'video.js'
import 'video.js/dist/video-js.css'

import {videoPlayer} from 'vue-video-player'
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
Vue.prototype.$axios = axios;
Vue.use(less)
Vue.config.productionTip = false
axios.defaults.baseURL = '/api'
Vue.use(ElementUI);
Vue.use(Vuex);
Vue.use(videoPlayer);


new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  store
})
