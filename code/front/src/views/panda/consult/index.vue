<template>
  <el-row :gutter="12" class="mb8 mt-3" style="margin-top:20px;margin-left: 90px">
      <e-col>
        <lemo-chat  ref="lemoChat"
                    :current-user-info="userInfo"
                    @initUserList="initUserList"
                    @initContacts="initContacts"
                    :contact-users="contactUsers"></lemo-chat>
      </e-col>

  </el-row>
</template>
<script>
  var autoGetContactInterval=null;
  import LemoChat from '../../../components/chat/lemeChat'
  import  {getCurrentOnlineUser,initAllUser} from '@/api/system/user';
  import {getSendContactList} from '@/api/panda/sendContact';
  import { mapGetters } from 'vuex'
  export default {
    name:'consult',
    components: { LemoChat },
    data(){
      return  {
        contactUserList:[],
        contactUsers:[],
      }
    },
    computed:{
      ...mapGetters(['userInfo'])
    },
    created() {

      this.userInfo.displayName=this.userInfo.name;
      this.userInfo.id=this.userInfo.userId;

      this.initUserList();
      this.initContacts();


    },
    methods:{
      getRecentContactList(){
        getSendContactList(this.userInfo).then(response=>{
          this.contactUserList=response.data;
          this.$refs.lemoChat.getRecentContactList(this.contactUserList);
        })
      },
      autoGetContactList(){
        getSendContactList(this.userInfo).then(response=>{
          this.contactUserList=response.data;
          this.$refs.lemoChat.initRecentContactList(this.contactUserList);
        })
      },
      initContacts(){
        getSendContactList(this.userInfo).then(response=>{
          this.contactUserList=response.data;
          this.$refs.lemoChat.initRecentContactList(this.contactUserList);
        })
        if(autoGetContactInterval!=null){
          clearInterval(autoGetContactInterval);
        }
        autoGetContactInterval =setInterval(()=>{
          this.getRecentContactList();
        },5000)

      },
       initUserList(){//初始化 所有 用户列表加载到好友列表中
         initAllUser({}).then(response=>{
             this.contactUsers=response.data;
             this.$refs.lemoChat.asyncInitContacts(this.contactUsers);
         })
       },
    }

  }
</script>
