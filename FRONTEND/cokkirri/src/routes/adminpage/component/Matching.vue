<template>
    <div>
        <button @click="loadPublicMatchingList()">모든 공강 매칭 불러오기</button>
        <button @click="loadClassMatchingList()">모든 수업 매칭 불러오기</button>
        <!--- api 없음.
        <input type="text" placeholder="Search Id" v-model="searchId">
        --->
        <input type="text" placeholder="Delete Matching By Id" v-model="deleteId">
        <button @click="DeleteMatchingByMatchingId()">입력한 매칭 번호에 대응되는 매칭 삭제하기</button>
        
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

                searchId: '',
                deleteId: '',
            }
        },
        methods:{
            async loadPublicMatchingList(){
                try{
                    await axios.get('/admin/publicMatching')
                    .then((result)=>{
                        console.log("공강 매칭 리스트 모두 불러오기")
                        console.log(result)
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
                        console.log(result)
                        this.PublicMatchingList = []
                        this.classMatchingList = result.data
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async DeleteMatchingByMatchingId(){
                try{
                    await axios.get('/matching/delete/class',{
                        params:{
                            matchingId: this.deleteId
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