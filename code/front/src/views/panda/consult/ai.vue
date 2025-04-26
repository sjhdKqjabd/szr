<template>
  <div class="chat-container">
    <!-- 聊天历史区域 -->
    <div class="chat-history" ref="chatHistory">
      <div v-for="(message, index) in chatMessages" :key="index"
           :class="['message', message.type === 'user' ? 'user-message' : 'ai-message']">
        <div class="avatar">
          <el-avatar :size="40" :icon="message.type === 'user' ? 'el-icon-user' : 'el-icon-service'"
                     :class="message.type === 'user' ? 'user-avatar' : 'ai-avatar'"/>
        </div>
        <div class="message-wrapper">
          <div class="message-content">
            {{ message.content }}
          </div>
          <div class="message-time">{{ message.time || getCurrentTime() }}</div>
        </div>
      </div>
    </div>

    <!-- 输入发送区域 -->
    <div class="chat-input-area">
      <el-input
        v-model="userInput"
        type="textarea"
        :rows="3"
        placeholder="请输入您的问题..."
        @keyup.enter.native="sendMessage"
        resize="none"
      />
      <el-button type="primary" @click="sendMessage" :loading="loading" class="send-button">
        <i class="el-icon-s-promotion"></i>
        发送
      </el-button>
    </div>
  </div>
</template>

<script>

import {sendMsgChat} from '@/api/panda/sendContact';





export default {
  name: 'ai',
  data () {
    return {
      chatMessages: [],
      userInput: '',
      loading: false,
      ws:null,
      sendContent:{
        question:null,
        sessionId:null,
        questionId:null
      },
      sessionId:null
    }
  },
  methods: {
    getCurrentTime() {
      const now = new Date();
      return `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`;
    },
    async sendMessage() {
      if (!this.userInput.trim()) return

      // 添加用户消息
      this.chatMessages.push({
        type: 'user',
        content: this.userInput,
        id:new Date().getTime(),
        time: this.getCurrentTime()
      })

      const question = this.userInput
      this.userInput = ''
      this.loading = true

      try {

        this.sendContent.sessionId=this.sessionId
        this.sendContent.question=question
        this.sendContent.questionId=new Date().getTime()


        // 添加思考中的提示消息
        const thinkingMessage = {
          type: 'ai',
          content: '正在思考中...',
          id: this.sendContent.questionId,
          time: this.getCurrentTime(),
          isThinking: true
        }

        this.chatMessages.push(thinkingMessage)



        // 这里需要替换为实际的API调用
        sendMsgChat(this.sendContent)





      } catch (error) {
        console.error('发送消息失败:', error)
        // 可以添加错误提示
      } finally {
        this.loading = false
      }
    },
    handleMessage(response){


      const existingMessageIndex = this.chatMessages.findIndex(msg => msg.id == response.questionId)



      // 滚动到底部显示思考中状态
      this.$nextTick(() => {
        const chatHistory = this.$refs.chatHistory;
        chatHistory.scrollTop = chatHistory.scrollHeight;
      });

      // 获取返回结果后移除思考中的消息
      const lastIndex = this.chatMessages.length - 1
      if (this.chatMessages[lastIndex].isThinking) {
        this.chatMessages.splice(lastIndex, 1)
      }

      //response 返回结构 {questionId:'123',answer:'afasdf'} 根据返回内容 新增 或者 修改 this.chatMessages中的内容，修改是一种追加的方式

      debugger
      if (existingMessageIndex !== -1) {
        // 如果存在相同questionId的消息，追加内容
        this.chatMessages[existingMessageIndex].content += response.answer
      }else{
        // 添加AI回答
        this.chatMessages.push({
          type: 'ai',
          id:response.questionId,
          content: response.answer,
          time: this.getCurrentTime()
        })

      }


      // 滚动到底部
      this.$nextTick(() => {
        const chatHistory = this.$refs.chatHistory;
        chatHistory.scrollTop = chatHistory.scrollHeight;
      });



    }
  },
  created(){

// 创建WebSocket连接
    const ws = new WebSocket('ws://127.0.0.1:52052/ws/chat')

    var sessionId=null;
// 监听WebSocket连接打开
    ws.onopen = (res) => {

      console.log('WebSocket连接已建立')
      // 发送问题到WebSocket服务器

    }

// 监听WebSocket错误
    ws.onerror = (error) => {
      console.error('WebSocket错误:', error)
      throw new Error('WebSocket连接失败')
    }

// 监听WebSocket关闭
    ws.onclose = () => {
      console.log('WebSocket连接已关闭')
    }

    ws.onmessage =(res)=>{
      if(res.data.startsWith("sessionId:")){
        sessionId=res.data.split(":")[1]
        this.sessionId=sessionId
      }else{
        this.handleMessage(JSON.parse(res.data))
      }

    }

  }
}




</script>

<style scoped>
.chat-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  padding: 20px;
  background: #f0f2f5;
}

.chat-history {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background: #fff;
  border-radius: 12px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.message {
  margin-bottom: 30px;
  display: flex;
  align-items: flex-start;
}

.avatar {
  margin: 0 12px;
}

.user-avatar {
  background: #409EFF;
  color: white;
}

.ai-avatar {
  background: #67C23A;
  color: white;
}

.message-wrapper {
  max-width: 70%;
}

.message-content {
  padding: 12px 16px;
  border-radius: 12px;
  word-break: break-word;
  line-height: 1.5;
  position: relative;
}

.message-time {
  font-size: 12px;
  color: #999;
  margin-top: 4px;
  padding: 0 12px;
}

.user-message {
  flex-direction: row-reverse;
}

.user-message .message-content {
  background: #409EFF;
  color: white;
}

.user-message .message-time {
  text-align: right;
}

.ai-message .message-content {
  background: #f4f6f8;
  color: #333;
}

.chat-input-area {
  display: flex;
  gap: 12px;
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.chat-input-area .el-textarea {
  flex: 1;
}

.chat-input-area .el-textarea >>> .el-textarea__inner {
  border-radius: 8px;
  resize: none;
  transition: all 0.3s;
}

.chat-input-area .el-textarea >>> .el-textarea__inner:focus {
  border-color: #409EFF;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

.send-button {
  align-self: flex-end;
  padding: 12px 24px;
  border-radius: 8px;
  transition: all 0.3s;
}

.send-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 2px 12px rgba(64, 158, 255, 0.3);
}

.send-button i {
  margin-right: 6px;
}

/* 滚动条样式 */
.chat-history::-webkit-scrollbar {
  width: 6px;
}

.chat-history::-webkit-scrollbar-thumb {
  background: #ccc;
  border-radius: 3px;
}

.chat-history::-webkit-scrollbar-track {
  background: #f5f5f5;
  border-radius: 3px;
}
</style>
