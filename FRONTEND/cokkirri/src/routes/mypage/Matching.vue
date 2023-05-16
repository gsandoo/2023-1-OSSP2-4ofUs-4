<template>
    <!-- 마이페이지의 매칭 세부정보  -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-body">
                    <div>
                        <router-link to="/my" class="my-link">&lt;</router-link>
                        <div style="clear:both;"></div>
                        <div class="matching-img"></div>
                        <div class="matching-txt">매칭 결과</div>
                        <router-link to="/my/matching" class="matching-btn">새로고침</router-link>
                        <div style="clear:both;"></div>
                        <div class="line-for-division"></div>
                        <div class="font-head">매칭 상황</div>
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
        .matching-img{
            width: 40px;
            height: 40px;
            margin-top: 0px;
            margin-left: 69px;
            background-image: url("../../assets/mypage/matching.png");
            background-size: cover;
            background-repeat: no-repeat;
            float: left;
        }
        .matching-txt{
            width: 200px;
            height: 40px;
            margin-top: 0px;
            margin-left: 31px;
            float: left;

            font-size: 30px; 
            display: flex;
            align-items: center;
        }
        .matching-btn{
            width: 163px;
            height: 55px;
            background-color: #B87514;
            margin-top: 0px;
            margin-left: 438px;
            border-radius: 20px;
            float: left;

            font-size: 23px; 
            color: #FFFFFF;
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