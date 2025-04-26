<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 6000

export default {
  name:'BarChart',
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '600px'
    },
    height: {
      type: String,
      default: '600px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    // this.$nextTick(() => {
    //   this.initChart()
    // })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart(responseData) {

      //
      var deptNamesArr=[];
      var detpCount=[];

      if(responseData.data.length!=0){
         var getList=responseData.data;
        for (let i = 0; i < getList.length; i++) {
            var each=getList[i];
            deptNamesArr.push(each.name);
            detpCount.push(each.number);
        }
      }

      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: deptNamesArr,
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          name: '就诊人',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: detpCount,
          animationDuration
        }
        ]
      })
    }
  }
}
</script>
