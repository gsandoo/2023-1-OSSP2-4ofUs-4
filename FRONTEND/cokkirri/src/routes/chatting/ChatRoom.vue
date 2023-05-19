<template>
  <div class="chat-room">
    <div class="messages">
      <div v-for="message in messages" :key="message.id" class="message">
        <div v-if="message.isSentByMe" class="sent-by-me">
          {{ message.text }}
        </div>
        <div v-else class="received">
          {{ message.text }}
        </div>
      </div>
    </div>
    <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요">
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      messages: [],
      newMessage: '',
      matchingId: 1,
      matchingType: 'free',
      sender: 'ckswls56@dogguk.edu'
    };
  },
  methods: {
    sendMessage() {
      if (this.newMessage.trim() === '') {
        return;
      }
      const message = {
        id: Date.now(),
        text: this.newMessage,
        isSentByMe: true
      };
      this.messages.push(message);
      this.newMessage = '';

      axios.post(`/room/${this.matchingId}/${this.matchingType}`, {
        matchingId: this.matchingId,
        matchingType: this.matchingType,
        sender: this.sender,
        content: message.text
      })
        .then(response => {
          console.log('Message sent:', response.data);
        })
        .catch(error => {
          console.error('Failed to send message:', error);
        });
    }
  }
};
</script>

<style scoped>
.chat-room {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.messages {
  flex: 1;
  overflow-y: scroll;
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}

.message {
  margin-bottom: 10px;
}

.sent-by-me {
  text-align: right;
  background-color: #e2f3ff;
  padding: 5px;
}

.received {
  text-align: left;
  background-color: #f5f5f5;
  padding: 5px;
}
</style>
