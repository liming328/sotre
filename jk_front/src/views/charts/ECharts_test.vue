<template>
    <Card>
        <div id="barChartsDiv"></div>
    </Card>
</template>

<script>
  /*  import echarts from 'echarts';*/
    var echarts = require('echarts');

            export default {
                name: 'ECharts_test',
                data:{},
                methods:{},
                mounted:function () {
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('barChartsDiv'));
                    // 绘制图表
                    myChart.setOption({

                        title: {
                            text: 'ECharts入门'
                        },
                        tooltip: {},
                        xAxis: {
                            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'bar',
                            data: [5, 20, 36, 10, 10, 20]
                        }]
                    });
                }

            }

</script>
<style scoped>
    #barChartsDiv{
        width: 50%;
        height: 500px;
    }
</style>
