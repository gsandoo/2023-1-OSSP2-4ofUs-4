<template>
    <div class="background-setting">
        <div class="container" >
            <div class="frame-body">
                <div class="font-h1">매칭</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style lang="scss" scoped>
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
        width: 990px;
        height: 578px;
        background-color: #FFFEF9;
        display: grid;
        justify-content: center;
        

        .font-h1{
            width: 408px;
            height: 60px;
            margin-left: 28px;
            display: block;
            text-align: center;
            align-items: center;

            font-size: 50px;
            color: #B87514;
        }
    }
</style>