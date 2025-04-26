<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="病情" prop="conditionInfo">
        <el-input v-model="queryParams.conditionInfo" placeholder="请输入病情" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="挂号科室" prop="deptName">
        <el-input v-model="queryParams.deptName" placeholder="请输入挂号科室" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['panda:import-condition:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['panda:import-condition:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="病情" align="center" prop="conditionInfo" />
      <el-table-column label="挂号科室" align="center" prop="deptName" />
      <el-table-column label="接诊医生" align="center" prop="docName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['panda:import-condition:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['panda:import-condition:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病情" prop="conditionInfo">
          <el-input v-model="form.conditionInfo" placeholder="请输入病情" />
        </el-form-item>
        <el-form-item label="挂号科室" prop="deptName">
          <el-select v-model="form.deptName"    @change="changeDept"  filterable >
            <el-option v-for="item in depts"
                       :key="item.id" :value="item.name"  :label="item.name" >

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="医生名" prop="docName">
          <el-autocomplete    :trigger-on-focus="true"    placeholder="键入开始搜索"
                              v-model="form.docName" :fetch-suggestions="querySearchDocAsync">
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createImportCondition, updateImportCondition, deleteImportCondition, getImportCondition, getImportConditionPage, exportImportConditionExcel } from "@/api/panda/importCondition";

import {getSubDepts} from '@/api/system/dept';
import {getUsersByDeptName} from '@/api/functions';

export default {
  name: "ImportCondition",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      depts:[],
      // 导医信息列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        conditionInfo: null,
        deptName: null,
      },
      // 表单参数
      form: {},
      getDeptId:undefined,
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.initDepts();
  },
  methods: {
    querySearchDocAsync(queryString,cb){
      if(this.getDeptId && !queryString && !this.form.docName){
        getUsersByDeptName({deptId:this.getDeptId}).then(response=>{
          var userArr=[];

          for (let item of response.data){
            userArr.push({
              value:item.username,
              id:item.id
            })
          }
          cb(userArr);
        })
      }
      if(queryString){
        queryPetUser({username:queryString,roleCode:'Doc'}).then(response=>{
          var  userArr=[];
          var userList=response.data;
          if(userList.length==0){
            cb([]);
          }
          var  result= queryString?userList.filter(state=>{
            if(!!~state.username.indexOf(queryString)){
              return  true;
            }
          }):userList;
          for (let item of result){
            userArr.push({
              value:item.username,
              id:item.id
            })
          }
          cb(userArr);
        })
      }
    },
    initDepts(){
      getSubDepts().then(response=>{
        var getdata=response.data;
        for (var each of getdata){
          this.depts.push({
            id:each.id,
            name:each.name
          })
        }
      })
    },
    changeDept(value){
      var getDept = this.depts.find(each=>{
        return each.name == value;
      })
      this.getDeptId=getDept.id;
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getImportConditionPage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        conditionInfo: undefined,
        deptName: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加导医信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getImportCondition(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改导医信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateImportCondition(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createImportCondition(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除导医信息编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteImportCondition(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$confirm('是否确认导出所有导医信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportImportConditionExcel(params);
        }).then(response => {
          this.downloadExcel(response, '导医信息.xls');
        })
    }
  }
};
</script>
