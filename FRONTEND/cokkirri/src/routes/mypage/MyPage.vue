<template>
    <!-- MyPage  -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-head">
                    <div class="user-img"></div>
                    <div class="user-name">{{this.$store.state.name}}</div>
                    <router-link to="/my/profile" class="user-btn">내 정보</router-link>
                    <div style="clear:both;"></div>
                </div>
                <div class="frame-body">
                    <div>
                            <div class="heart-img"></div>
                            <div class="heart-txt">내 하트</div>
                            <div class="heart-btn"><router-link to="/Payments">충전 하기</router-link></div>
                            <div style="clear:both;"></div>
                            <div class="line-for-division"></div>

                            <div class="timetable-img"></div>
                            <div class="timetable-txt">내 시간표</div>
                            <router-link to="/my/timetable" class="timetable-btn"> 등록 및 수정 &gt;</router-link>
                            <div style="clear:both;"></div>
                            <div class="line-for-division"></div>

                            <div class="matching-img"></div>
                            <div class="matching-txt">매칭 결과</div>
                            <router-link to="/my/matching" class="matching-btn">확인하기 &gt;</router-link>
                            <div style="clear:both;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>




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
        .user-btn{
            width: 163px;
            height: 55px;
            background-color: #D9D9D9;
            margin-left: 535px;
            border-radius: 20px;
            float: left;

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

        .heart-img{
            width: 43px;
            height: 40px;
            margin-top: 59px;
            margin-left: 69px;
            background-image: url("../../assets/mypage/heart.png");
            background-size: cover;
            
            float: left;
        }
        .heart-txt{
            width: 200px;
            height: 40px;
            margin-top: 59px;
            margin-left: 28px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .heart-btn{
            width: 596px;
            height: 40px;
            margin-top: 59px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
            align-items: center;
        }

        .timetable-img{
            width: 40px;
            height: 40px;
            margin-top: 40px;
            margin-left: 69px;
            background-image: url("../../assets/mypage/timetable.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
        }
        .timetable-txt{
            width: 200px;
            height: 40px;
            margin-top: 40px;
            margin-left: 31px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .timetable-btn{
            width: 596px;
            height: 40px;
            margin-top: 40px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
            align-items: center;
        }

        .matching-img{
            width: 40px;
            height: 40px;
            margin-top: 40px;
            margin-left: 69px;
            background-image: url("../../assets/mypage/matching.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
        }
        .matching-txt{
            width: 200px;
            height: 40px;
            margin-top: 40px;
            margin-left: 31px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .matching-btn{
            width: 596px;
            height: 40px;
            margin-top: 40px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
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
    }

</style>