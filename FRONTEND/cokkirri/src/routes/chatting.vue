<template>
  <div>
    <header>
      <nav class = "menuBar">
        <span>
          <a href="javascript:history.back();">
            <img :src="imagePath_arrow" alt="Arrow" class = "Back_img"/>
          </a>
        </span>
        <span>
          <a href="javascript:history.back();" class="Back_txt">뒤로가기</a>
        </span>
        <div class = "co-txt">
          <span>
            <h1>Co-kkirri</h1>
          </span>
        </div>
      </nav>
    </header>

    <main>
      <div class="WholeBox">
        <div class="chat-room">
          <div class="messages">
            <div v-for="message in messages" :key="message.id" class="message">
              <div :class="{'sent-by-me': message.isSentByMe, 'received-from': !message.isSentByMe}" class="message-content">
                {{ message.isSentByMe ? message.text : message.received }}
              </div>
            </div>
          </div>
          <div class="input-section">
            <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" class="messageInput">
            <a @click="sendMessage" class="sendMessage">
              <!--전송하기 화살표 구현해야함.-->
              <i class="fa-paper-plane-top" style="color: #b87514;"></i>
            </a>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>


<script>
import { ref, onMounted } from 'vue';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import axios from '../api/index.js';

export default {
  data() {
    return {
      imagePath_arrow: require("@/assets/pay/arrow-left.png"),
    };
  },
  setup() {
    const messages = ref([]);
    const newMessage = ref('');
    const matchingId = ref(1);
    const matchingType = ref('free');
    const sender = ref('skxkswls@gmail.com');
    let stompClient = null;
    
    /*추가한 부분*/
    const received = ref('');

    const connectToWebSocket = () => {
      const socket = new SockJS('http://3.37.37.164:8080/ws');
      stompClient = Stomp.over(socket);

      socket.onopen = () => {
        stompClient.subscribe(`/room/${matchingId.value}/${matchingType.value}`, (message) => {
          const receivedMessage = JSON.parse(message.body);
          messages.value = [
            ...messages.value,
            {
              id: Date.now(),
              text: receivedMessage.content, // 수정: message.content 값을 사용
              isSentByMe: false // 수정: 서버에서 받은 메시지이므로 false로 설정
            }
          ];
          received.value = receivedMessage.content; // 추가된 부분
        });
      };

    };

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
      messages.value = [
        ...messages.value,
        {
          id: Date.now(),
          text: newMessage.value,
          isSentByMe: true
        }
      ];
      newMessage.value = '';
    };

    const fetchChatHistory = async () => {
      try {
        const response = await axios.get(`/room/${matchingId.value}/${matchingType.value}`);
        messages.value = response.data;
        console.log(response.data);
      } catch (error) {
        console.error('Failed to fetch chat history:', error);
      }
    }

    onMounted(async () => {
      connectToWebSocket();
      try {
        await fetchChatHistory();
      } catch (error) {
        console.error('Failed to load chat history:', error);
      }
    });

    return {
      messages,
      newMessage,
      matchingId,
      matchingType,
      sender,
      sendMessage,
      received // received 속성 반환
    };
  },
  filters: {
    formatTimestamp(timestamp) {
      const options = {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        hour12: true,
      };
      return new Intl.DateTimeFormat('en-US', options).format(timestamp);
    },
  },
};

</script>

<style scoped>

header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  
  height: 30px;
  padding: 1rem;
  color: #B87514;
  background: #fffef9;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align : center;
}

/* .WholeBox{
  background-color: yellow;
} */

.chat-room {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
  padding-top: 75px;
}

.messages { 
  /* background-color:black; */
  max-height: 700px;
  overflow-y: scroll;
  }

.message {
  /* background-color:blue; */
  margin-bottom: 10px;
}

.sent-by-me {
  text-align: right;
  background-color: #B87514;
  padding: 5px;
  color : white;
}

.received-from {
  background-color : #ECBC76;
  text-align: left;
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

.Back_img{
position: absolute;
position : fixed;
left: 3.68%;
right: 93.89%;
top: 10px;
bottom: 90.11%;
}

.Back_txt{color : #B87514;
position: absolute;
position : fixed;
width: 98px;
height: 38px;
left: 100px;
top: 10px;

text-decoration : none;
font-style: normal;
font-weight: 600;
font-size: 20px;
line-height: 38px;
}

.co-txt {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left : 680px;
}

.co-txt h1 {
  text-align: center;
}

</style>