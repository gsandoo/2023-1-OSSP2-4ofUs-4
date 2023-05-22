<template>
  <div>
  <div class="chat-room">
    <h2>Chat History</h2>
    <ul class="message-list">
      <li v-for="message in messages" :key="message.id" class="message">
        <span class="sender">{{ message.sender }}:</span> {{ message.content }}
      </li>
    </ul>

    <div class="input-section">
      <input type="text" v-model="newMessage" placeholder="Enter your message" />
      <a href="#" class="send-link" @click="sendMessage">Send</a>
    </div>
  </div>

  <div class="chat-rooms">
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
</div>

</template>

<script>
import axios from 'axios';
import Stomp from 'stompjs';
// import WebSocket from 'websocket';
import SockJS from 'sockjs-client';

export default {
  data() {
    return {
      messages: [],
      newMessage: '',
      matchingId: 123, // 매칭 ID를 적절하게 설정하세요.
      matchingType: true, // 매칭 유형을 적절하게 설정하세요.
      sender: "ckswls56@dogguk.edu", // 보내는 사람을 적절하게 설정하세요.
      stompClient: null
    };
  },
  mounted() {
    this.connectToWebSocket();
    this.fetchChatHistory();
  },
  beforeUnmounted() {
    this.disconnectFromWebSocket();
  },
  methods: {
    connectToWebSocket() {
      const socket = new SockJS('http://3.37.37.164:8080/ws');
      this.stompClient = Stomp.over(socket);

      socket.onopen = () => {
        this.stompClient.subscribe('/room/receive', (message) => {
          const receivedMessage = JSON.parse(message.body);
          this.messages.push(receivedMessage);
        });
      };
    },
    disconnectFromWebSocket() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.disconnect();
      }
    },
    fetchChatHistory() {
      axios.get(`/room/{matchingId}/{matchingType}`)
        .then(response => {
          this.messages = response.data.messages;
        })
        .catch(error => {
          console.error('Failed to fetch chat history:', error.response);
        });
    },
    sendMessage() {
      if (!this.newMessage || !this.stompClient) {
        return;
      }

      const chatMessage = {
        matchingId: this.matchingId,
        matchingType: this.matchingType,
        sender: this.sender,
        content: this.newMessage
      };

      this.stompClient.send('/room/send', {}, JSON.stringify(chatMessage));
      this.newMessage = '';
    }
  }
};
</script>

<style scoped>
    .chat-room {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }

    .message-list {
      list-style: none;
      padding: 0;
    }

    .message {
      margin-bottom: 10px;
    }

    .sender {
      font-weight: bold;
    }

    .input-section {
      margin-top: 20px;
    }

.chat-rooms {
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
