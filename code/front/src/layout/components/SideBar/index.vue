<template>
     <div :class="{'has-logo':showLogo}"
          :style="{backgroundColor:settings.sideTheme==='theme-dark'?
             variables.menuBg:variables.menuLightBg }">
       <logo v-if="showLogo"  :collapse="isCollapse"></logo>


       <el-scrollbar :class="settings.sideTheme" wrap-class="scrollbar-wrapper">
            <el-menu
                :default-active="activeMenu"
                :collapse="isCollapse"
                :background-color="settings.sideTheme==='theme-dark'?variables.menuBg:variables.menuLightBg"
                :text-color="settings.sideTheme==='theme-dark'?variables.menuText:'rgba(0,0,0,.65)'"
                :unique-opened="true"
                :active-text-color="settings.theme"
                :collapse-transition="false"
                mode="vertical"
            >
              <side-bar-item
                v-for="(route, index) in sidebarRouters"
                :key="route.path  + index"
                :item="route"
                :base-path="route.path"
              />
            </el-menu>
       </el-scrollbar>
     </div>

</template>


<script>

  import Logo from './Logo';
  import SideBarItem from './SideBarItem';

  import {mapState,mapGetters} from 'vuex';

  import variables from "@/assets/styles/variables.scss";
  import SidebarItem from './SideBarItem'

  export  default {
    name:'SideBar',
    components:{
      SidebarItem,
      Logo,
      SideBarItem
    },
    computed:{
      ...mapGetters(["sidebarRouters", "sidebar"]),
      //获取 getters 中的参数  sidebarRouters  在 menu.js中请求
      //系统加载时 加载permission.js 文件然后 输出 permission
      ...mapState(["settings"]), //获取 this.store settings中的参数
      showLogo(){
        return this.$store.state.settings.sidebarLogo;
      },
      variables(){
        return variables;
      },
      isCollapse(){
        return   !this.sidebar.opened;
      },
      activeMenu(){
        const route = this.$route;
        const { meta, path } = route;
        // if set path, the sidebar will highlight the path you set
        if (meta.activeMenu) {
          return meta.activeMenu;
        }
        return path;
      }
    },
  }
</script>
