import Vue from 'vue'

import Cookies from 'js-cookie'
import Element from 'element-ui'
import './assets/styles/element-variables.scss'
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'

import router from './router'
//全局挂在store模块内容 包括登录事件响应等
import store from './store';

import './assets/icons' // icon
import './permission' // permission control

//数据字典加载
import {DICT_TYPE, getDictDataLabel, getDictDatas} from "@/utils/dict";

//权限控制 接口
import permission from './directive/permission'
//注入Vue
Vue.use(permission);


//全局挂在组件内容
import  RightToolbar   from '@/components/RightToolbar';
import  Pagination from "@/components/Pagination";
//在 user.index.vue中使用  获取配置
import { getConfigKey } from "@/api/infra/config";

//引入  共用 函数，
import {
  parseTime,
  resetForm,
  addDateRange,
  addBeginAndEndTime,
  selectDictLabel,
  download,
  handleTree,
  downloadExcel,
  downloadWord,
  downloadZip,
  downloadHtml,
  downloadMarkdown,
} from "@/utils/index";


//全局挂载 方法
Vue.prototype.parseTime=parseTime;
Vue.prototype.handleTree=handleTree;
Vue.prototype.resetForm=resetForm;
Vue.prototype.addDateRange=addDateRange;
Vue.prototype.addBeginAndEndTime=addBeginAndEndTime;
Vue.prototype.downloadExcel=downloadExcel;
Vue.prototype.download=download;
Vue.prototype.selectDictLabel=selectDictLabel;
Vue.prototype.downloadWord=downloadWord;
Vue.prototype.downloadZip=downloadZip;
Vue.prototype.downloadHtml=downloadHtml;
Vue.prototype.downloadMarkdown=downloadMarkdown;


//数据字典 加载
Vue.prototype.getDictDatas = getDictDatas
Vue.prototype.getDictDataLabel = getDictDataLabel
Vue.prototype.DICT_TYPE = DICT_TYPE

Vue.prototype.getConfigKey=getConfigKey;


//全局挂载 方法
Vue.prototype.msgSuccess=function(msg)
{
  this.$message({showClose:true,message:msg,type:'success'});
}
Vue.prototype.msgError=function(msg){
  this.$message({showClose:true,message:msg,type:'error'});
}


import Chat from 'jwchat';
Vue.use(Chat)

import LemonIMUI from 'lemon-imui';
import 'lemon-imui/dist/index.css';
Vue.use(LemonIMUI);

//全局挂在组件
Vue.component("Pagination",Pagination);
Vue.component("RightToolbar",RightToolbar);



/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
