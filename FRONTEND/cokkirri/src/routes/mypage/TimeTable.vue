<template>
    <!--  마이페이지의 시간표 및 관리  -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-body">
                    <div>
                            <router-link to="/my" class="my-link">&lt;</router-link>
                            <div style="clear:both;"></div>
                            <div class="timetable-img"></div>
                            <div class="timetable-txt">내 시간표</div>

                            <div v-if="!stateTF.iseditState" class="timetable-btn-edit" @click="editStateControl()"> 수정</div>
                            <div v-if="stateTF.iseditState" class="timetable-btn-complete" @click="editStateControl()"> 완료</div>

                            <div style="clear:both;"></div>
                            <div class="line-for-division"></div>
                            <div class="font-head">시간표</div>
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
    .frame-body{
        width: 996px;
        height: 569px;
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
        .timetable-img{
            width: 40px;
            height: 40px;
            margin-top: 0px;
            margin-left: 69px;
            background-image: url("../../assets/mypage/timetable.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
        }
        .timetable-txt{
            width: 200px;
            height: 40px;
            margin-top: 0px;
            margin-left: 31px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .timetable-btn-edit{
            width: 163px;
            height: 55px;
            background-color: #B87514;
            margin-top: 0px;
            margin-left: 438px;
            border-radius: 20px;
            float: left;

            font-size: 23px; 
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .timetable-btn-complete{
            width: 163px;
            height: 55px;
            background-color: #D9D9D9;
            margin-top: 0px;
            margin-left: 438px;
            border-radius: 20px;
            float: left;

            font-size: 23px; 
            display: flex;
            justify-content: center;
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
    }

</style>