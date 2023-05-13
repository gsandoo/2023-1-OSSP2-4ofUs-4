import { createRouter, createWebHashHistory } from 'vue-router'
// 연결된 페이지들
import Home from './Home.vue'
import SignUpPage from './SignUpPage.vue'
import MyPage from './mypage/MyPage.vue'
import MyProfile from './mypage/Profile.vue'
import MyHeart from './mypage/Heart.vue'
import MyTimeTable from './mypage/TimeTable.vue'
import MyMatching from './mypage/Matching.vue'

import Payments from './Payments.vue'
import Starting from './Starting.vue'

export default createRouter({
    // Hash 모드로 설정
    history: createWebHashHistory(),
    // 연결된 페이지들
    routes: [
        {
            path: '',
            component: Home
        },
        {
            path: '/signup',
            component: SignUpPage
        },
        {
            path: '/my',
            component: MyPage
        },
        {
            path: '/my/profile',
            component: MyProfile
        },
        {
            path: '/my/heart',
            component: MyHeart
        },
        {
            path: '/my/timetable',
            component: MyTimeTable
        },
        {
            path: '/my/matching',
            component: MyMatching
        },
        {
            path: '/Payments',
            component: Payments
        },
        {
            path: '/Starting',
            component: Starting
        }
    ]
})