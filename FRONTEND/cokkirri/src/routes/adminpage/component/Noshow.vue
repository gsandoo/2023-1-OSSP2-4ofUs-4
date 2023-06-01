<template>
    <div>
        <button @click="loadClassNoshow()">수업 노쇼 조회</button>
        <button @click="loadPublicNoshow()">공강 노쇼 조회</button>
        <br>
        <button @click="loadReportedClassNoshow()">수업 노쇼 신고 내역 조회</button>
        <button @click="loadReportedPublicNoshow()">공강 노쇼 신고 내역 조회</button>
        <br>
        <input type="text" placeholder="수업 매칭아이디" v-model="classMatchingIdForNoshowRecord">
        <button @click="searchReportedClassNoshow()">수업 노쇼 신고 내역 매칭아이디로 조회</button>
        <br>
        <input type="text" placeholder="공강 매칭아이디" v-model="publicMatchingIdForNoshowRecord">
        <button @click="searchReportedPublicNoshow()">공강 노쇼 신고 내역 매칭아이디로 조회</button>
        <br>
        <input type="text" placeholder="수업 매칭아이디" v-model="classMatchingIdForNoshowResister">
        <input type="text" placeholder="수업 아이디" v-model="classIdForNoshowResister">
        <button @click="resisterClassNoshow()">수업 노쇼 등록</button>
        <br>
        <input type="text" placeholder="공강 매칭아이디" v-model="publicMatchingIdForNoshowResister">
        <input type="text" placeholder="공강 아이디" v-model="publicIdForNoshowResister">
        <button @click="resisterPublicNoshow()">공강 노쇼 등록</button>
        <br>
        <div v-for="(matching,index) in classNoshow" :key="index">
            {{matching}}
            <br>
        </div>
        <div v-for="(matching,index) in publicNoshow" :key="index">
            {{matching}}
            <br>
        </div>        
    </div>
</template>

<script>
import axios from '../../../api/index.js'
export default {
    data(){
        return{
            classNoshow: [],
            publicNoshow: [],

            classMatchingIdForNoshowRecord: '',
            publicMatchingIdForNoshowRecord: '',

            classMatchingIdForNoshowResister: '',
            publicMatchingIdForNoshowResister: '',
            classIdForNoshowResister: '',
            publicIdForNoshowResister: '',
        }
    },
    methods:{
        // 타입별 노쇼 매칭 조회(구체적인 테스트 필요)
        async loadClassNoshow(){
            try{
                await axios.get('/matching/get/noshow/public')
                .then((result)=>{
                    this.classNoshow = result.data
                    this.publicNoshow = []
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
        async loadPublicNoshow(){
            try{
                await axios.get('/matching/get/noshow/class')
                .then((result)=>{
                    this.classNoshow = []
                    this.publicNoshow = result.data
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
        // 타입별 노쇼 신고 조회(구체적인 테스트 필요)
        async loadReportedClassNoshow(){
            try{
                await axios.get('matching/get/accusation',{
                    params:{
                        matchingType: "class"
                    }
                })
                .then((result)=>{
                    this.classNoshow = result.data
                    this.publicNoshow = []
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
                    this.classNoshow = []
                    this.publicNoshow = result.data
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
        // 타입별 메칭 아이디 노쇼 신고 조회
        async searchReportedClassNoshow(){
            try{
                await axios.get('/matching/get/classmatch/accusation',{
                    params:{
                        matchingId: this.classMatchingIdForNoshowRecord,
                        matchingType: "class"
                    }
                })
                .then((result)=>{
                    // 특이하게 dara가 Array 형태로 반환되지 않음. 이거 확인 필요함. 오류 유의.
                    console.log(result)
                    this.classNoshow = result.data
                    this.publicNoshow = []
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
                        matchingId: this.publicMatchingIdForNoshowRecord,
                        matchingType: "free"
                    }
                })
                .then((result)=>{
                    // 특이하게 data가 Array 형태로 반환되지 않음. 이거 확인 필요함. 오류 유의.
                    console.log(result)
                    this.classNoshow = []
                    this.publicNoshow = result.data
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
        // 타입별 노쇼 등록
        async resisterClassNoshow(){
            alert(this.classMatchingIdForNoshowResister)

            try{
                await axios.post('/matching/post/noshow/public',{

                        matchingId: this.classMatchingIdForNoshowResister,
                        email: this.classIdForNoshowResister,
                        matchingType: "class"

                })
                .then((result)=>{
                    console.log(result)
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
        async resisterPublicNoshow(){
            try{
                await axios.post('/matching/post/noshow/class',{

                        matchingId: this.publicMatchingIdForNoshowResister,
                        email: this.publicIdForNoshowResister,
                        matchingType: "free"
                })
                .then((result)=>{
                    console.log(result)
                }).catch(function(error){
                    console.log(error)
                })
            }catch(error){
                console.log(error)
            }
        },
    },
}
</script>

<style lang="scss" scoped>

</style>