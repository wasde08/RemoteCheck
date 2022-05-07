import Vue from 'vue'
const reg = Vue.resource('/clients');
const reg2 = Vue.resource('/records/lastRecord/{id}');
const reg3 = Vue.resource('/records/all/{id}');
const reg4 = Vue.resource('/tasks/newTask');
const reg5 = Vue.resource('/clients/sendTelMess/{mess}');
const reg6 = Vue.resource('/clients/getImage/{id}');



export default {
    get: () => reg.get(),
    getLast: id => reg2.get({id}),
    getAllByID: id => reg3.get({id}),
    addTask: task => reg4.save({}, task),
    sendTel: mess => reg5.get({mess}),
    getImage: id => reg6.get({id}),

}