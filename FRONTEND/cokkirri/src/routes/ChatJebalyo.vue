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
        <div class="input-section">
          <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" class = "messageInput">
          <button @click="sendMessage">Send</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from '../api/index.js';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

export default {
  data() {
    return {
      messages: [],
      newMessage: '',
      matchingId: 1, // 매칭 ID
      matchingType: 'free', // 매칭 유형
      sender: "skxkswls@gmail.com", // 보내는 유저
      stompClient: null
    };
  },
  mounted() {
    this.connectToWebSocket();
  },
  beforeUnmounted() {
    this.disconnectFromWebSocket();
  },
  methods: {
    // 웹소켓 연결을 위한 메소드
    connectToWebSocket() {
      const socket = new SockJS('http://3.37.37.164:8080/ws');
      this.stompClient = Stomp.over(socket);

      socket.onopen = () => {
        this.stompClient.subscribe('/room/1/free', (message) => {
          const receivedMessage = JSON.parse(message.body);
          this.messages.push(receivedMessage);
        });
      };
    },

    // 웹 소켓 종료를 위한 메소드
    disconnectFromWebSocket() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.disconnect();
      }
    },

    sendMessage() {
      //값이 없으면 메시지를 보내지 않음.
      if (!this.newMessage || !this.stompClient) {
        return;
      }

      const chatMessage = {
        matchingId: this.matchingId,
        matchingType: this.matchingType,
        sender: this.sender,
        content: this.newMessage
      };

      //해당 경로로 메시지를 전달함.
      this.stompClient.send('/send/1/free', {}, JSON.stringify(chatMessage));
      // 전달하는 내용은 다음과 같음.
      this.messages.push({
        id: Date.now(), //현재시간 기반
        text: this.newMessage, //메시지 내용
        isSentByMe: true
      });
      this.newMessage = ''; // newMessage 재사용을 위한 초기화
    }
  }
};
</script>

<style scoped>
.wholeBox{background-color: FFFEF9;
  background-color: FFFEF9;
}

.chat-room {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.messages {
  max-height: 400px;
  overflow-y: scroll;
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

.input-section {
  margin-top: 20px;
  display: flex;
}

.input-section input {
  flex: 1;
}

.input-section button {
  margin-left: 10px;
}

.messageInput {
  background-color: FFFEF9;
}
</style>
