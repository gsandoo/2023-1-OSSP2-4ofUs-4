<template>
  <div>
      <div class = "WholeBox">
          <div class="chat-room">
              <div class="MessageBox">
                  <div v-for="message in messages" :key="message.id" class="message">
                      <div :class="{'sent-by-me': message.isSentByMe, 'received': !message.isSentByMe}" style>
                          {{ message.text }}
                      </div>
                  </div>
              </div>
              <div class="input-section">
              <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" class = "messageInput">
                  <a @click="sendMessage" class = "sendMessage">
                      <i class="fa-paper-plane-top" style="color: #b87514;"></i>
                  </a>
              </div>
          </div>
      </div>
  </div>
  
</template>

<script>
  import { ref, onMounted } from 'vue';
  import Stomp from 'stompjs';
  import SockJS from 'sockjs-client';
  import axios from '../api/index.js'; // Use axios directly here.

  export default {
  setup() {
      const messages = ref([]);
      const newMessage = ref('');
      const matchingId = ref(1);
      const matchingType = ref('free');
      const sender = ref('skxkswls@gmail.com');
      let stompClient = null;

      const connectToWebSocket = () => {
      const socket = new SockJS('http://3.37.37.164:8080/ws');
      stompClient = Stomp.over(socket);

      socket.onopen = () => {
          stompClient.subscribe(`/room/${matchingId.value}/${matchingType.value}`, (message) => {
          const receivedMessage = JSON.parse(message.body);
          messages.value.push(receivedMessage);

          console.log(receivedMessage); // Added console.log statement
      });
      };
  };

      // 메시지 보내기
      const sendMessage = () => {
          if (!newMessage.value || !stompClient) {
              return;
          }

          const chatMessage = {
              matchingId: parseInt(matchingId.value),
              matchingType: matchingType.value,
              sender: sender.value,
              content: newMessage.value
          };

          stompClient.send('/send/{matchingId}/{matchingType}', {}, JSON.stringify(chatMessage));
          messages.value.push({
              id: Date.now(),
              text: newMessage.value,
              isSentByMe: true
          });
          newMessage.value = '';
      };

      // fetchChatHistory modified here.
      const fetchChatHistory = async () => {
          try {
              const response = await axios.get(`/room/${matchingId.value}/${matchingType.value}`);
              messages.value = response.data;  // 가져온 데이터를 메시지에 직접 할당.
          } catch (error) {
              console.error('Failed to fetch chat history:', error);
          }
      }

  onMounted(() => {
      connectToWebSocket();
          fetchChatHistory().catch(error => {
          console.error('Failed to load chat history:', error);
      });
  });
  
  return {
      messages,
      newMessage,
      matchingId,
      matchingType,
      sender,
      sendMessage
  };
  }
};
</script>

<style scoped>

/* .WholeBox{
  background-color: yellow;
} */

/* .chat-room {
  background-color:brown;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
} */

.chat-room {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.MessageBox { 
  /* background-color:black; */
  max-height: 800px;
  overflow-y: scroll;
  }

.message {
  /* background-color:blue; */
  margin-bottom: 10px;
}

.sent-by-me {
  text-align: right;
  background-color: aqua;
  padding: 5px;
}

.received {
  text-align: left;
  /* background-color: magenta; */
  padding: 5px;
  color : black;
}

.input-section {
  margin-top: 20px;
  display: flex;
}

.input-section input {
  flex: 30;
}

.input-section button {
  margin-left: 10px;
}

.messageInput {
  background-color: FFFEF9;
  /* background-color: red; */
  width: 500px;
  height: 32px;
  font-size: 15px;
  border: 0;
  /* border-radius: 15px; */
  border: 1.5px solid #B87514;
  border-radius: 40px;
  outline: none;
  padding-left: 10px;
  display: flex;
  color : #B87514;
}

.messageInput::placeholder{
  color : #B87514;
}

.sendMessage{
  text-decoration:none;
  position: absolute;
  left: 80.49%;
  right: 17.43%;
  top: 89.56%;
  bottom: 7.11%;
}


</style>