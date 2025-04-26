<template>
  <div>
    <el-row :gugger="32">
      <el-card  shadow="never">
        <el-col  style="margin-left:10%" :md="6" :sm="8">
          <el-date-picker
            v-model="dateTime"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :picker-options="pickerOptions"
            @change="getHotSearchWord"
          />
        </el-col>
        <br>
        <div class="chart-wrapper" style="margin-top:20px">
          <bar-chart  ref="barChart" />
        </div>

      </el-card>

    </el-row>

  </div>
</template>
<script>

import moment from 'moment'
import BarChart from '../../dashboard/BarChart'
import {getDeptCouns} from  '@/api/panda/statistic'
export default {
  name:'count',
  components: { BarChart },
  data(){
    return  {
      dateTime:undefined,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      queryDeptCounts:{
        startTime:undefined,
        endTime:undefined
      },
      dateStartFormat: 'YYYY-MM-DD 00:00:00',
      dateEndFormat: 'YYYY-MM-DD 23:59:59'
    }
  },
  methods:{
    getHotSearchWord() {
      this.queryDeptCounts.startTime = moment(this.dateTime[0]).format(this.dateStartFormat)
      this.queryDeptCounts.endTime = moment(this.dateTime[1]).format(this.dateEndFormat)

      getDeptCouns(this.queryDeptCounts).then(response => {
        this.$refs.barChart.initChart(response)
      })
    },
    initDeptCountChart(){
      getDeptCouns({ }).then(response => {
        this.$refs.barChart.initChart(response)
      })
    }
  },
  created() {
    this.initDeptCountChart();
  }
}
</script>
