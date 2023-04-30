import { createRouter, createWebHashHistory } from 'vue-router'
// 연결된 페이지들
import Home from './Home.vue'

export default createRouter({
    // Hash 모드로 설정
    history: createWebHashHistory(),
    // 연결된 페이지들
    routes: [
        {
            path: '',
            component: Home
        }
    ]
})