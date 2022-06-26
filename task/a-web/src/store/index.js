// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
/* eslint-disable no-new */
import actions from './actions'
import getters from './getters'
import mutations from './mutations'
import storeData from './modules/storeData'
export default new Vuex.Store({
  getters,
  actions,
  mutations,
  modules:{
    storeData
  }
  
  })