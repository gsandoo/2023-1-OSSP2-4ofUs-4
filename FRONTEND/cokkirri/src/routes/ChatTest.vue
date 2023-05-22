<template>
  <div>
    <div class = "wholeBox">
      <div class="chat-room">
        <div class="messages">
          <div v-for="message in messages" :key="message.id" class="message">
            <div :class="{'sent-by-me': message.isSentByMe, 'received': !message.isSentByMe}">
              {{ message.text }}
            </div>
          </div>
        </div>
        <!-- <div class="input-section">
          <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" class = "messageInput">
          <button @click="sendMessage">Send</button>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from '../api/index.js';
export default {
  mounted(){
    this.fetchChatHistory();
  },
  methods:{
      // 예전의 채팅을 불러오는 기능
      fetchChatHistory() {
        axios.get(`/room/{matchingId}/{matchingType}`, {

            // params: {
            //   matchingId: this.matchingId,
            //   matchingType: this.matchingType
            // }
          })
          .then(response => {
            console.log('Chat history response:', response.data);
            this.messages = response.data.messages;
          })
          .catch(error => {
            console.error('Failed to fetch chat history:', error.response);
          });
      }
  }
}
</script>