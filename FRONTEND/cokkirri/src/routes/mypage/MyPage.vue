<template>
    <!-- MyPage  -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-head">
                    <div class="user-img"></div>
                    <div class="user-name">~이름~</div>
                    <div class="user-btn">내 정보</div>
                </div>
                <div class="frame-body">
                    <div>
                            <div class="heart-img"></div>
                            <div class="heart-txt">내 하트</div>
                            <router-link to="/my/heart" class="heart-btn">~쿠키 개수~ &gt;</router-link>
                            <div style="clear:both;"></div>

                            <div class="timetable-img"></div>
                            <div class="timetable-txt">내 시간표</div>
                            <router-link to="/my/timetable" class="timetable-btn"> 등록 및 수정 &gt;</router-link>
                            <div style="clear:both;"></div>

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
        width: 1255px;
        height: 157px;
        background-color: #FFFEF9;
        border-radius: 20px;
        display: flex;
        align-items: center;
        .user-img{
            width: 116px;
            height: 119px;
            margin-left: 47px;
            background-image: url("../../assets/mypage/user.png");
        }
        .user-name{
            width: 152px;
            height: 71px;
            margin-left: 0px;

            font-size: 35px; 
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .user-btn{
            width: 205px;
            height: 69px;
            background-color: #D9D9D9;
            margin-left: 675px;
            border-radius: 20px;

            font-size: 23px; 
            display: flex;
            justify-content: center;
            align-items: center;
        }
    }
    .frame-body{
        width: 1255px;
        height: 500px;
        margin-top: 65px;
        background-color: #FFFEF9;
        border-radius: 20px;
        display: flex;

        .heart-img{
            width: 50px;
            height: 50px;
            margin-top: 80px;
            margin-left: 95px;
            background-image: url("../../assets/mypage/heart.png");
            background-repeat: no-repeat;
            float: left;
        }
        .heart-txt{
            width: 200px;
            height: 55px;
            margin-top: 80px;
            margin-left: 39px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .heart-btn{
            width: 805px;
            height: 50px;
            margin-top: 80px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
            align-items: center;
        }

        .timetable-img{
            width: 50px;
            height: 50px;
            margin-top: 96px;
            margin-left: 95px;
            background-image: url("../../assets/mypage/timetable.png");
            background-repeat: no-repeat;
            float: left;
        }
        .timetable-txt{
            width: 200px;
            height: 55px;
            margin-top: 96px;
            margin-left: 39px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .timetable-btn{
            width: 805px;
            height: 50px;
            margin-top: 96px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
            align-items: center;
        }

        .matching-img{
            width: 50px;
            height: 50px;
            margin-top: 96px;
            margin-left: 95px;
            background-image: url("../../assets/mypage/matching.png");
            background-repeat: no-repeat;
            float: left;
        }
        .matching-txt{
            width: 200px;
            height: 55px;
            margin-top: 96px;
            margin-left: 39px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .matching-btn{
            width: 805px;
            height: 50px;
            margin-top: 96px;
            float:left;

            font-size: 30px;
            color: #B87514;
            display: flex;
            justify-content: right;
            align-items: center;
        }
    }

</style>