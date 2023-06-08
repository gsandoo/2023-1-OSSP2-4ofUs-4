import { createRouter, createWebHashHistory } from 'vue-router'
// 연결된 페이지들
import Home from './Home.vue'
import SignUpPage from './SignUpPage.vue'

import MyPage from './mypage/MyPage.vue'
import MyProfile from './mypage/Profile.vue'
import MyTimeTable from './mypage/TimeTable.vue'
import MyMatching from './mypage/Matching.vue'
import Report from './mypage/component/Report.vue'

import MatchingStartPage from './matchingpage/MatchingStartPage.vue'

import AdminHome from './adminpage/AdminHome.vue'

import Payments from './Payments.vue'
import Starting from './Starting.vue'

import ChatRoom from './ChatRoom.vue'
import paypay from './paypay.vue'

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
            path: '/my/timetable',
            component: MyTimeTable
        },
        {
            path: '/my/matching',
            component: MyMatching
        },
        {
            path: '/my/matching/report',
            component: Report
        },

        {
            path: '/matchingstart',
            component: MatchingStartPage
        },
        
        {
            path: '/admin',
            component: AdminHome
        },

        {
            path: '/Payments',
            component: Payments
        },
        {
            path: '/Starting',
            component: Starting
        }
        ,{
            path: '/ChatRoom',
            component: ChatRoom
        }
        ,{
            path: '/paypay',
            component: paypay
        }
    ]
})