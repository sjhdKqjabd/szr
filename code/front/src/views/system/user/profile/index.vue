<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div class="text-center">
<!--               头像信息-->
              <user-avatar :user="user"></user-avatar>
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user" />用户名称
                <div class="pull-right">{{user.username}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone" />手机号码
                <div class="pull-right">{{user.mobile}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="email" />用户邮箱
                <div class="pull-right">{{user.email}}</div>
              </li>
              <li class="list-group-item" v-if="user.depts">
                <svg-icon icon-class="tree" />所属科室
                 <div class="pull-right">{{user.depts.map(dept=>dept.name).join(",")}}</div>

              </li>
<!--              <li class="list-group-item" v-if="user.posts">-->
<!--                <svg-icon icon-class="tree"/>所属岗位-->
<!--                 <div class="pull-right">{{user.posts.map(post=>post.name).join(",")}}</div>-->
<!--              </li>-->
              <li class="list-group-item" v-if="user.roles">
                <svg-icon icon-class="peoples" />所属角色
                 <div class="pull-right">{{user.roles.map(each=>each.name).join(",")}}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date" />创建日期
                <div class="pull-right">{{ parseTime(user.createTime) }}</div>
<!--                parseTime  提前引入  在main.js中 全局引入    -->
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <el-tabs  v-model="activeTab"   >
            <el-tab-pane label="基本资料" name="userinfo">
              <user-info :user="user"></user-info>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="resetPwd">
             <reset-pwd :user="user"></reset-pwd>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

   import {getUserProfile} from '@/api/system/user';

   import UserInfo from './userinfo'
   import resetPwd from './resetPwd'
   import userAvatar from './userAvatar'

   export  default {
      name:'Profile',
     components: { UserInfo ,resetPwd,userAvatar},
     data() {
       return {
         user: {},
         roleGroup: {},
         postGroup: {},
         activeTab: "userinfo"
       };
     },
      created() {
        this.getUserInfo();
      },
      methods:{
        getUserInfo(){
          getUserProfile().then(response=>{
             this.user=response.data;
             console.log(response.data);

          })
        }
      }
   }
</script>

