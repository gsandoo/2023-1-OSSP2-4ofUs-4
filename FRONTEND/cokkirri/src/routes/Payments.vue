<template>
  <div>
  <div class="BoxingPay">
    <div class="Back">
      <div class="Back_img">
        <a href="javascript:history.back();">
          <img :src="imagePath_arrow" alt="Arrow" />
        </a>
      </div>
      <div>
        <a href="javascript:history.back();" class="Back_txt">뒤로가기</a>
      </div>
    </div>
    <div class="Title">
      <p>하트 충전소</p>
    </div>
    <div class="Parent">
      <div class="HeartNum">
        <div class="HeartNum_text">
          <p>나의 하트</p>
        </div>
        <div class = "NumOfHeart">
          <!-- {{this.$store.state.heart}} &gt; -->
          <p>2</p>
        </div>
        <div class="HeartNum_img">
          <img :src="imagePath_htnum" alt="Heartnum" class="htnum" />
        </div>
      </div>
      <div class="GoPay">
        <div class="GoPay_text">
          <p>하트 충전</p>
        </div>
        <div class="GoPay_heart">
          <img :src="imagePath_heart" alt="heart" class = "heart-1000"/>
          <img :src="imagePath_heart" alt="heart" class = "heart-3000"/>
          <img :src="imagePath_heart" alt="heart" class = "heart-5000"/>
          <img :src="imagePath_heart" alt="heart" class = "heart-10000"/>
        </div>

        <div class="GoPay_5ht"> <p>5개</p> </div>
        <div class="GoPay_10ht"> <p>10개</p> </div>
        <div class="GoPay_15ht"> <p>15개</p> </div>
        <div class="GoPay_20ht"> <p>20개</p> </div>

        <div class="GoPay_img">
          <img :src="imagePath_gopay" alt="Heartgopay" />
        </div>
        <div>
          <a href="#" @click.prevent="checkModule(1000)" class = "charge1000">1000원</a>
          <a href="#" @click.prevent="checkModule(3000)" class = "charge3000">3000원</a>
          <a href="#" @click.prevent="checkModule(5000)" class = "charge5000">5000원</a>
          <a href="#" @click.prevent="checkModule(10000)" class = "charge10000">10000원</a>
        </div>
      </div>
      <div class="UseHistory">
        <div class="UseHistory_text">
          <p>사용 내역</p>
        </div>
        <div class="UseHistory_img">
          <img :src="imagePath_history" alt="UseHistory" />
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from '../api/index.js';

export default {
data() {
  return {
    // 이미지 삽입
    imagePath_arrow: require("@/assets/pay/arrow-left.png"),
    imagePath_htnum: require("@/assets/pay/rec_heart.png"),
    imagePath_gopay: require("@/assets/pay/rec_gopay.png"),
    imagePath_history: require("@/assets/pay/rec_history.png"),
    imagePath_heart: require("@/assets/pay/heart.png"),

    userId: "skxkswls@gmail.com",
    payDate: '2023-05-15',
  };
},

methods: {
  // 금액에 따라 결제를 달리하기 위해 checkModule(amount) 형태로 작성
  checkModule(amount) {
    var IMP = window.IMP;
    IMP.init(""); // 대외비, 가맹점 코드 입력
    
    IMP.request_pay(
      {
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: 'heart_' + new Date().getTime(),
        name: 'cokkirri 하트 결제',
        payDate: this.payDate,
        amount: amount,
        buyer_email: this.userId,
      },
      (rsp) => {
        console.log(rsp);
        let msg;
        
        if (rsp.success) {
          msg = '결제가 완료되었습니다.';
          this.$router.push("/Payments"); // Payments 페이지로 이동
          // msg += '고유ID : ' + rsp.imp_uid;
          // msg += '상점 거래ID : ' + rsp.merchant_uid;
          // msg += '결제 금액 : ' + rsp.paid_amount;
          // msg += '카드 승인번호 : ' + rsp.apply_num;

          // 서버에 결제 정보 전송
          this.sendPaymentInfo(amount);
        } else {
          msg = '결제에 실패하였습니다.';
          msg += '에러내용 : ' + rsp.error_msg;
          this.$router.push("/Payments"); // Payments 페이지로 이동
        }
        alert(msg);
      }
    );
  },
  
  sendPaymentInfo(amount) {
    const paymentData = {
      userId: this.userId,
      payDate: this.payDate,
      amount: parseInt(amount),
    };
    
    axios.put('/payment', paymentData)
      .then(response => {
        console.log('결제 성공!:', response.data);
      })
      .catch(error => {
        console.error('결제 실패:', error);
      });
  },
},
};
</script>

<style scoped>
@font-face {
font-family: 'Merriweather Sans', sans-serif;
src: url("https://fonts.googleapis.com/css2?family=Merriweather+Sans:wght@800&display=swap");
}

* {
font-family: "Merriweather Sans";
}

.BoxingPay{
  background-color : #FFFEF9;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow : auto;

  height : 100vh;
  width : 100vw;
}

.Back_img{
position: absolute;
left: 3.68%;
right: 93.89%;
top: 6%;
bottom: 90.11%;
}

.Back_txt{color : #B87514;
position: absolute;
width: 98px;
height: 38px;
left: 111px;
top: 52px;

text-decoration : none;
font-style: normal;
font-weight: 600;
font-size: 25px;
line-height: 38px;
}

.Title{color: #B87514;
position: absolute;
width: 408px;
height: 3rem;
left: 40rem;
top: 7rem;

font-style: normal;
font-weight: bold;
font-size: 50px;
line-height: 75px;
display: flex;
align-items: center;
text-align: center;
}

.Parent{
width: 90vw;
margin: 10px auto;
}

.htnum{
width : 23rem;
height : 37.5rem;
}

.HeartNum{  /*border: 1px solid red;*/
position : relative;
text-align: center;
float: left;
width:30%;
top : 5rem;
box-sizing: border-box;
}

.HeartNum_text{color: #000;
position: absolute;
width: 10rem;
height: 5rem;
left: 8.5rem;
top: 5rem;

font-style: normal;
font-weight: 600;
font-size: 40px;
line-height: 60px;
display: flex;
align-items: center;
text-align: center;
}

.GoPay{  /*border: 1px solid green;*/
position : relative;
text-align: center;

margin-left: 5%;
left: 0rem;
right: 535px;
top: 5rem;
float: left;
width:30%;
box-sizing: border-box;
}

.GoPay_heart{
position : absolute;
left : 4rem;
top : 15rem;
vertical-align: auto;
}

.GoPay_5ht{
position : absolute;
left : 7.5rem;
top : 14.25rem;
font-weight: bold;
font-size: 20px;
}

.GoPay_10ht{
position : absolute;
left : 7.5rem;
top : 19.25rem;
font-weight: bold;
font-size: 20px;
}

.GoPay_15ht{
position : absolute;
left : 7.5rem;
top : 24.25rem;
font-weight: bold;
font-size: 20px;
}

.GoPay_20ht{
position : absolute;
left : 7.5rem;
top : 29.25rem;
font-weight: bold;
font-size: 20px;
}

.GoPay_text{ color: #000;
position: absolute;
width: 10rem;
height: 5rem;
left: 8rem;
top: 5rem;

font-style: normal;
font-weight: 600;
font-size: 40px;
line-height: 60px;
display: flex;
align-items: center;
text-align: center;
}

.UseHistory{  /*border: 1px solid blue;*/
position: relative;
text-align: center;

top: 5rem;
float: right;
width:30%;
box-sizing: border-box;
}

.UseHistory_text{color: #FFFEF9;
position: absolute;
width: 10rem;
height: 5rem;
left: 8.5rem;
top: 5rem;

font-style: normal;
font-weight: 600;
font-size: 40px;
line-height: 60px;
display: flex;
align-items: center;
text-align: center;
}

.charge1000{ color:white;
background: #ECBC76;
border-radius: 50px;

text-decoration : none;
font-weight: bold;
display: flex;
flex-direction: row;
justify-content: center;
align-items: center;
padding: 12px 20px;
gap: 10px;

position: absolute;
width: 5rem;
height: 1rem;
left: 15rem;
top: 15rem;
}

.heart-1000{
  position : absolute;
  left : 0.1rem;
  top : 0rem;
  vertical-align: auto;
}

.heart-3000{
  position : absolute;
  left : 0.1rem;
  top : 5rem;
  vertical-align: auto;
}

.heart-5000{
  position : absolute;
  left : 0.1rem;
  top : 10rem;
  vertical-align: auto;
}

.heart-10000{
  position : absolute;
  left : 0.1rem;
  top : 15rem;
  vertical-align: auto;
}

.charge3000{ color:white;
background: #ECBC76;
border-radius: 50px;

text-decoration : none;
font-weight: bold;
display: flex;
flex-direction: row;
justify-content: center;
align-items: center;
padding: 12px 20px;
gap: 10px;

position: absolute;
width: 5rem;
height: 1rem;
left: 15rem;
top: 20rem;
}

.charge5000{ color:white;
background: #ECBC76;
border-radius: 50px;

text-decoration : none;
font-weight: bold;
display: flex;
flex-direction: row;
justify-content: center;
align-items: center;
padding: 12px 20px;
gap: 10px;

position: absolute;
width: 5rem;
height: 1rem;
left: 15rem;
top: 25rem;
}

.charge10000{ color:white;
background: #ECBC76;
border-radius: 50px;

text-decoration : none;
font-weight: bold;
display: flex;
flex-direction: row;
justify-content: center;
align-items: center;
padding: 12px 20px;
gap: 10px;

position: absolute;
width: 5rem;
height: 1rem;
left: 15rem;
top: 30rem;
}

.NumOfHeart{
  position: absolute;
  width: 100px;
  height: 100px;
  left: 190px;
  top: 300px;

  font-style: normal;
  font-weight: 600;
  font-size: 50px;
  line-height: 75px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
</style>
