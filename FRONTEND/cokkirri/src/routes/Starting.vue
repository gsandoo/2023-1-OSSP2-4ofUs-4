<!--매칭페이지로 넘어가기 전 스타팅페이지-->

<template>
  <div>
    <div class = "BOX">
    <div class = "whole">

      <div class = "parent-talk">

        <div class = "wrap-que">
          <div class = "question">
            
            <div class="mybutton">
              <router-link class="link-button" to="/my/matching">매칭 페이지</router-link>
            </div>

            <img :src="imagePath_q" alt="User Question" />
            <img :src="imagePath_a" alt="User Answer" class = "answer"/>

            <div class = "text-que">
              <p>심각한<br><br><br>'Co-끼리' 중독입니다.</p>
            </div>

            <div class = "text-ans">
              <p>흥, 중독인지 아닌지<br><br><br>매칭된 사람한테 물어봐요.</p>
            </div>

          </div>
        </div>

      </div>

      <div class = "GotoMypage">
        <router-link class="link-mypage" to = "/my">My Page</router-link>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imagePath_q: require('@/assets/startmainpage/user_question.png'),
      imagePath_a: require('@/assets/startmainpage/user_answer.png'),
      imagePath_back: require('@/assets/startmainpage/MainBack.png')
    };
  }
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

.BOX{
  height : 100%;
  width : 100%
}

.whole{
  height: 100vh;
  width: 100vw;
  overflow: auto;
  margin:0;
  background-image: url("@/assets/startmainpage/MainBack.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

.mybutton{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 12px 20px;
  gap: 10px;

  position: absolute;
  width: 230px;
  height: 67px;
  left: 100px;
  top: 600px;

  background: #B87514;
  border-radius: 50px;
}

.link-button{
  width: 187px;
  height: 24px;

  text-decoration : none;
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 24px;
  text-align: center;

  letter-spacing: 0.5px;
  color: #FFFFFF;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.GotoMypage{
  position: absolute;
  width: 82px;
  height: 30px;
  left: 75rem;
  top: 63px;
}

.link-mypage{
  text-decoration : none;
  font-style: normal;
  font-weight: 600;
  font-size: 20px;
  line-height: 30px;
  color: #B87514;
}

.parent-talk{
  display: flex;
  align-items: center;
  justify-content: center;
  margin : 10px auto;
}

.question{ /* 중독입니다.*/
  position: absolute;
  left: 33.82%;
  right: 32.57%;
  top: 14.67%;
  bottom: 59.34%;
  mix-blend-mode: normal;
}

.text-que{
  position: absolute;
  width: 439px;
  height: 265px;
  left: 30px;
  top: -35px;
  
  font-style: normal;
  font-weight: bold;
  font-size: 40px;
  line-height: 24px;

  display: flex;
  align-items: center;
  letter-spacing: 0.5px;

  color: #FFFFFF;
}

.answer{ /* 흥! */
  position: absolute;
  left: 0px;
  right: 32.57%;
  top: 300px;
  bottom: 23.68%;
  mix-blend-mode: normal;
}

.text-ans{
  position: absolute;
  width: 484px;
  height: 158px;
  left: 30px;
  top: 320px;

  font-style: normal;
  font-weight: bold;
  font-size: 38px;
  line-height: 24px;

  display: flex;
  align-items: center;
  letter-spacing: 0.5px;

  color: #000000;
}

</style>
