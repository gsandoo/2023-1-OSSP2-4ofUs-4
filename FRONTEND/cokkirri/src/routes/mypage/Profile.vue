<template>
    <!-- 마이페이지의 프로필 세부사항 -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-head">
                    <div class="user-img"></div>
                    <div class="user-name">{{this.$store.state.name}}</div>
                    <div v-if="!stateTF.iseditState" class="user-btn-edit" @click="editStateControl()">수정</div>
                    <div v-if="stateTF.iseditState" class="user-btn-complete" @click="editStateControl()">완료</div>
                    <div style="clear:both;"></div>
                </div>
                <div class="frame-body">
                    <div>
                        <router-link to="/my" class="my-link">&lt;</router-link>
                        <div style="clear:both;"></div>
                        <div class="font-head">Info</div>
                        <div class="font-body">
                            id: {{this.$store.state.id}}<br>
                            major: {{this.$store.state.major}}<br>
                            name: {{this.$store.state.name}}<br>
                            number: {{this.$store.state.number}}<br>
                            sex: {{this.$store.state.sex}}<br>
                            studentNum: {{this.$store.state.studentNum}}<br>
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
        display: flex;
        .my-link{
            width: 100px;
            height: 71px;
            margin-top: 0px;
            margin-left: 17px;
            float:left;

            font-size: 50px;
            color: #B87514;
            display: flex;
            justify-content: left;
            align-items: center;
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
            width: 200px;
            height: 40px;
            margin-left: 28px;

            font-size: 50px;
            color: #B87514;
        }
        .font-body{
            width: 891px;
            height: 400px;
            margin-top: 17px;
            margin-left: 53px;
            margin-bottom: 0px;

            font-size: 30px;
        }
    }

</style>