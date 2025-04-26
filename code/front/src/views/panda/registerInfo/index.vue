<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="就诊人" prop="petName">
        <el-autocomplete    :trigger-on-focus="false"
                            placeholder="键入开始搜索"
                            v-model="queryParams.petName"
                            @select="handleSelect"
                            :fetch-suggestions="querySearchAsync">
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="就诊人Id" prop="petId">
        <el-input v-model="queryParams.petId" placeholder="请输入就诊人Id"

                  clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="科室名" prop="deptName">
        <el-input v-model="queryParams.deptName" placeholder="请输入科室名" clearable size="small" @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="医生名" prop="docName">
        <el-autocomplete    :trigger-on-focus="false"    placeholder="键入开始搜索"
                            v-model="queryParams.docName" :fetch-suggestions="querySearchDocAsync">
        </el-autocomplete>
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
                   v-hasPermi="['panda:register-info:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   v-hasPermi="['panda:register-info:export']">导出</el-button>
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
      <el-table-column label="就诊人" align="center" prop="petName" />
      <el-table-column label="就诊人Id" align="center" prop="petId" />
      <el-table-column label="科室名" align="center" prop="deptName" />
      <el-table-column label="医生名" align="center" prop="docName" />
      <el-table-column label="应缴金额" align="center" prop="needMoney" />
      <el-table-column label="交款金额" align="center" prop="chargeMoney" />
      <el-table-column label="找零" align="center" prop="changeMoney" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['panda:register-info:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['panda:register-info:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="就诊人" prop="petName">
          <el-autocomplete    :trigger-on-focus="false"
                              placeholder="键入开始搜索"
                              v-model="form.petName"
                              @select="handleSelect"
                              :fetch-suggestions="querySearchAsync">
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="就诊人Id" prop="petId">
          <el-input v-model="form.petId" disabled   placeholder="请输入就诊人Id" />
        </el-form-item>
        <el-form-item label="科室名" prop="deptName">
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
        <el-form-item label="应缴金额" prop="needMoney">
          <el-input v-model="form.needMoney" placeholder="请输入应缴金额" />
        </el-form-item>
        <el-form-item label="交款金额" prop="chargeMoney">
          <el-input v-model="form.chargeMoney" placeholder="请输入交款金额" />
        </el-form-item>
        <el-form-item label="找零" prop="changeMoney">
          <el-input v-model="form.changeMoney" placeholder="请输入找零" />
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
import { createRegisterInfo, updateRegisterInfo, deleteRegisterInfo, getRegisterInfo, getRegisterInfoPage, exportRegisterInfoExcel } from "@/api/panda/registerInfo";
import  {queryPetUser} from '@/api/system/user'
import {getSubDepts} from '@/api/system/dept';
import  {getUsersByDeptName} from  '@/api/functions';
export default {
  name: "RegisterInfo",
  components: {
  },
  data() {
    return {
      // 遮罩层
      depts:[],
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 挂号表列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      getDeptId:undefined,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        petName: null,
        petId: null,
        deptName: null,
        docName: null,
      },
      // 表单参数
      form: {},
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
    changeDept(value){
      var getDept = this.depts.find(each=>{
        return each.name == value;
      })
      this.getDeptId=getDept.id;
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
    handleSelect(){
      this.form.petId=this.form.petName.split("-")[0];
      this.form.petName=this.form.petName.split("-")[1];
    },
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
      getRegisterInfoPage(params).then(response => {
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
        petName: undefined,
        petId: undefined,
        deptName: undefined,
        docName: undefined,
        needMoney: undefined,
        chargeMoney: undefined,
        changeMoney: undefined,
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
      this.title = "添加挂号表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getRegisterInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改挂号表";
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
          updateRegisterInfo(this.form).then(response => {
            this.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createRegisterInfo(this.form).then(response => {
          this.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$confirm('是否确认删除挂号表编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return deleteRegisterInfo(id);
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
      this.$confirm('是否确认导出所有挂号表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRegisterInfoExcel(params);
        }).then(response => {
          this.downloadExcel(response, '挂号表.xls');
        })
    }
  }
};
</script>
