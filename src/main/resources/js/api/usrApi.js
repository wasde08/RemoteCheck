import Vue from 'vue'
const reg = Vue.resource('/clients');
const reg2 = Vue.resource('/records/lastRecord/{id}');



export default {
    get: () => reg.get(),
    getLast: id => reg2.get({id})

}