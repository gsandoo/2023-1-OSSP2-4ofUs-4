<template>
    <div class="container">
        <!---
            matchingType 신고된 매칭의 타입
            matchingId 신고된 매칭의 id
            email 신고자
            title 신고 제목
            comment 신고 내용
        --->

        <button @click="reportNoshow()">노쇼 신고</button>
        <div>
            신고자 : {{email}}
        </div>
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
    import axios from '../../../api/index.js'
    export default {
        
        data(){
            return{
                matchingType: 'free',
                matchingId: '1',
                email: '',
                title: '노쇼 신고',
                comment: '제곧내',
            }
        },
        methods:{
            async reportNoshow(){
                try{
                    await axios.post('matching/post/accusation',{
                        matchingType: this.matchingType,
                        matchingId: this.matchingId,
                        email: this.email,
                        title: this.title,
                        comment: this.comment
                    }).then((result)=>{
                        console.log(result)
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            close(){
                window.close()
            }
        },
        mounted() {
            this.email = this.$route.query.email;
        },
    }
</script>

<style lang="scss" scoped>
    .container{
        width: 100vw;
        height: 100vh;

        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;

        background-color: #ECBC76; 
        
        .frame-main{

        }
    }
</style>