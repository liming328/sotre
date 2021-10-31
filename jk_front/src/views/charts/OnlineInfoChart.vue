<template>
    <Row>
        <div style="width: 100%;height:500px;" id="onlineChart"></div>
    </Row>
</template>

<script>
    import echarts from 'echarts';
    import fetch from '../../utils/fetch';
   /* var echarts = require('echarts');*/
    export default {
        name: "onlineChart",
        data() {
            return {
                titles: null,
                values: null
            };
        },
        methods: {},
        mounted:function () {
                let chart = echarts.init(document.getElementById('onlineChart'));
                chart.title = '坐标轴刻度与标签对齐';
                //ajax请求获取报表数据
                fetch({
                    url: "/stat/onlineInfo",
                    method: "get"
                }).then(resp => {
                    this.titles = resp.data.titles;
                    this.values = resp.data.values;
                    let option = {
                        title: {
                            text: '系统访问压力统计',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross'
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: this.titles
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} W'
                            },
                            axisPointer: {
                                snap: true
                            }
                        },
                        visualMap: {
                            show: false,
                            dimension: 0,
                            pieces: [{
                                lte: 10,
                                color: 'green'
                            }, {
                                gt: 10,
                                lte: 13,
                                color: 'red'
                            },{
                                gt: 13,
                                lte: 15,
                                color: 'green'
                            },{
                                gt: 14,
                                lte: 17,
                                color: 'red'
                            },{
                                gt: 17,
                                color: 'green'
                            }]
                        },
                        series: [{
                            name:'用电量',
                            type:'line',
                            smooth: true,
                            data:this.values,
                            markArea: {
                                data: [ [{
                                    name: '早高峰',
                                    xAxis: '10'
                                },{
                                    xAxis: '12'
                                }], [{
                                    name: '晚高峰',
                                    xAxis: '18'
                                },{
                                    xAxis: '22'
                                }] ]
                            }
                        }]
                    };
                    chart.setOption(option);
                })
                window.addEventListener('resize', function () {
                    chart.resize();
            });
        }
    };
</script>