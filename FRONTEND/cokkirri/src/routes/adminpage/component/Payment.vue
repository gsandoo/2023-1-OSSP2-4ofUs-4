<template>
    <div>
        <button @click="loadPaymentList()">결제내역 전체 반환</button>
        <input type="text" placeholder="Id 결제내역 조회" v-model="SearchingId" @change="searchPaymentById()">
    </div>
</template>

<script>
    import axios from '../../../api/index.js'
    export default {
        data(){
            return{
                paymentList: [],
                SearchingId: '',
            }
        },
        methods:{
            async loadPaymentList(){
                try{
                    await axios.get('/admin/payment')
                    .then((result)=>{
                        console.log("결제내역 출력")
                        console.log(result)
                    })
                    .catch(function(error){
                        console.log(error)
                    })
                }catch(error){
                    console.log(error)
                }
            },
            async searchPaymentById(){
                try{
                    await axios.get('/admin/user/payment',{
                        params:{
                            userId: this.SearchingId
                        }
                    })
                    .then((result)=>{
                        console.log(this.SearchingId+"의 결제내역 조회 요청")
                        console.log(result)
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