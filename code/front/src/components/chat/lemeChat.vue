<template>
  <lemon-imui
    :user="user"
    ref="IMUI"
    width="1200px"
    height="600px"
    :contextmenu="contextmenu"
    :contact-contextmenu="contactContextmenu"
    :theme="theme"
    :hide-menu="hideMenu"
    :hide-menu-avatar="hideMenuAvatar"
    :hide-message-name="hideMessageName"
    :hide-message-time="hideMessageTime"

    @change-contact="handleChangeContact"
    @pull-messages="handlePullMessages"
    @message-click="handleMessageClick"
    @menu-avatar-click="handleMenuAvatarClick"
    @send="handleSend"
  >

  </lemon-imui>
</template>
<script>
  import { uploadFile } from "@/api/system/user";
  import EmojiData from "../../database/emoji";
  import  {createSendMsg,deleteSendMsg,getSendMsgWithEachOtherUnreaded} from  '@/api/panda/sendMsg';
  import  {getSendMsgWithEachOther} from '@/api/panda/sendContact';
  const getTime = () => {
    return new Date().getTime();
  };
  const generateRandId = () => {
    return Math.random()
      .toString(36)
      .substr(-8);
  };
  const generateRandWord = () => {
    return Math.random()
      .toString(36)
      .substr(2);
  };



  let interval=null;


  export default {
    name:'lemoChat',
    props:{
      currentUserInfo:{
        type:Object,
        default:{}
      },
      contactUserList:{
        type:Array,
        default:[]
      },
      contactUsers:{
        type:Array,
        default:[]
      }
    },
    data() {
      return {
        theme: "default",
        contactContextmenu: [

        ],
        contextmenu: [
          {
            visible: instance => {
              return instance.message.type == "text";
            },
            text: "复制文字",
          },

        ],
        hideMenuAvatar: false,
        hideMenu: false,
        hideMessageName: false,
        hideMessageTime: true,
        user:{},
      };
    },
    mounted() {

      const { IMUI } = this.$refs;

      IMUI.initMenus([
        {
          name: "messages",
          click: (next) => {
            if(this.contactUserList.length==0){
              this.$emit("initContacts");
            }
            next();
          }
        },
        {
          name: "contacts",
          click: (next) => {
            if(this.contactUserList.length==0){
              this.$emit("initUserList");
            }
             next();
          }
        },

      ]);

      IMUI.initEditorTools([
        {
          name: "emoji",
        },
        // {
        //   name: "uploadFile",
        // },
        {
          name: "uploadImage",
        },


      ]);
      IMUI.initEmoji(EmojiData);
      const { SimpleIMUI } = this.$refs;
      SimpleIMUI.initEmoji(EmojiData);


    },
    methods: {
      initRecentContactList(data){
        this.$nextTick(()=>{
          const { IMUI } = this.$refs;
          IMUI.initContacts(data);
          if(data && data.length!=0){
            IMUI.changeContact(data[0].id);
          }
        })
      },
      getRecentContactList(data){
        this.$nextTick(()=>{
          const { IMUI } = this.$refs;
          var existContacts=IMUI.contacts;
          var getData=data;

          console.log("existcontacts");
          console.log(existContacts);

          //遍历获取的每个内容 判断在已有的中是否存在，不存在则新增
          getData.forEach(getEach=>{
            var getIndex= existContacts.findIndex(each=>{
              return each.id == getEach.id})
            if(getIndex==-1){
              IMUI.contacts.push(getEach);
            }else{
              // existContacts[getIndex].unread= existContacts[getIndex].unread;
            }

          })



        })
      },
      asyncInitContacts(data){
        const { IMUI } = this.$refs;
        IMUI.initContacts(data);
      },
      changeTheme() {
        this.theme = this.theme == "default" ? "blue" : "default";
      },
      scrollToTop() {
        document.body.scrollIntoView();
      },
      handleMenuAvatarClick() {
        // console.log("Event:menu-avatar-click");
      },
      handleMessageClick(e, key, message, instance) {

        if (key == "status") {
          instance.updateMessage({
            id: message.id,
            status: "going",
            content: "正在重新发送消息...",
          });

        }
      },
      changeMenuAvatarVisible() {
        this.hideMenuAvatar = !this.hideMenuAvatar;
      },
      changeMenuVisible() {
        this.hideMenu = !this.hideMenu;
      },
      changeMessageNameVisible() {
        this.hideMessageName = !this.hideMessageName;
      },
      changeMessageTimeVisible() {
        this.hideMessageTime = !this.hideMessageTime;
      },
      removeMessage() {
        const { IMUI } = this.$refs;
        const messages = IMUI.getCurrentMessages();
        const id = messages[messages.length - 1].id;
        deleteSendMsg(id).then(respones=>{});
        if (messages.length > 0) {
          IMUI.removeMessage(id);
        }
      },
      updateMessage() {
        const { IMUI } = this.$refs;
        const messages = IMUI.getCurrentMessages();
        const message = messages[messages.length - 1];
        if (messages.length > 0) {
          const update = {
            id: message.id,
            status: "succeed",
            type: "file",
            fileName: "被修改成文件了.txt",
            fileSize: "4200000",
          };
          if (message.type == "event") {
            update.fromUser = this.user;
          }
          IMUI.updateMessage(update);
          IMUI.messageViewToBottom();
        }
      },
      appendCustomMessage() {
        const { IMUI } = this.$refs;
        const message = {
          id: generateRandId(),
          status: "succeed",
          type: "voice",
          sendTime: getTime(),
          content: "语音消息",
          params1: "1",
          params2: "2",
          toContactId: "contact-1",
          fromUser: this.user,
        };
        IMUI.appendMessage(message, true);
      },
      appendMessage(message) {
        // const { IMUI } = this.$refs;
        // const contact = IMUI.currentContact;
        //
        // IMUI.appendMessage(message, true);
        this.handleSend(message,next,from)
      },
      updateContact() {

        this.$refs.IMUI.updateContact({
          id: "contact-3",
          unread: 10,
          displayName: generateRandWord(),
          lastSendTime: getTime(),
          lastContent: "修改昵称为随机字母",
        });
      },
      handleChangeContact(contact, instance) {
        console.log("Event:change-contact");

        instance.updateContact({
          id: contact.id,
          unread: contact.unread==null?0: contact.unread,
        });
        // instance.closeDrawer();
      },
      handleSend(message, next, file) {

        if(message.type=='image' ){
          let formData = new FormData();
          formData.append("file", file);
          uploadFile(formData).then(response => {
            message.content=response.msg;
            var sendMsg=JSON.parse(JSON.stringify(message));
            sendMsg.id=undefined;
            createSendMsg(sendMsg).then(response=>{
              sendMsg.id=response.id;
            })
            next();
          });


        }else{
          var sendMsg=JSON.parse(JSON.stringify(message));
          sendMsg.id=undefined;
          createSendMsg(sendMsg).then(response=>{
            sendMsg.id=response.id;
          })
          next();
        }


      },
      handlePullMessages(contact, next, instance) {

        if(instance.contacts.length!=0){
          getSendMsgWithEachOther({toContactId:contact.id,fromUserId:this.user.id}).then(response=>{
            next(response.data, true);
          })
          //如果存在其他定时 则清除
          this.setAutoGetMessage(contact,next);

        }else{
          //获取聊天列表
          if(contact.id){
            this.setAutoGetMessage(contact,next);
          }
          next([], true);
        }
      },
      setAutoGetMessage(contact,next){
        //如果存在其他定时 则清除
        if(interval!=null){
          clearInterval(interval);
        }
        interval = setInterval(()=>{
          // append new data
          this.getSendMsgWithEachOtherUnreaded({toContactId:contact.id,fromUserId:this.user.id},next);
        },2000)
      },
      getSendMsgWithEachOtherUnreaded(param,next){
        //获取当前消息方法
        const { IMUI } = this.$refs;
        var messages = IMUI.getCurrentMessages();
        getSendMsgWithEachOtherUnreaded(param).then(response=>{

          for (let i = 0; i < response.data.length; i++) {
               var message= response.data[i];
                debugger
                var getindex=messages.findIndex(each=> each.id ==message.id)
               if(getindex==-1){
                 IMUI.getCurrentMessages().push(message)
               }

          }

          next([],true);
        })

      },

    },
    created() {
      this.user=Object.assign(this.user,this.currentUserInfo);
    }
  }

</script>
