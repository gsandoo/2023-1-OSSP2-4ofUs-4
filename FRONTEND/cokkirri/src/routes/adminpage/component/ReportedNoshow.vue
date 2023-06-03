<template>
    <div>
        신고 내역
    </div>
</template>

<script>
    import axios from '../../../api/index.js'
    export default {
        data(){
            return{
                classReportedNoshow: [],
                publicReportedNoshow: [],

                matchingIdForNoshowRecord: '',
            }
        },
        methods:{
            // 타입별 노쇼 신고 조회(구체적인 테스트 필요)
            loadReportedNoshow(){
                this.loadReportedClassNoshow()
                this.loadReportedPublicNoshow()
            },
            async loadReportedClassNoshow(){
                try{
                    await axios.get('matching/get/accusation',{
                        params:{
                            matchingType: "class"
                        }
                    })
                    .then((result)=>{
                        this.classReportedNoshow = result.data
                        //this.publicReportedNoshow = []
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async loadReportedPublicNoshow(){
                try{
                    await axios.get('matching/get/accusation',{
                        params:{
                            matchingType: "free"
                        }
                    })
                    .then((result)=>{
                        //this.classReportedNoshow = []
                        this.publicReportedNoshow = result.data
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            // 타입별 메칭 아이디 노쇼 신고 조회
            searchReportedNoshow(){
                this.searchReportedClassNoshow()
                this.searchReportedPublicNoshow()
            },
            async searchReportedClassNoshow(){
                try{
                    await axios.get('/matching/get/classmatch/accusation',{
                        params:{
                            matchingId: this.matchingIdForNoshowRecord,
                            matchingType: "class"
                        }
                    })
                    .then((result)=>{
                        // 특이하게 dara가 Array 형태로 반환되지 않음. 이거 확인 필요함. 오류 유의.
                        console.log(result)
                        this.classReportedNoshow = result.data
                        //this.publicReportedNoshow = []
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async searchReportedPublicNoshow(){
                try{
                    await axios.get('/matching/get/publicmatch/accusation',{
                        params:{
                            matchingId: this.matchingIdForNoshowRecord,
                            matchingType: "free"
                        }
                    })
                    .then((result)=>{
                        // 특이하게 data가 Array 형태로 반환되지 않음. 이거 확인 필요함. 오류 유의.
                        console.log(result)
                        //this.classReportedNoshow = []
                        this.publicReportedNoshow = result.data
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
        }
    }
</script>