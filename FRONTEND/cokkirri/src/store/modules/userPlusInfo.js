import axios from '../../api/index.js'
export default{
    namespaced: true,

    state: {
        publicMatchingRecord: '',
        classMatchingRecord: '',
    },
    mutations: {
        publicSave(state, record){
            console.log("공강 매칭 불러오기 완료")
            state.publicMatchingRecord = record
            console.log("공강 매칭 불러오기 완료")
        },
        classSave(state, record){
            console.log("수업 매칭 불러오기 완료")
            state.classMatchingRecord = record
            console.log("수업 매칭 불러오기 완료")
        },
    },
    actions: {
        async callRecord({commit, rootstate}) {
            try{
                await axios.get('/userMypage/publicMatching',{
                    params:{
                        userId: rootstate.id
                }}).then((result)=>{
                    console.log(result.data)
                    commit('publicSave',result.data)
                }).catch(function(error){
                    console.log(error)
                })
            } catch(error){
                console.log(error)
            }
            try{
                await axios.get('/userMypage/classMatching',{
                    params:{
                        userId: rootstate.id
                }}).then((result)=>{
                    console.log(result.data)
                    commit('publicSave',result.data)
                }).catch(function(error){
                    console.log(error)
                })
            } catch(error){
                console.log(error)
            }
        }
    }
}