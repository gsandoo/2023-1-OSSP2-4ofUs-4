<template>
    <div>
        <button @click="loadPublicMatchingList()">모든 공강 매칭 불러오기</button>
        <button @click="loadClassMatchingList()">모든 수업 매칭 불러오기</button>
        <br>
        <input type="text" placeholder="Search 수업 By Matching Id" v-model="classMatchingIdForSearch">
        <button @click="searchClassMatchingId()">수업 매칭 검색</button>
        <br>
        <input type="text" placeholder="Search 공강 By Matching Id" v-model="publicMatchingIdForSearch">
        <button @click="searchPublicMatchingId()">공강 매칭 검색</button>
        <br>
        <input type="text" placeholder="Delete 수업 Matching By Id" v-model="classMatchingIdForDelete">
        <button @click="deleteClassMatchingByMatchingId()">입력한 매칭 번호에 대응되는 수업 매칭 삭제하기</button>
        <br>
        <input type="text" placeholder="Delete 공강 Matching By Id" v-model="publicMatchingIdForDelete">
        <button @click="deletePublicMatchingByMatchingId()">입력한 매칭 번호에 대응되는 공강 매칭 삭제하기</button>
        <br>
        <div v-for="(matching,index) in publicMatchingList" :key="index">
            {{matching}}
            <br>
        </div>
        <div v-for="(matching,index) in classMatchingList" :key="index">
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
                publicMatchingList: [],
                classMatchingList: [],

                classMatchingIdForSearch: '',
                publicMatchingIdForSearch: '',

                classMatchingIdForDelete: '',
                publicMatchingIdForDelete: '',
            }
        },
        methods:{
            // 전체 각 타입별 매칭 리스트 불러오기
            async loadPublicMatchingList(){
                try{
                    await axios.get('/admin/publicMatching')
                    .then((result)=>{
                        console.log("공강 매칭 리스트 모두 불러오기")
                        this.publicMatchingList = result.data
                        this.classMatchingList = []
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async loadClassMatchingList(){
                try{
                    await axios.get('/admin/classMatching')
                    .then((result)=>{
                        console.log("수업 매칭 리스트 모두 불러오기")
                        this.publicMatchingList = []
                        this.classMatchingList = result.data
                    })
                }catch(error){
                    console.log(error)
                }
            },
            // Ec2에 아래 두 개 api 업데이트가 아직 안 된 상황임
            // 매칭아이디로 타입별 매칭 검색하기
            async searchClassMatchingId(){
                try{
                    await axios.get('/matching/admin/classMatchedList',{
                        params:{
                            matchingId: this.classMatchingIdForSearch
                        }
                    }).then((result)=>{
                        this.publicMatchingList = result.data
                        this.classMatchingList = []
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async searchPublicMatchingId(){
                try{
                    await axios.get('/matching/admin/classMatchedList',{
                        params:{
                            matchingId: this.publicMatchingIdForSearch
                        }
                    }).then((result)=>{
                        this.publicMatchingList = []
                        this.classMatchingList = result.data
                    }).catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },

            // 매칭아이디로 타입별 매칭 삭제하기
            async deleteClassMatchingByMatchingId(){
                try{
                    await axios.get('/matching/delete/class',{
                        params:{
                            matchingId: this.classMatchingIdForDelete
                        }
                    })
                    .then((result)=>{
                        alert(result.data)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async deletePublicMatchingByMatchingId(){
                try{
                    await axios.get('/matching/delete/class',{
                        params:{
                            matchingId: this.publicMatchingIdForDelete
                        }
                    })
                    .then((result)=>{
                        alert(result.data)
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