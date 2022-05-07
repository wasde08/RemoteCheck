import Vue from 'vue'
import VueRouter from 'vue-router'


import Main from "../pages/Main.vue";
import MonitorPage from "../pages/MonitorPage.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Main},
    {path: '/server/:id', component: MonitorPage},
]

export default new VueRouter({
    mode: 'history',
    routes
})