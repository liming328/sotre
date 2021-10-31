<template>
    <Row>
        <div style="width: 100%;height:500px;" id="productChart"></div>
    </Row>
</template>

<script>
    import echarts from 'echarts';
    import fetch from '../../utils/fetch';
   /* var echarts = require('echarts');*/
    export default {
        name: "productChart",
        data() {
            return {
                titles: null,
                values: null
            };
        },
        methods: {},
        mounted:function () {
                let chart = echarts.init(document.getElementById('productChart'));
                chart.title = '坐标轴刻度与标签对齐';
                //ajax请求获取报表数据
                fetch({
                    url: "/stat/productSale",
                    method: "get"
                }).then(resp => {
                    this.titles = resp.data.titles;
                    this.values = resp.data.values;
                    let option = {
                        title: {
                            text: '产品销售情况排行',
                            subtext: '',
                            x:'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: this.titles
                        },
                        series: [{
                            name: '产品销售情况排行',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data:this.values,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
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