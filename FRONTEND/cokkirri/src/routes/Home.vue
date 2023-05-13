<template>
    <!-- 이곳에 로그인 뼈대 생성. 추후 컴포넌트로 빼내서 활용할 예정.  -->
    <div class="background-setting">
        <div class="container" >
            <div class="form-frame">
                <div :style="{'margin-left': '341px'}">
                    <p :style="{'margin-left': '5px','margin-top':'53px','margin-bottom':'0','font-size': '16px','color': '#8D8D8D'}">
                        아직 회원이 아니라면 ?
                    </p>
                    <router-link to="/signup" :style="{'margin-left': '95px','color': '#B87514'}">회원가입</router-link>
                </div>
                <h2 :style="{'margin-top': '18px','margin-left': '44px','font-size':'55px'}">로그인</h2>
                <div :style="{'margin-top': '52px'}">
                    <p :style="{'margin-left': '44px','font-size': '16px','margin-bottom': '0px'}">아이디 또는 이메일 주소를 입력하세요.</p>
                    <input
                        name="user id" 
                        placeholder="email address"
                        :style="bigInputStyle" 
                        @keydown.enter="submitForm"
                        v-model="user.id">
                    <p :style="{'margin-top': '69px','margin-left': '44px','font-size': '16px','margin-bottom': '0px'}">비밀번호를 입력하세요</p>
                    <input 
                        type="password"
                        name="password" 
                        placeholder="password" 
                        :style="[bigInputStyle,{'margin-bottom': '8px'}]"
                        @keydown.enter="submitForm"
                        v-model="user.password">
                    <router-link @click="alertMassage" to="/" :style="{'margin-left': '416px','color': '#AD3113','font-size': '13px'}">비밀번호 찾기</router-link>
                    <button 
                        :style="ButtonStyle"
                        @click="submitForm">
                        로그인
                    </button>
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
            user: {
                id: '',
                password: '',
            },
            // 스타일 객체
            bigInputStyle: {
                'margin-top': '13px',
                width: '451px',
                height: '50px', 
                'margin-left': '44px',
                background: '#FFFFFF',
                border: '1px solid #4285F4',
                'border-radius': '9px'
            },
            ButtonStyle: {
                'box-shadow': '0px 4px 19px rgba(119, 147, 65, 0.3)',
                'border-radius': '10px',
                background: '#E48700',
                'margin-top': '80px',width: '451px', height: '54px',
                'margin-left': '44px', 'margin-right': '44px',
                'border-color': '#E48700',
                color: '#FFFFFF'
            }
        }
    },
    // 비밀번호 찾기 api 구현되면 구성할 부분
    methods: {
        alertMassage(){
            alert("구현 예정")
        },
        // 로그인 api 요청 부분. 반환값에 토큰 없음.
        async login() {
            try{
                await axios.post('/login', null, {
                    params: {
                        id: this.user.id,
                        password: this.user.password,
                    }
                })
                .then((result) => {
                    if(result.status === 200){
                        if(result.data === true){
                            this.$store.commit('loginSuccess', this.user.id);
                            alert('로그인 되었습니다.')
                        }
                        else{
                            alert('아이디 밒 비밀번호에 대응되는 회원 정보가 없습니다.')
                        }
                    }
                }).catch(function(error){
                    console.log(error);
                });
            } catch(error){
                console.log(error);
            }
        },
        // 로그인 입력 값 검사
        submitForm() {
            if (this.user.id === '') {
                alert('아이디를 입력하세요.')
            }
            else if (this.user.password === '') {
                alert('비밀번호를 입력하세요.')
            }
            else{
                this.login()
            }
        }
    }
}
</script>

<style lang="scss" scoped>
    @import "../scss/main";
    // 배경화면 설정
    .background-setting{
        height: 100vh;
        width: 100vw;
        margin:0;
        background: url("../assets/login/background.jpg") no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        display: grid;
        grid-template-rows: auto;
        justify-items: center;
        align-items: center;
    }
    // container 클래스 위치 조정
    .container{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    // form 틀 만들기
    .form-frame {
        width: 539px;
        height: 741px;
        background: #FFFEF9;
        box-shadow: 0px 4px 35px rgba(0, 0, 0, 0.08);
        border-radius: 40px;
    }
</style>