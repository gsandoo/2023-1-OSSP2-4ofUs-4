<template>
    <!---모든 주소 정보를 RouterView로 가져왔음--->
    <div>
    <RouterView />
    </div>
</template>

<style lang="scss">
    // 배경 화면 설정
    body {
        background: url("./assets/login/background.jpg") no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        display: grid;
        grid-template-rows: auto;
        justify-items: center;
        align-items: center;
    }
    body, html {
        margin: 0;
        height: 100%;
    }
</style>