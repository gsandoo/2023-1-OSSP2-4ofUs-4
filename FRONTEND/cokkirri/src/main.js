import { createApp } from 'vue'
import App from './App.vue'
// path와 컴포넌트 정보 가져오기
import router from './routes/index.js'

createApp(App)
    .use(router)
    .mount('#app')