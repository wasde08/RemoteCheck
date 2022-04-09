<template>
  <div>
<!--    <v-btn @click="getClient" style="position: absolute;top: 15%;left: 10%">-->
<!--      Send-->
<!--    </v-btn>-->
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
            <td>{{ item.client.id }}</td>
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
  <!--  <v-label>-->
  <!--    Public key: {{ clientData }} <br>-->

  <!--  </v-label>-->
</template>

<script>
import client from "../api/usrApi";

export default {
  data() {
    return {

      clients: [],

    }
  },
  created(){

  },
  methods: {
    getClient() {
      this.clients = []
      client.get().then(result => {
        result.data.forEach(data => {
          let obj = data
          client.getLast(obj.id).then(result2 => {
            this.clients.push({
              "last": result2.data,
              "client": obj
            })
          })
        })
      })
      setTimeout(this.getClient, 10000);
    }
  },
  beforeMount(){
    this.getClient()
  },
}
</script>
<style>

</style>