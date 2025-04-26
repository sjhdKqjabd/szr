import router from './router'

import  {Message} from 'element-ui'

import store from './store';

import NProgress from 'nprogress'

import {getToken} from '@/utils/auth'

NProgress.configure({showSpinner: false})

const whiteList=["/login","/auth-redirect","/bind","/register"];

//路由监控
router.beforeEach((to,from,next)=>{
    NProgress.start();
    if(getToken()){ //如果已经登录
      if(to.path=='/login'){
         next({path:'/'});
         NProgress.done();
      }else{//如果不是 登录页面地址 直接放行
        //后台首页获取用户信息 并获取用户菜单信息
        if(store.getters.roles.length===0){
          //初始化数据字典信息
          store.dispatch("dict/loadDictDatas");
          //加载用户
          store.dispatch("GetInfo").then(res=>{
            //获取用户角色信息
            const roles=res.roles;
            store.dispatch("GenerateRoutes",{roles}).then(accessRoutes=>{
              // 根据roles权限生成可访问的路由表
              router.addRoutes(accessRoutes) // 动态添加可访问路由表
              next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
            })
          }).catch(error=>{
              store.dispatch("LogOut").then(res=>{
                Message.error(error);
                next({path:'/'});//跳转 /
              })
          });
        }else {
          //放行
          next();
        }
      }

    }else{////如果 尚未登录

       if(whiteList.indexOf(to.path)!==-1){ //如果请求地址在白名单中 直接进入
           next();
       }else{
         // 进入 登录页面
         next(`/login`);
         console.log("getlogin");
         NProgress.done();
       }
    }
})

router.afterEach(()=>{
  NProgress.done();
})



