import Vue from 'vue'
// import Vuetify from 'vuetify'

import vuetify from "./router/vuetify"
import 'api/resource.js'
import router from 'router/router.js'
import App from 'pages/app.vue'
import 'vuetify/dist/vuetify.min.css'

// Vue.use(Vuetify)
/*"vuetify": "1.5.16"*/

new Vue({
    el: '#app',
    router,
    vuetify,
    //vuetify: new Vuetify(),
    render: a => a(App)
})