<template>
    <Row>
        <div id="factorySale" class="echarts"></div>
    </Row>
</template>

<script>
    /*import echarts from 'echarts';*/
    import fetch from '../../utils/fetch';
    var echarts = require('echarts');
    export default {
        name: "FactorySale",
        data () {
            return {
                myChart:{}
            };
        },
        methods:{ },
        mounted () {
            /* this.$nextTick(() => {
                let chart = echarts.init(document.getElementById('factoryChart'));
                chart.title = '坐标轴刻度与标签对齐';

                const option = {
                    color: ['#3398DB'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: { // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        axisTick: {alignWithLabel: true}
                    }],
                    yAxis: [{
                        type: 'value'
                    }],
                    series: [{
                        name: '直接访问',
                        type: 'bar',
                        barWidth: '60%',
                        data: [10, 52, 200, 334, 390, 330, 220]
                    }]
                };
                chart.setOption(option);
            })
            window.addEventListener('resize', function () {
                chart.resize();
            });*/
            // 基于准备好的dom，初始化echarts实例
            this.myChart = echarts.init(document.getElementById('factorySale'));
            fetch({
                'url':"/stat/factorysale",
                'method':"get",
            }).then(resp=>{
                const json = resp.data;
                // 绘制图表
                this.myChart.setOption({
                    title: {
                        text: '厂家销售情况统计'
                    },
                    tooltip: {},
                    xAxis: {
                        data: json.titles
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: json.values
                    }]
                });
            })
        }
    };
</script>

<style scoped>
    #factorySale{
        width: 80%;
        height: 500px;
        border-radius: 25px;
    }
</style>