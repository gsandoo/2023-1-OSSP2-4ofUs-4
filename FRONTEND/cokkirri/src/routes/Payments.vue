<template>
  <div>
    <button @click="requestPay">결제하기</button>
    <button @click="setAmount(1000)">1000원</button>
    <button @click="setAmount(3000)">3000원</button>
    <button @click="setAmount(5000)">5000원</button>
    <button @click="setAmount(10000)">10000원</button>
  </div>
</template>

<script>
//import axios from 'axios';

export default {
  data() {
  return {
    amount: 0 // 초기값은 0으로 설정
  };
  },
  methods: {
    setAmount(value) {
    this.amount = value;
    },
    requestPay() {
      const IMP = window.IMP;
      IMP.init(""); // 예: imp00000000 - github에는 x

      IMP.request_pay(
        {
          // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "ORD20180131-0000011",
          name: "cokkirri 서비스 하트충전",
          amount: this.amount, //amount 변수 사용
          buyer_email: "gildong@gmail.com",
          buyer_name: "김혜성",
          buyer_tel: "010-3333-3333",
          buyer_addr: "서울특별시 구로구 고척동",
          buyer_postcode: "08275"
        },
        (rsp) => {
          // callback
          if (rsp.success) {
            // 결제 성공 시 로직
          } else {
            // 결제 실패 시 로직
          }
        }
      );
    }
  }
};
</script>
<style>
/* 필요한 스타일이 있다면 여기에 추가 */
</style>