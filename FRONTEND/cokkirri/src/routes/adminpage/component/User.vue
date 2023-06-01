<template>
    <div>
        <button @click="loadUserList()">PrintUserListToConsole</button>
        <br>
        <input type="text" placeholder="Search Id" v-model="searchId" @change="searchUserId()">
        <input type="text" placeholder="Search Name" v-model="searchName" @change="searchUserName()">
        <br>
        <input type="text" placeholder="Delete Id" v-model="deleteId">
        <button @click="deleteUserById()">입력된 Delete Id 에 해당하는 User 삭제 요청</button>
        <br>
        <div v-for="(user,index) in userList" :key="index">
            {{user}}
            <br>
        </div>
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
            // 모든 유저 정보 불러오기
            async loadUserList(){
                try{
                    await axios.get('/admin/user')
                    .then(result=>{
                        this.userList = result.data
                        console.log(result)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            // 유저를 아이디로 검색
            async searchUserId(){
                try{
                    await axios.get('/admin/user/id',{
                        params: {
                            userId: this.searchId
                        }
                    })
                    .then((result) =>{
                        // id 검색 결과는 {} 반환됨
                        this.userList = [result.data]
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
            // 유저를 이름으로 검색
            async searchUserName(){
                
                try{
                    await axios.get('/admin/user/name',{
                        params: {
                            userName: this.searchName
                        }
                    })
                    .then((result) =>{
                        // Name 검색 결과는 [{},...] 반환됨
                        this.userList = result.data
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
            // 유저의 아이디로 해당 유저 삭제
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