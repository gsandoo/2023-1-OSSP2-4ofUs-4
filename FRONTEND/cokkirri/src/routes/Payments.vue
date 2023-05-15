<template>
  <div>
    <div class = "BoxingPay">
      <div class = Back>
        <div class = "Back_img">
          <a href="javascript:history.back();">
            <img :src="imagePath_arrow" alt="Arrow" />
          </a>
        </div>
        <div>
          <a href="javascript:history.back();" class = "Back_txt">뒤로가기</a>
        </div>
      </div>
      <div class = "Title">
        <p>하트 충전소</p>
      </div>
      <div class = "Parent">
        <div class = "HeartNum">
          <div class = "HeartNum_text">
            <p>나의 하트</p>
          </div>
          <div class = "HeartNum_img">
            <!--뒷그림 수정 예정-->
            <img :src="imagePath_htnum" alt="Heartnum" class = "htnum"/>
          </div>
        </div>
        <div class="GoPay">
          <div class = "GoPay_text">
            <p>하트 충전</p>
          </div>
          <div class = "GoPay_heart">
            <img :src="imagePath_heart" alt="heart" />
          </div>
          <div class = "GoPay_10ht">
            <p>10개</p>
          </div>
          <div class = "GoPay_img">
            <img :src="imagePath_gopay" alt="Heartgopay" />
          </div>
          <div>
            <a href="#" @click="requestPay" class = "charge">1000원</a>
          </div>
        </div>
        <div class = "UseHistory">
          <div class = "UseHistory_text">
            <p>사용 내역</p>
          </div>
          <div class = "UseHistory_img>">
            <img :src="imagePath_history" alt="UseHistory" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/api';

export default {
  data() {
    return {
      imagePath_arrow: require('@/assets/pay/arrow-left.png'),
      imagePath_htnum: require('@/assets/pay/rec_heart.png'),
      imagePath_gopay: require('@/assets/pay/rec_gopay.png'),
      imagePath_history: require('@/assets/pay/rec_history.png'),
      imagePath_heart: require('@/assets/pay/heart.png'),

      priceAmount: 1000,
      buyerMemberEmail: "skxksls@gmail.com",
      buyerMemberName: "나찬진",
      IMP: null, // IMP 객체 선언
    };
  },
  mounted() {
    this.loadIamportScript();
  },
  methods: {
    loadIamportScript() {
      // iamport.payment.js 로드
      const script = document.createElement("script");
      script.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
      script.onload = this.initializeIamport;
      document.head.appendChild(script);
    },
    initializeIamport() {
      // iamport.payment.js 로드 완료 후 실행될 콜백 함수
      this.IMP = window.IMP; // IMP 객체 초기화
      this.IMP.init(""); // 외부 유출 금지!! API 키를 넣어주세요
    },
    requestPay() {
      const { priceAmount, buyerMemberEmail, buyerMemberName, IMP } = this;

      if (typeof IMP === "undefined") {
        // IMP 객체가 아직 정의되지 않았을 경우에는 오류 처리
        alert("결제 모듈을 로드 중입니다. 잠시 후 다시 시도해주세요.");
        return;
      }

      // IMP.request_pay(param, callback) 결제창 호출
      IMP.request_pay(
        {
          pg: "kakaopay.TC0ONETIME",
          pay_method: "card",
          merchant_uid: "heart_" + new Date().getTime(),
          name: "heart",
          amount: priceAmount,
          buyer_email: buyerMemberEmail,
          buyer_name: buyerMemberName,
        },
        this.handlePaymentResponse
      );
    },
    handlePaymentResponse(rsp) {
      // 결제 검증
      this.verifyPayment(rsp.imp_uid);
    },
    verifyPayment(impUid) {
      // 서버로 결제 검증 요청
      const apiUrl = `/verifyIamport/${impUid}`;

      // AJAX 요청
      axios.post(apiUrl).then((response) => {
        const result = response.data;

        // rsp.paid_amount와 result.response.amount(서버 검증) 비교 후 로직 실행
        if (result && response.paid_amount === result.response.amount) {
          alert("결제가 완료되었습니다.");
        } else {
          alert(
            "결제에 실패했습니다. 에러코드: " +
              response.error_code +
              " 에러 메시지: " +
              response.error_message
          );
        }
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
  top: 4rem;

font-style: normal;
font-weight: bold;
font-size: 50px;
line-height: 75px;
display: flex;
align-items: center;
text-align: center;
}

.Parent{
  width: 90%;
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
  top : 10rem;
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
  top: 10rem;
  float: left;
  width:30%;
  box-sizing: border-box;
}

.GoPay_heart{
  position : absolute;
  left : 4rem;
  top : 15rem;
}

.GoPay_10ht{
  position : absolute;
  left : 7.5rem;
  top : 14.25rem;
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

  top: 10rem;
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

.charge{ color:white;
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
</style>
