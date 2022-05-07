<template>
  <div>
    <div>
      IP: {{ip}}
    </div>
    <div>
      <v-dialog
          v-model="dialogFlag"
          width="500"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
              style="width: 120px"
              v-bind="attrs"
              v-on="on"
          >
            Команды
          </v-btn>
        </template>

        <v-card>
          <v-card-title class="headline grey lighten-2">
            Отправка команды
          </v-card-title>
          <v-form>
            <v-text-field
                v-model="commandText"
                label="Введите команду"
                outlined
            ></v-text-field>
          </v-form>
          <v-card-actions class="justify-end">
            <v-btn @click="sendCommand(commandText)">
              Отправить
            </v-btn>
            <v-btn @click="dialogFlag=false">
              Отмена
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <v-btn @click="getData"> Обновить данные</v-btn>

    <LineChartGenerator v-if="loaded" :chart-data="chartData"
                        :chart-options="chartOptions"
                        :chart-id="chartId"
                        :dataset-id-key="datasetIdKey"
                        :plugins="plugins"
                        :css-classes="cssClasses"
                        :styles="styles"
                        :width="width"
                        :height="height"
    />
    <div>
      <v-btn @click="getImage"> image</v-btn>
      <img :src = img>
    </div>
  </div>


</template>

<script>

import client from "../api/usrApi";
import {Line as LineChartGenerator} from 'vue-chartjs/legacy'

import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  CategoryScale,
  PointElement
} from 'chart.js'

ChartJS.register(
    Title,
    Tooltip,
    Legend,
    LineElement,
    LinearScale,
    CategoryScale,
    PointElement
)

export default {
  name: "MonitorPage",
  components: {
    LineChartGenerator
  },
  props: {
    chartId: {
      type: String,
      default: 'line-chart'
    },
    datasetIdKey: {
      type: String,
      default: 'label'
    },
    width: {
      type: Number,
      default: 100
    },
    height: {
      type: Number,
      default: 300
    },
    cssClasses: {
      default: '',
      type: String
    },
    styles: {
      type: Object,
      default: () => {
      }
    },
    plugins: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      id: null,
      ip: "",
      img: "",
      cpu: [],
      commandText: "",
      dialogFlag: false,
      loaded: false,
      chartData: {
        labels: [],
        datasets: [
          {
            label: 'CPU',
            backgroundColor: '#f87979',
            data: []
          },
          {
            label: 'RAM',
            backgroundColor: '#1a4ccb',
            data: []
          },
          {
            label: 'SSD',
            backgroundColor: '#4fcb1a',
            data: []
          }
        ]
      },

      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },

  created() {
    this.id = this.$route.params.id;
    this.getImage()
  },
  methods: {

    async getData() {
      this.loaded = false
      this.chartData.labels = []
      this.chartData.datasets[0].data = []
      try {
        await client.getAllByID(this.$route.params.id).then(result => {
          let i = 0
          result.data.forEach(data => {
            this.chartData.labels.push(data.timestamp.toString().split("T")[1].split(".")[0])
            this.chartData.datasets[0].data.push(data.cpu)
            this.chartData.datasets[1].data.push(data.ram)
            this.chartData.datasets[2].data.push(data.ssd)
            i = i + 1
          })
          this.ip = result.data[result.data.length - 1].ip
          this.chartData.labels = this.chartData.labels.slice(-30, -1)
          for (let j = 0; j < 3; j++) {
            this.chartData.datasets[j].data = this.chartData.datasets[j].data.slice(-30, -1)
          }
        })
        this.loaded = true
      } catch (e) {
        console.error(e)
      }

    },
    sendCommand(command) {
      this.commandText = ""
      const task = {
        command: command,
        status: false,
        clients_id: {
          id: this.id
        }
      }
      client.addTask(task).then(result => {
        if (result.status == 200) {
          alert("Успешно")
          this.dialogFlag = false
        }
      }).catch(error => {
        alert("Ошибка " + error.status)
        this.dialogFlag = false
      })
    },
    getImage(){
      client.getImage(this.id).then(result =>{
        this.img = "data:image/png;base64," + result.data.img.slice(2,-1)
      }).catch(error => {
        alert("Ошибка " + error.status)
      })
    }

  },

  async mounted() {
    this.loaded = false
    try {
      await client.getAllByID(this.$route.params.id).then(result => {
        let i = 0

        result.data.forEach(data => {
          this.chartData.labels.push(data.timestamp.toString().split("T")[1].split(".")[0])
          this.chartData.datasets[0].data.push(data.cpu)
          this.chartData.datasets[1].data.push(data.ram)
          this.chartData.datasets[2].data.push(data.ssd)
          i = i + 1
        })
        this.ip = result.data[result.data.length - 1].ip
        this.chartData.labels = this.chartData.labels.slice(-30, -1)
        for (let j = 0; j < 3; j++) {
          this.chartData.datasets[j].data = this.chartData.datasets[j].data.slice(-30, -1)
        }
      })
      this.loaded = true
    } catch (e) {
      console.error(e)
    }
  }
}
</script>

<style scoped>

</style>