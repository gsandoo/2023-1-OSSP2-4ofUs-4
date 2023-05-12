import { createStore } from 'vuex'
// vuex 를 사용하여 로그인 상태와 로그인 id 를 저장
export default createStore({
    state: {
        userId: null,
        isLogin: false
    },
    mutations: {
        loginSuccess(state, payload) {
            state.isLogin = true
            state.userId = payload
        },
        logout(state) { 
            state.isLogin = false
            state.userId = null
        }
    },
    actions: {},
    modules: {}
})