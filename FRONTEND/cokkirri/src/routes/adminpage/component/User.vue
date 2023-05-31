<template>
    <div>
        <button @click="loadUserList()">PrintUserListToConsole</button>
        <input type="text" placeholder="Search Id" v-model="searchId" @change="searchUserId()">
        <input type="text" placeholder="Search Name" v-model="searchName" @change="searchUserName()">
        <input type="text" placeholder="Delete Id" v-model="deleteId">
        <button @click="deleteUserById()">입력된 Delete Id 에 해당하는 User 삭제 요청</button>
    </div>
</template>

<script>
    import axios from '../../../api/index.js'
    export default {
        data(){
            return{
                userList: [],
                searchId: '',
                searchName: '',
                deleteId: '',
            }
        },
        methods:{
            async loadUserList(){
                try{
                    await axios.get('/admin/user')
                    .then(result=>{
                        console.log(result)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async searchUserId(){
                try{
                    await axios.get('/admin/user/id',{
                        params: {
                            userId: this.searchId
                        }
                    })
                    .then((result) =>{
                        // id 검색 결과는 {} 반환됨
                        console.log("Id 검색결과")
                        console.log(result)
                    })
                    .catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async searchUserName(){
                
                try{
                    await axios.get('/admin/user/name',{
                        params: {
                            userName: this.searchName
                        }
                    })
                    .then((result) =>{
                        // Name 검색 결과는 [{},...] 반환됨
                        console.log("Name 검색결과")
                        console.log(result)
                    })
                    .catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }    
            },
            async deleteUserById(){
                try{
                    await axios.delete('/admin/user/'+this.deleteId)
                    .then((result)=>{
                        console.log("상태: "+result.status+", "+this.deleteId+"제거 요청 완료")
                    })
                    .catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            }
        }
    }
</script>


<style lang="scss" scoped>

</style>