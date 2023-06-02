import { createStore } from 'vuex';
import axios from "../api/index.js";
// vuex의 mutations 및 action 에서 주소관리를 하기 위해서 가져옴

import router from '../routes/index.js';
// 모듈 불러오기


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
        studentNum: null,
        course: [],
        password: null,
        heart: null,

        usageHistory: [],

        notification: null,

        publicMatchingRecord: [],
        classMatchingRecord: [],
    },
    mutations: {
        // 사용자 매칭 정보 불러오기
        publicSave(state, record){
            state.publicMatchingRecord = record
            console.log(state.publicMatchingRecord)
        },
        classSave(state, record){
            state.classMatchingRecord = record
            console.log(state.classMatchingRecord)
        },

        //하트 사용내역 불러오기
        setUsageHistory(state, history) {
            state.usageHistory = history;
        },



        // 로그인 적용 후 ~ 페이지로 이동. 추후 메인 페이지로 이동 변경 예정.
        loginSuccess(state, payload) {
            state.isLogin = true
            state.id = payload
            router.replace('/Starting')
        },
        userInfoApply(state, {major, name, number, sex, studentNum, course, password, heart}){
            state.major = major
            state.name = name
            state.number = number
            state.sex = sex
            state.studentNum = studentNum
            state.course = course
            state.password = password
            state.heart = heart
        },
        logout(state) { 
            state.isLogin = false
            state.id = null
        },
        SET_NOTIFICATION(state, notification){
            console.log("들어온 신호"+notification)
            state.notification = notification
            console.log("저장한 신호"+state.notification)
        },

        // 사용내역 업데이트
        updateUsageHistory(state, newHistory) {
            state.usageHistory = newHistory;
        },
    },
    actions: {
        // 매칭 정보 불러오기
        async callRecord({commit, state}) {
            try{
                await axios.get('/userMypage/publicMatchedList',{
                    params:{
                        userId: state.id
                }}).then((result)=>{
                    commit('publicSave',result.data)
                }).catch(function(error){
                    console.log(error)
                })
            } catch(error){
                console.log(error)
            }
            try{
                await axios.get('/userMypage/classMatchedList',{
                    params:{
                        userId: state.id
                }}).then((result)=>{
                    commit('classSave',result.data)
                }).catch(function(error){
                    console.log(error)
                })
            } catch(error){
                console.log(error)
            }
        },

        // 각 컴포넌트에서 this.$store.dispatch('메소드 이름', { 데이터 변수: 입력값 }) 형식으로 사용 가능
        // 로그인 요청 및 store 정보 업데이트
        async loginRequest({commit, dispatch}, {inputId, inputPassword}){
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
                            dispatch('userInfoUpdate')
                            dispatch('callRecord')
                            alert('로그인 되었습니다.')
                        }
                        else{
                            alert('아이디 및 비밀번호에 대응되는 회원 정보가 없습니다.')
                        }
                    }
                }).catch(function(error){
                    console.log(error);
                });
            } catch(error){
                console.log(error);
            }
            dispatch('subscribeToSse')
        },
        // vuex의 state.id 기반으로 현재 유저의 정보를 업데이트한다.
        async userInfoUpdate({state, commit}){
            try{
                await axios.get('/admin/user/id',{
                        params: {
                            userId: state.id
                        }
                    })
                    .then((result) =>{
                        if(result.status === 200){
                            commit('userInfoApply', {
                                major: result.data.major,
                                name: result.data.name,
                                number: result.data.number,
                                sex: result.data.sex,
                                studentNum: result.data.studentNum,
                                course: result.data.course,
                                password: result.data.password,
                                heart: result.data.heart
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
        },
        //마이페이지에서 유저 정보 수정
        async userInfoEdit({dispatch}, {inputId, inputMajor, inputNumber, inputCourse, inputPassword}){
            try{
                await axios.put('/userMypage/'+inputId, null, {
                    params: {
                        password: inputPassword,
                        major: inputMajor,
                        number: inputNumber,
                        course: inputCourse.map(encodeURIComponent).join(',')
                    }
                })
                .then((result)=>{
                    if(result.status === 200){
                        dispatch('userInfoUpdate')
                        alert("수정 완료")
                    }
                })
                .catch(function(error){
                    console.log(error)
                })
            } catch(error){
                console.log(error)
            }
        },

        subscribeToSse({ state }) {
            let eventSource = new EventSource('http://3.37.37.164:8080/subscribe/' + state.id);
        
            eventSource.addEventListener("sse",(event)=>{
                console.log(event.data)
                //commit('SET_NOTIFICATION', event);
            });
            
            eventSource.onerror = error => {
                console.error('SSE connection error', error);
                if (eventSource.readyState === EventSource.CLOSED) {
                    eventSource = new EventSource('http://3.37.37.164:8080/subscribe/' + state.id);
                }
                else{
                    console.log("sse 연결된 상태입니다. 하지만 sse 응답 에러가 발생했습니다.")
                }
            }; 
            
        },

        async fetchUsageHistory({ commit }) {
            try {
              const response = await axios.get('/admin/user/payment'); // 적절한 API 엔드포인트로 변경
              const history = response.data; // 가져온 데이터를 적절히 가공하여 history 변수에 저장
                commit('setUsageHistory', history);
            } catch (error) {
                console.log(error);
            }
        }
    },
})
