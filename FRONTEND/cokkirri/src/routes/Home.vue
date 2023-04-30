<template>
    <!-- 이곳에 로그인 뼈대 생성. 추후 컴포넌트로 빼내서 활용할 예정.  -->
    <div class="container background" >
        <div class="form-frame">
            <h2 :style="{'padding-top': '113px','margin-left': '44px'}">로그인</h2>
            <div :style="{'margin-top': '52px'}">
                <form @submit.prevent="fnLogin">
                    <p :style="{'margin-left': '44px'}">아이디 또는 이메일 주소를 입력하세요.</p>
                        <input
                            class="w3-input" 
                            name="uid" 
                            placeholder="email address"
                            :style="{'margin-top': '13px',width: '451px', height: '50px', 'margin-left': '44px',background: '#FFFFFF',border: '1px solid #4285F4','border-radius': '9px'}" 
                            v-model="user_id">
                    <p :style="{'margin-top': '69px','margin-left': '44px'}">비밀번호를 입력하세요</p>
                    <input 
                        name="password" 
                        class="w3-input" 
                        placeholder="password" 
                        :style="{'margin-top': '13px',width: '451px', height: '50px', 'margin-left': '44px',background: '#FFFFFF',border: '1px solid #4285F4','border-radius': '9px'}"
                        v-model="user_pw" type="password">
                    <p>
                        <button 
                            type="submit"
                            class="w3-button w3-green w3-round"
                            :style="{'box-shadow': '0px 4px 19px rgba(119, 147, 65, 0.3)','border-radius': '10px',background: '#E48700','margin-top': '80px',width: '451px', height: '54px', 'margin-left': '44px'}">
                            로그인
                        </button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    // 아직 로그인 추가 구현 필요함
    data() {
        return {
            user_id: '',
            user_pw: '',
    }},
    methods: {
        fnLogin() {
            if (this.user_id === '') {
                alert('ID를 입력하세요.')
            return
            }
            if (this.user_pw === '') {
                alert('비밀번호를 입력하세요.')
                return
            }
            alert('로그인 되었습니다.')
        }
    }
}
</script>

<style lang="scss" scoped>
    @import "../scss/main";
    // 배경화면. 추후 컴포넌트에서 벗어나서 상위 배경화면으로 옮길 예정.
    .background{
        background: url("../assets/login/background.jpg") no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }
    // container 위치 조정
    .container{
        padding-top: 10vh;
        display: flex;
        justify-content: center;
        
    }
    // form 틀 만들기
    .form-frame {
        width: 539px;
        height: 741px;
        background: #FFFFFF;
        box-shadow: 0px 4px 35px rgba(0, 0, 0, 0.08);
        border-radius: 40px;
    }
</style>