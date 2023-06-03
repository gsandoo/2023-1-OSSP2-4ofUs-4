<template>
    <div class="frame-body">
        <!---
            matchingType 신고된 매칭의 타입
            matchingId 신고된 매칭의 id
            email 신고자
            title 신고 제목
            comment 신고 내용
        --->
        
        노쇼 신고

        <!---
        matchingType
        상위 컴포넌트에서 가져온 값 예시 : free 또는 class
        matchingId
        위 컴포넌트에서 가져온 값 예시 : 1~
        위의 항목은 상위 컴포넌트에서만 조작 가능
        신고 취소 버튼
        신고자는 자동으로 처리됨
        신고 제목
        신고 내용
        제출하기
        --->
    </div>
</template>

<script>
export default {
    data(){
        return{
            isOpen: false
        }
    },
    methods:{
        open(){
            this.isOpen = true
        },
        close(){
            this.isOpen = false
        }
    }
}
</script>

<style lang="scss" scoped>
    .frame-body{
        width: 600px;
        height: 750px;

        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;

        background-color: #ECBC76; 
        
    }
</style>