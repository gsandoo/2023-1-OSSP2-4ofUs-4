import { createApp } from 'vue'
import App from './App.vue'
// path와 컴포넌트 정보 가져오기
import router from './routes/index.js'
// 상태 관리 라이브러리 vuex 를 위한 연결
import store from "./store/index.js"

createApp(App)
    .use(store)
    .use(router)
    .mount('#app')

