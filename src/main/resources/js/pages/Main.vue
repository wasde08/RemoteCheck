<template>
  <div>
    <div>
      <v-alert
          v-model="alertFlag"
          border="left"
          close-text="Close Alert"
          color="deep-purple accent-4"
          dark
          dismissible
      >
        Сервер упал
      </v-alert>
    </div>


    <template>
      <v-simple-table>
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">
              Id
            </th>
            <th class="text-left">
              Key
            </th>
            <th class="text-left">
              Status
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in clients"
              :key="item.client.id"

          >
            <td @click="goTobyPage(item.client.id)">{{ item.client.id }}</td>
            <td>{{ item.client.key }}</td>
            <td>
              <div v-if="(new Date().getTime() - Date.parse(item.last.timestamp))<60000">
                On
              </div>
              <div v-else>
                Off
              </div>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>
    </template>

  </div>

</template>

<script>
import client from "../api/usrApi";

export default {
  data() {
    return {

      clients: [],
      alertFlag: false,
      alerts : new Map(),

    }
  },
  created() {

  },
  methods: {
    getClient() {
      this.clients = []
      client.get().then(result => {
        result.data.forEach(data => {
          let obj = data
          client.getLast(obj.id).then(result2 => {
            let x = new Date().getTime()
            if (x - Date.parse(result2.data.timestamp) < 70000) {
              if (x - Date.parse(result2.data.timestamp) > 60000) {
                this.alertFlag = true
                client.sendTel(obj.id)
              }
            }
            this.clients.push({
              "last": result2.data,
              "client": obj
            })
          })
        })
      })
      setTimeout(this.getClient, 10000);
    },
    goTobyPage(id) {
      this.$router.push('/server/' + id)
    },
  },
  beforeMount() {
    this.getClient()
  },
}
</script>
<style>

</style>