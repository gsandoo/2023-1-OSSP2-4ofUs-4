import { createApp } from 'vue'
import App from './App.vue'
import router from './routes/index.js'
import store from "./store/index.js"
import axios from 'axios'

const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')
app.config.globalProperties.$http = axios

