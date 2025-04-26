<template>
  <div class="dashboard-editor-container">

    <el-card         :gutter="20" style="background-color: #4AB7BD" >

      公告信息
    </el-card>
    <el-row   v-loading="loading"     >

      <el-card  v-for="each  in   notices"     :gutter="20"  style="margin-top: 10px"  >
        <div>
          <div>
            公告标题：{{each.title}}
          </div>
          <div>
            内容：
             <span  style="display: inline"  v-html="each.content"></span>
            <el-button   style=" float: right;" type="primary"   @click="handleRead(each.id)"  > 已读</el-button>

          </div>
          <div>

            <span>创建时间： {{parseTime(each.createTime)}}   </span>
          </div>

        </div>
      </el-card>


      <el-card   v-if="notices==undefined"    :gutter="20"  style="margin-top: 10px"  >
        <div>
          <div>
              暂无公告
          </div>

        </div>
      </el-card>


    </el-row>

   </div>
</template>


<script>


  import {listNotice,handleRead}  from  '@/api/system/notice';


  export default {
     name:'Index',
     components:{

     },
    created() {
      this.listNotice();
    },
    data(){
      return {
        loading:true,
        notices:undefined,
        queryParams:{
          pageNo:1,
          pageSize:10,
          isfront:"true",
          status:0
        }
      }
     },
    methods:{
      handleRead(id){
        this.loading=true;
        handleRead(id).then(()=>{
          listNotice(this.queryParams).then(response=>{
            this.notices=response.data.list;
            if(this.notices.length==0){
              this.notices=undefined;
            }
          })
          this.loading=false;
        })

      },
      listNotice(){
        listNotice(this.queryParams).then(response=>{

          this.notices=response.data.list;
          if(this.notices.length==0){
            this.notices=undefined;
          }
        })
        this.loading=false;
      }
    }

  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>
