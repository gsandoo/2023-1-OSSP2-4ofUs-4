<template>
    <!-- 디자인 완료 -->
    <!-- 이후 버튼 동학에 따라 입력값을 보내도록 할 예정 -->
    <!--  튀어나온 걸 누르는 모양새도 있으면 좋겠음-->
    <div class="background-setting">
        <div class="container" >
            <div class="frame-body">
                <div class="font-h1">매칭</div>
                <div class="font-h2">#이런 친구는 어때요?</div>
                <div :style="{'display': 'flex','margin-top': '21px'}">
                    <div class="matching-mode-btn-1">수업</div>
                    <div class="matching-mode-btn-2">공강</div>
                </div>
                <div class="font-h3"># 몇 명과 함께 할까요?</div>
                <div :style="{'display': 'flex','margin-top': '15px'}">
                    <div class="matching-numset-btn-1">1명</div>
                    <div class="matching-numset-btn-2">3명</div>
                    <div class="matching-numset-btn-3">5명</div>
                </div>
                <div class="font-h4">* ‘동의합니다' 버튼을 누르면, 자동으로 매칭에 참여되며 매칭 수수료로 하트 2개가 소진됩니다. * 매칭 완료 시 자동으로 채팅방이 생성되며, 생성된 채팅방은 24시간동안 유지됩니다.</div>
                <div class="matching-submit-btn">동의합니다</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{

        }
    }
}
</script>

<style lang="scss" scoped>
    // 배경화면 설정
    .background-setting{
        height: 100vh;
        width: 100vw;
        margin:0;

        background-color: #FFFEF9;
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
        width: 100vw;
        height: 578px;
        background-color: #FFFEF9;
        display: flex;
        flex-direction: column; 
        align-items: center;
        justify-content: center;
        .font-h1{
            width: 408px;
            height: 60px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 75px;
            font-size: 50px;
            color: #B87514;
        }
        .font-h2{
            width: 375px;
            height: 34px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            line-height: 38px;
            font-size: 25px;
            color: #B87514;
        }
        .matching-mode-btn-1{
            width: 100px;
            height: 100px;
            
            background: #ECBC76;
            border-radius: 20px;

            float: left;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 45px;
            font-size: 30px;
            color: #000000;
        }
        .matching-mode-btn-2{
            width: 100px;
            height: 100px;
            
            margin-left: 54px;
            background: #ECBC76;
            border-radius: 20px;

            float: left;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 45px;
            font-size: 30px;
            color: #000000;
        }
        .font-h3{
            width: 375px;
            height: 34px;

            margin-top: 15px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            line-height: 38px;
            font-size: 25px;
            color: #B87514;
        }
        .matching-numset-btn-1{
            width: 100px;
            height: 100px;
            
            background: #ECBC76;
            border-radius: 20px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 45px;
            font-size: 30px;
            color: #000000;
        }
        .matching-numset-btn-2{
            width: 100px;
            height: 100px;
            
            margin-left: 54px;
            background: #ECBC76;
            border-radius: 20px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 45px;
            font-size: 30px;
            color: #000000;
        }
        .matching-numset-btn-3{
            width: 100px;
            height: 100px;
            
            margin-left: 54px;
            background: #ECBC76;
            border-radius: 20px;

            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            line-height: 45px;
            font-size: 30px;
            color: #000000;
        }
        .font-h4{
            width: 989px;
            height: 112px;

            margin-top: 0px;

            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            line-height: 38px;
            font-size: 25px;
            color: #B87514;
        }
        .matching-submit-btn{
            width: 400px;
            height: 60px;
            
            background: #B87514;
            border-radius: 50px;

            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;

            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;

            font-size: 32px;
            color: #FFFFFF;
            line-height: 24px;
            letter-spacing: 0.5px;
        }
    }
</style>