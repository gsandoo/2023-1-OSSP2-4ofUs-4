<template>
    <!-- 마이페이지의 프로필 세부사항 -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-head">
                    <div class="user-img"></div>
                    <div class="user-name">{{this.$store.state.name}}</div>
                    <div v-if="stateTF.iseditState" class="user-btn-complete" @click="editStateControl()">완료</div>
                    <div v-else class="user-btn-edit" @click="editStateControl()">수정</div>
                    <div style="clear:both;"></div>
                </div>
                <div class="frame-body">
                    <div>
                        <router-link to="/my" class="my-link">&lt;</router-link>
                        <div style="clear:both;"></div>
                        <div v-if="stateTF.iseditState" class="frame-sub-body">
                            <div class="font-head" :style="{'margin-top': '35px'}">아이디</div>
                            <div class="font-body" :style="{'margin-top': '35px'}">{{this.$store.state.id}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">전공</div>
                            <input type="text" class="font-input" v-model="this.$store.state.major">
                            <div style="clear:both;"></div>

                            <div class="font-head">이름</div>
                            <div class="font-body">{{this.$store.state.name}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">H.P</div>
                            <input type="text" class="font-input" v-model="this.$store.state.number">
                            <div style="clear:both;"></div>

                            <div class="font-head">성별</div>
                            <div class="font-body">{{this.$store.state.sex}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">학번</div>
                            <div class="font-body">{{this.$store.state.studentNum}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">PWD</div>
                            <input type="text" class="font-input" placeholder="비밀번호 재설정" v-model="userInfo.password">
                            <div style="clear:both;"></div>

                            <div class="font-head">REPWD</div>
                            <input type="text" class="font-input" placeholder="비밀번호 재설정 확인" v-model="userInfo.repassword">
                            <div style="clear:both;"></div>

                        </div>
                        <div v-else class="frame-sub-body">
                            <div class="font-head" :style="{'margin-top': '35px'}">아이디</div>
                            <div class="font-body" :style="{'margin-top': '35px'}">{{this.$store.state.id}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">전공</div>
                            <div class="font-body">{{this.$store.state.major}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">이름</div>
                            <div class="font-body">{{this.$store.state.name}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">H.P</div>
                            <div class="font-body">{{this.$store.state.number}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">성별</div>
                            <div class="font-body">{{this.$store.state.sex}}</div>
                            <div style="clear:both;"></div>

                            <div class="font-head">학번</div>
                            <div class="font-body">{{this.$store.state.studentNum}}</div>
                            <div style="clear:both;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userInfo: {
                major: '',
                number: '',
                password: '',
                repassword: ''
            },
            stateTF: {
                iseditState: false
            },
        }
    },
    methods: {
        editStateControl() {
            return this.stateTF.iseditState = !this.stateTF.iseditState;
        },
        UserUpdate() {
            this.$store.dispatch('userInfoUpdate')
        }
    }
    

}

</script>

<style lang="scss" scoped>
    @import "../../scss/main";
    
    // 배경화면 설정
    .background-setting{
        height: 100vh;
        width: 100vw;
        margin:0;

        background-color: #ECBC76; 
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
    .frame-head{
        width: 996px;
        height: 125px;
        background-color: #FFFEF9;
        border-radius: 20px;
        display: flex;
        align-items: center;
        .user-img{
            width: 92px;
            height: 95px;
            margin-left: 30px;
            background-image: url("../../assets/mypage/user.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
        }
        .user-name{
            width: 121px;
            height: 57px;
            margin-left: 0px;

            font-size: 35px; 
            display: flex;
            justify-content: center;
            align-items: center;
            float: left;
        }
        .user-btn-edit{
            width: 163px;
            height: 55px;
            margin-left: 535px;
            border-radius: 20px;
            float: left;
            background-color: #ECBC76;
            font-size: 23px; 
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .user-btn-complete{
            width: 163px;
            height: 55px;
            margin-left: 535px;
            border-radius: 20px;
            float: left;
            background-color: #D9D9D9;
            font-size: 23px; 
            display: flex;
            justify-content: center;
            align-items: center;
        }
    }
    .frame-body{
        width: 996px;
        height: 397px;
        margin-top: 47px;
        background-color: #FFFEF9;
        border-radius: 20px;

        .my-link{
            width: 43px;
            height: 31px;
            margin-top: 0px;
            margin-left: 0px;
            float:left;

            font-size: 50px;
            color: #B87514;
            display: flex;
            justify-content: left;
            align-items: center;
        }
        .frame-sub-body{
            width: 910px;
            height: 337px;
            margin-top: 0px;
            margin-left: 43px;
            background-image: url("../../assets/mypage/profile/sub-frame.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
            overflow-y: scroll;
        }
        .line-for-division{
            width: 891px;
            height: 1px;
            margin-top: 39px;
            margin-left: 53px;
            margin-bottom: 0px;

            border: 1px solid #B87514
        }
        .font-head{
            width: 144px;
            height: 75px;
            margin-top: 0px;
            margin-left: 0px;

            float: left;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 30px;
            color: #B87514;
        }
        .font-body{
            width: 754px;
            height: 75px;
            margin-top: 0px;
            margin-left: 12px;

            float: left;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 25px;
            color: #000000;
        }
        .font-input{
            width: 300px;
            height: 49px;
            margin-top: 13px;
            margin-left: 239px;
            margin-bottom: 13px;

            border: 5px solid #ECBC76;
            border-radius: 20px;

            float: left;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 25px;
            color: #000000;
        }
    }

</style>