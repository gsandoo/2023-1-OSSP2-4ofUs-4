import { createStore } from 'vuex'
import axios from "../api/index.js";
// vuex의 mutations 및 action 에서 주소관리를 하기 위해서 가져옴
import router from '../routes/index.js'
// vuex 를 사용하여 로그인 상태와 로그인 id 를 저장
export default createStore({
    // 컴포넌트에서 해당 state의 값을 불러오고자 한다면, this.$store.state.~ 형식으로 불러올 수 있다.
    // 예시 : this.$store.state.id
    state: {
        id: null,
        isLogin: false,

        major: null,
        name: null,
        number: null,
        sex: null,
        studentNum: null
    },
    mutations: {
        // 로그인 적용 후 ~ 페이지로 이동. 추후 메인 페이지로 이동 변경 예정.
        loginSuccess(state, payload) {
            state.isLogin = true
            state.id = payload
            router.replace('/Starting')
        },
        userInfoApply(state, {major, name, number, sex, studentNum}){
            state.major = major
            state.name = name
            state.number = number
            state.sex = sex
            state.studentNum = studentNum
        },
        logout(state) { 
            state.isLogin = false
            state.id = null
        }
    },
    actions: {
        // 각 컴포넌트에서 this.$store.dispatch('메소드 이름', { 데이터 변수: 입력값 }) 형식으로 사용 가능
        // 로그인 요청 및 store 정보 업데이트
        async loginRequest({commit}, {inputId, inputPassword}){
            // 로그인 api 요청 부분. 반환값에 토큰 없음.
            try{
                await axios.post('/login', null, {
                    params: {
                        id: inputId,
                        password: inputPassword,
                    }
                })
                .then((result) => {
                    if(result.status === 200){
                        if(result.data === true){
                            commit('loginSuccess', inputId);
                            this.dispatch('userInfoUpdate')
                            alert('로그인 되었습니다.')
                        }
                        else{
                            alert('아이디 밒 비밀번호에 대응되는 회원 정보가 없습니다.')
                        }
                    }
                }).catch(function(error){
                    console.log(error);
                });
            } catch(error){
                console.log(error);
            }
        },
        // vuex의 state.id 기반으로 현재 유저의 정보를 업데이트한다.
        async userInfoUpdate(){
            try{
                await axios.get('/admin/user/id',{
                        params: {
                            userId: this.state.id
                        }
                    })
                    .then((result) =>{
                        if(result.status === 200){
                            this.commit('userInfoApply', {
                                major: result.data.major,
                                name: result.data.name,
                                number: result.data.number,
                                sex: result.data.sex,
                                studentNum: result.data.studentNum
                            })
                            console.log("유저 정보 업데이트 완료")
                        }
                        else{
                            console.log("response가 200이 아님")
                        }
                    })
                    .catch(function(error){
                        console.log(error)
                    })
            } catch(error){
                console.log(error);
            }
        }

    },
    modules: {}
})