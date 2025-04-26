<template>
  <div :class="classObj" class="app-wrapper" :style="{'--current-color': theme}">
<!--       移动手机-->
    <div  v-if="device==='mobile' &&sidebar.opened "     class="drawer-bg"  @click="handleClickOutside"/>
<!--      侧边栏-->
       <sidebar   class="sidebar-container"
                  :style="{ backgroundColor:sideTheme==='theme-dark'?variables.menuBg:variables.menuLigtBg}">

       </sidebar>
<!--      主容器模块-->
      <div :class="{hasTagsView:needTagsView}" class="main-container"  >
         <div :class="{'fixed-header':fixedHeader}">
           <!--        头部导航-->
           <navbar/>
           <tags-view  v-if="needTagsView"/>
         </div>
<!--        主内容-->
         <app-main/>
<!--        右边panel-->
          <right-panel v-if="showSettings">
             <settings/>
          </right-panel>


      </div>

  </div>
</template>

<!--
   后台首页 模板
   所有组件容器

-->
<script>


  import RightPanel from '@/components/RightPanel'
  import {mapState} from 'vuex';

  import ResizeMixin from './mixin/ResizeHandler';

  import variables from '@/assets/styles/variables.scss'

  import {Navbar,Settings,TagsView,Sidebar,AppMain} from  './components';

  export  default {
     name:'Layout',
     data(){
       return{

       }
     },
     components:{
       RightPanel,
       Navbar,
       Settings,
       TagsView,
       Sidebar,
       AppMain,

     },
     computed:{
       ...mapState({  //计算属性 计算方法  直接从 this.$store.state.settings中获取属性
         theme: state=>state.settings.theme,
         sideTheme:state=>state.settings.sideTheme,
         sidebar:state=>state.app.sidebar,
         device:state=>state.app.device,
         showSettings:state=>state.settings.showSettings,
         needTagsView:state=>state.settings.tagsView,
         fixedHeader:state=>state.settings.fixedHeader
       }),
       //获取 classObject 生成  class="hideSidebar mobile" 等类似class
       classObj() {
         return {
           hideSidebar: !this.sidebar.opened,
           openSidebar: this.sidebar.opened,
           withoutAnimation: this.sidebar.withoutAnimation,
           mobile: this.device === 'mobile'
         }
       },
       //获取 variables变量
       variables(){
         return variables;
       }
     },
     methods:{
       handleClickOutside(){
          //调用 app.js中的 closeSideBar 方法
          this.$store.dispatch("app/closeSideBar",{withoutAnimation:false});  //  组件名称/action名称
       }
     },
     mixins:[ResizeMixin]
  }


</script>

<style lang="scss" scoped>
  @import "~@/assets/styles/mixin.scss";
  @import "~@/assets/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
