<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="接诊医生" prop="docName">
        <el-autocomplete    :trigger-on-focus="false"    placeholder="键入开始搜索"
                            v-model="queryParams.docName" :fetch-suggestions="querySearchDocAsync">
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="挂号科室" prop="deptName">
        <el-select v-model="queryParams.deptName"  filterable >
          <el-option v-for="item in depts"
                     :key="item.id" :value="item.name"  :label="item.name" >

          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="病人姓名" prop="petName">
        <el-autocomplete    :trigger-on-focus="false"    placeholder="键入开始搜索"   v-model="queryParams.petName" :fetch-suggestions="querySearchAsync">
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="病情描述" prop="descInfo">
        <el-input v-model="queryParams.descInfo" placeholder="请输入病情描述" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
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
                   v-hasPermi="['panda:pat-record:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['panda:pat-record:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="病人姓名" align="center" prop="petName" />
      <el-table-column label="挂号科室" align="center" prop="deptName" />
      <el-table-column label="接诊医生" align="center" prop="docName" />
      <el-table-column label="诊断结果" align="center" prop="result" />
      <el-table-column label="病情描述" align="center" prop="descInfo" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['panda:pat-record:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['panda:pat-record:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="诊断结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入诊断结果" />
        </el-form-item>
        <el-form-item label="病情描述" prop="descInfo">
          <el-input v-model="form.descInfo" placeholder="请输入病情描述" />
        </el-form-item>
        <el-form-item label="接诊医生" prop="docName">
          <el-autocomplete    :trigger-on-focus="false"    placeholder="键入开始搜索"
                              v-model="form.docName" :fetch-suggestions="querySearchDocAsync">
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="挂号科室" prop="deptName">
          <el-select v-model="form.deptName"  filterable >
            <el-option v-for="item in depts"
                     :key="item.id" :value="item.name"  :label="item.name" >

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="病人姓名" prop="petName">
          <el-autocomplete    :trigger-on-focus="false"
                              placeholder="键入开始搜索"
                              v-model="form.petName"
                              @select="handleSelect"
                              :fetch-suggestions="querySearchAsync">
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="病人Id" prop="petId">
          <el-input v-model="form.petId"  disabled  placeholder="病人Id" />
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
import { createPatRecord, updatePatRecord, deletePatRecord, getPatRecord, getPatRecordPage, exportPatRecordExcel } from "@/api/panda/patRecord";
import  {queryPetUser} from '@/api/system/user'
import {getSubDepts} from '@/api/system/dept';
import { mapGetters } from 'vuex'
export default {
  name: "PatRecord",
  components: {
  },
  data() {
    return {
      depts:[],
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 就诊列表
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
        result: null,
        descInfo: null,
        docName: null,
        deptName: null,
        petName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  computed:{
    ...mapGetters(['roles','name'])
  },
  created() {
    this.getList();
    this.initDepts();

    for (let i = 0; i < this.roles.length; i++) {
        var role = this.roles[i];
        if(role == 'Doc'){
            this.queryParams.docName=this.name;
        }
    }
  },
  methods: {
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
    handleSelect(){
      this.form.petId=this.form.petName.split("-")[0];
      this.form.petName=this.form.petName.split("-")[1];
    },
    querySearchDocAsync(queryString,cb){
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
    querySearchAsync(queryString,cb){
      if(queryString){
        queryPetUser({username:queryString,roleCode:'patient'}).then(response=>{
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
              value:item.id+"-"+item.username,
              id:item.id
            })
          }
          cb(userArr);
        })
      }
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getPatRecordPage(params).then(response => {
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
        result: undefined,
        descInfo: undefined,
        docName: undefined,
        deptName: undefined,
        petName: undefined,
        petId: undefined,
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
      this.title = "添加就诊";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getPatRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改就诊";
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
          updatePatRecord(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createPatRecord(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除就诊编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deletePatRecord(id);
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
      this.$confirm('是否确认导出所有就诊数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPatRecordExcel(params);
        }).then(response => {
          this.downloadExcel(response, '就诊.xls');
        })
    }
  }
};
</script>
