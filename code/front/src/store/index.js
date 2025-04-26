
import Vue from 'vue';

import Vuex from 'vuex'
//挂在用户函数 登录事件等
import user from './modules/user';
//挂在数据字典
import dict from './modules/dictionary';

import settings  from './modules/settings'
import  getters from './getters'
import tagsView from './modules/tagsView'
import app from './modules/app';
import permission  from './modules/permission'

// vuex install into  vue
Vue.use(Vuex)

const store =new Vuex.Store({
   modules:{
     app,  //使用方法  this.$store.state.app.xxx
     user,
     settings ,//使用 this.$store.state.setting.xxx
     permission,
     tagsView, //打开的页面标签
     dict
   },
   getters,

})


export  default  store; //使用 import store from '@/store' 默认加载index.js函数
