<template>
  <div class="app-container">
    <!--     科室数据信息-->
    <el-row :gutter="20">
      <!--       侧边-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input  placeholder="科室名称"  v-model="deptName"  clearable size="small"
                     prefix-icon="el-icon-search" style="margin-bottom: 20px"></el-input>
        </div>
        <div class="head-container">
          <el-tree :data="deptOptions"
                   :props="defaultProps"
                   :expand-on-click-node="false"
                   :filter-node-method="filterNode"
                   ref="tree"
                   default-expand-all
                   @no-click="handleNodeClick"
          >
          </el-tree>
        </div>
      </el-col>
      <!--  搜索框 -->
      <el-col :span="20" :xs="24">
        <el-form  :model="queryParams"  ref="queryForm"  :inline="true"  v-show="showSearch"    label-width="80px">
          <el-form-item label="用户名"  prop="username">
            <el-input  v-model="queryParams.username"
                       placeholder="输入用户名"
                       clearable size="small"
                       style="width:240px"
                       @keyup.enter.native="handlerQuery"
            />
          </el-form-item>
          <el-form-item  label="手机号"  prop="mobile">
            <el-input v-model="queryParams.mobile"  placeholder="手机号"  clearable size="small"
                      style="width:240px"     @keyup.enter.native="handlerQuery" />
          </el-form-item>

          <el-form-item label="状态" prop="status">
            <el-select  v-model="queryParams.status"   placeholder="选择状态" clearable size="small" style="width: 240px" >
              <el-option  v-for="each in statusDictDatas"
                          :key="parseInt(each.value)"
                          :label="each.label" :value="parseInt(each.value)"
              >

              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="创建时间">
            <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                            range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handlerQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <!--       用户数据表-->
        <!--      用户数据表头-->

        <!--       用户表-->
        <el-table v-loading="loading" :data="userList">
          <el-table-column label="用户编号"  align="center"  prop="id"/>
          <el-table-column label="用户名称" align="center" prop="username"/>
          <el-table-column label="用户昵称" align="center" prop="nickname"/>
          <el-table-column label="科室" align="center" prop="dept.name"/>
          <el-table-column label="手机号码" align="center" prop="mobile"/>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.status" :active-value="0"  :inactive-value="1"
                         @change="handleStatusChange(scope.row)"></el-switch>
            </template>
          </el-table-column>

          <el-table-column label="创建时间" align="center" prop="createTime" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" class-name="small-padding fixed-width"   width="160">
            <template slot-scope="scope">
              <el-button size="large" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                         v-hasPermi="['system:role:update']">修改</el-button>
              <el-dropdown @command="(command)=>{handleCommand(command,scope.$index,scope.row)}">
 <span class="el-dropdown-link">
                  更多操作<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="handleDelete" v-if="scope.row.id !== 1" size="mini" type="text" icon="el-icon-delete"
                                    v-hasPermi="['system:user:delete']">删除</el-dropdown-item>
                  <el-dropdown-item command="handleResetPwd" size="mini" type="text" icon="el-icon-key"
                                    v-hasPermi="['system:user:update-password']">重置密码</el-dropdown-item>
                  <el-dropdown-item command="handleRole" size="mini" type="text" icon="el-icon-circle-check"
                                    v-hasPermi="['system:permission:assign-user-role']">分配角色</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>


        </el-table>
        <!--         用户数据表分页信息-->


      </el-col>


    </el-row>

  </div>

</template>
<script>
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  //引入 user.js中  各个方法的request 请求
  import {
    addUser,
    changeUserStatus,
    delUser,
    exportUser,
    getUser,
    importTemplate,
    listUser,
    resetUserPwd,
    updateUser
  } from "@/api/system/user";

  import {getToken} from "@/utils/auth";
  import Treeselect from "@riophae/vue-treeselect";

  import {listSimpleDepts} from "@/api/system/dept";
  import {listSimplePosts} from "@/api/system/post";
  import {listSimpleRoles} from "@/api/system/role";
  import {assignUserRole, listUserRoles} from "@/api/system/permission";

  import {SysCommonStatusEnum} from "@/utils/constants";
  import {DICT_TYPE, getDictDatas} from "@/utils/dict";

  export default {
    name:'User',
    data(){
      return{
        deptName:undefined, //初始化未定义 用于搜索的开始没有定义
        deptOptions:undefined,
        statusDictDatas: getDictDatas(DICT_TYPE.SYS_COMMON_STATUS),
        queryParams:{
          username:undefined,
          mobile:undefined,
          status:undefined,
          pageNo:1,
          pageSize:10,
          deptId:undefined
        },
        userList:null,
        loading:false,
        total:0,
        // 日期范围
        dateRange: [],
        showSearch:true,
        defaultProps:{ //树默认属性值
          children:'children',
          label:'name'
        },
        // 表单校验
        rules: {
          username: [
            { required: true, message: "用户名称不能为空", trigger: "blur" }
          ],
          nickname: [
            { required: true, message: "用户昵称不能为空", trigger: "blur" }
          ],
          password: [
            { required: true, message: "用户密码不能为空", trigger: "blur" }
          ],
          email: [
            {
              type: "email",
              message: "'请输入正确的邮箱地址",
              trigger: ["blur", "change"]
            }
          ],
          mobile: [
            {
              pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
              message: "请输入正确的手机号码",
              trigger: "blur"
            }
          ]
        },
        // 是否显示弹出层（角色权限）
        openRole: false,
      }
    },
    created() {
      //加载时 开始就需要初始化数据的值
      /** 查询科室下拉树结构 + 岗位下拉 */
      this.getTreeselect();
      //加载用户数据表数据
      this.getUserList();

    },
    methods:{
      handleCommand(command,index,row){
        switch (command) {
          case 'handleUpdate':
            this.handleUpdate(row);//修改客户信息
            break;
          case 'handleDelete':
            this.handleDelete(row);//红号变更
            break;
          case 'handleResetPwd':
            this.handleResetPwd(row);
            break;
          case 'handleRole':
            this.handleRole(row);
            break;
          default:
            break;
        }
      },
      handleResetPwd(){

      },
      handleRole(row){

      },
      handleUpdate(row){

      },
      handleStatusChange(row){
        console.log(row);
        var text=row.status===SysCommonStatusEnum.ENABLE?"启用":"停用";
        this.$confirm('确认要"' + text + '""' + row.username + '"用户吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return  changeUserStatus(row.id,row.status);
        }).then(respnose=>{
          this.msgSuccess("操作成功");
        }).catch(error=>{
          this.msgError("操作失败");
          row.status = row.status === SysCommonStatusEnum.ENABLE ? SysCommonStatusEnum.DISABLE
            : SysCommonStatusEnum.ENABLE;
        })

      },
      getUserList(){
        this.loading=true;

        listUser(this.addDateRange(this.queryParams,[
          this.dateRange[0] ? this.dateRange[0] + ' 00:00:00' : undefined,
          this.dateRange[1] ? this.dateRange[1] + ' 23:59:59' : undefined,
        ])).then(response=>{
          this.userList=response.data.list;
          this.total=response.data.total;
          this.loading=false;
        })

      },
      handlerQuery(){
        this.queryParams.pageNo=1;
        this.getUserList();
      },
      resetQuery(){
        //重置输入框
        this.resetForm('queryForm');
        this.dateRange=[];
        this.handlerQuery();
      },

      getTreeselect(){
        //初始化科室书
        listSimpleDepts().then(response=>{
          this.deptOptions=[];
          // 使用  ...调用 hanldTree树方法
          this.deptOptions.push(...this.handleTree(response.data,"id"));
        })
        //初始化 岗位
        listSimplePosts().then(response=>{

        })
      },
      handleNodeClick(){

      },
      filterNode(){

      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          deptId: undefined,
          username: undefined,
          nickname: undefined,
          password: undefined,
          mobile: undefined,
          email: undefined,
          sex: undefined,
          status: "0",
          remark: undefined,
          postIds: [],
          roleIds: []
        };
        this.resetForm("form");
      },
    }
  }
</script>


<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #1890ff;
    margin-left: 5px;
  }
  .el-icon-arrow-down {
    font-size: 14px;
  }
</style>
