<template>
    <!-- 마이페이지의 매칭 세부정보  -->
    <!-- 회원정보 수정 put에 대해 cors 에러 발생. 해결 필요. -->
    <div class="background-setting">
        <div class="container" >
            <div>
                <div class="frame-body">
                    <div>
                        <router-link to="/my" class="my-link">&lt;</router-link>
                        <div style="clear:both;"></div>

                        <div class="matching-img-box">
                            <div class="matching-img"></div>
                        </div>
                        <div class="matching-txt">매칭 결과</div>

                        <router-link to="/my/matching" class="matching-btn">새로고침</router-link>
                        <div style="clear:both;"></div>

                        <div class="line-for-division"></div>

                        <div class="frame-sub-body">
                            <div class="font-hash-h1"># 예시</div>
                            <div class="font-state-box"></div>

                            <div class="font-hash-h1"># 예시</div>
                            <div class="font-state-box"></div>

                            <div class="font-hash-h1"># 예시</div>
                            <div class="font-state-box"></div>

                            <div class="font-hash-h1"># 예시</div>
                            <div class="font-state-box"></div>

                            <div class="font-hash-h1"># 예시</div>
                            <div class="font-state-box"></div>

                            <div :style="{'margin-top': '30px'}"></div>
                        </div>
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
        
        .my-link{
            width: 51px;
            height: 46px;

            margin-top: 0px;
            margin-left: 17px;
            float:left;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-size: 50px;
            line-height: 75px;
            color: #B87514;
            display: flex;
            align-items: center;
        }
        .matching-img-box{
            width: 68px;
            height: 55px;
            
            margin-top: 0px;
            margin-left: 68px;
            float: left;

            display: flex;
            justify-content: left;
            align-items: center;
        }
        .matching-img{
            width: 40px;
            height: 40px;
            
            float: left;

            background-image: url("../../assets/mypage/matching.png");
            background-size: cover;
            background-repeat: no-repeat;
        }
        .matching-txt{
            width: 200px;
            height: 55px;

            margin-top: 0px;
            margin-left: 0px;
            float: left;

            display: flex;
            justify-content: left;
            align-items: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-size: 30px;
            line-height: 45px;
        }
        .matching-btn{
            width: 163px;
            height: 55px;
            background-color: #B87514;

            margin-top: 0px;
            margin-left: 444px;
            border-radius: 20px;
            float: left;

            color: #FFFFFF;
            display: flex;
            justify-content: center;
            align-items: center;

            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-size: 23px;
            line-height: 28px;
        }

        .line-for-division{
            width: 891px;
            height: 1px;
            margin-top: 30px;
            margin-left: 53px;
            margin-bottom: 0px;

            border: 1px solid #B87514
        }
    }
    .frame-sub-body{
        width: 996px;
        height: 432px;
        margin-top: 5px;
        margin-left: 0px;

        background: #FFFEF9;
        border-radius: 20px;
        overflow-y: scroll;
        .font-hash-h1{
            width: 891px;
            height: 34px;
            
            margin-top: 30px;
            margin-left: 53px;

            display: flex;
            justify-content: left;
            align-items: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-size: 25px;
            line-height: 38px;
            color: #B87514;
        }
        .font-state-box{
            width: 891px;
            height: 110px;

            margin-top: 16px;
            margin-left: 53px;

            border: 5px solid #ECBC76;
            border-radius: 20px;
        }  
    }
</style>