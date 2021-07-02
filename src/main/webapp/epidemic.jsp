<%--
  Created by IntelliJ IDEA.
  User: 53219
  Date: 2021/6/30
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>疫情信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        #body1 {
            background-color: #10AEB5;
        }
    </style>
    <!-- bootstrap-->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/respond.js"></script>
</head>
<body id="body1">
<!--绘制静态界面-->
<div class="container">
    <!--地图-->
    <div class="row">
        <div class="col-md-12" style="background-color: #fff;margin: 5px;">
            <div id="myMap" style="height: 600px;"></div>
        </div>
    </div>
    <br>
    <!--表格-->
    <div class="row" style="height: 400px;overflow: auto;">
        <div class="col-md-12" style="background-color: #fff">
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th>省份</th>
                    <th>确诊人数</th>
                    <th>疑似人数</th>
                    <th>隔离人数</th>
                    <th>治愈人数</th>
                    <th>死亡人数</th>
                </tr>
                </thead>
                <tbody id="tbody1">

                </tbody>
            </table>
        </div>
    </div>
    <br>
    <!--柱状图-->
    <div class="row">
        <div class="col-md-12">
            <div id="mycharts" style="height: 500px;background: #fff;">
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
<!--动态特效处理-->
<script type="text/javascript">
    $(function () {
        /**定义个用来给表格中装在数据的函数
         *
         * @param epidemic
         */
        var fillToTable = function (epidemic) {
            var tbody1 = $("#tbody1");
            tbody1.empty();//先清理
            $.each(epidemic, function (index, epidemic) {
                var tr = $("<tr>");
                var td = $("<td>");
                td.text(epidemic.provinceName);//省份名称：从实体类中复制
                tr.append(td);

                var td = $("<td>");
                td.html(epidemic.affirmedTotal);//确诊人数
                tr.append(td);

                var td = $("<td>");
                td.html(epidemic.suspectedTotal);//疑似人数
                tr.append(td);

                var td = $("<td>");
                td.html(epidemic.isolatedTotal);//隔离人数
                tr.append(td);

                var td = $("<td>");
                td.html(epidemic.curedTotal);//治愈人数
                tr.append(td);

                var td = $("<td>");
                td.html(epidemic.deadTotal);//死亡人数
                tr.append(td);

                tbody1.append(tr);
            })
        };


        //将服务器返回的数据设置到图标上
        var fillToChart = function (epidemics) {
            var provinceNames = [];//所有的省份名称
            var affirmedTotal = [];//所有确诊人数
            $.each(epidemics, function (index, epidemic) {
                var obj = [];
                provinceNames.push(epidemic.provinceName);//省份名称
                affirmedTotal.push(epidemic.affirmedTotal);//确诊人数
            });
            myCharts.setOption({
                xAxis: {
                    data: provinceNames
                },
                series: [
                    {
                        data: affirmedTotal
                    }
                ]
            })
        }
        /**
         *定义空白图表
         */
        var myCharts = echarts.init($("#mycharts").get(0));//拿到柱状图布局
        var option = {
            title: {
                text: "当日全国疫情柱状图",
                subtext: "2020-03-06"
            },
            grid: {
                show: true
            },
            legend: {
                data: ["2020-03-06 全国累计确诊人数"]
            },
            tooltip: {
                trigger: "axis"
            },
            xAxis: {
                data: []
            },
            yAxis: {
                data: []
            },
            series: [
                {
                    name: '2020-03-06 全国累计确诊人数',
                    type: 'bar',
                    data: []
                }
            ]

        };
        myCharts.setOption(option);
        /**
         *定义空白地图
         */
        var myMap = echarts.init($("#myMap").get(0));//拿到地图布局
        //获取地图json数据，显示中国地图
        $.getJSON("${pageContext.request.contextPath}/echarts/china.json", {}, function (chinaJson) {
            //注册地图
            echarts.registerMap("china", chinaJson);
            var option = {
                title: {
                    text: "2020-03-06 全国疫情分布图"
                },
                legend: {
                    data: ["累计确诊人数"]
                },
                tooltip: {},
                visualMap: {
                    type: 'piecewise',
                    min: 0,
                    max: 10000,
                    splitList:
                        [{
                            start: 1000,
                            end: 10000
                        }, {
                            start: 500,
                            end: 1000
                        }, {
                            start: 100,
                            end: 500
                        }, {
                            start: 0,
                            end: 100
                        }],
                    textStyle: {
                        color: 'orange'
                    },
                    series: [
                        {
                            name: '累计确诊人数',
                            type: 'map',
                            mapType: "china",
                            data: []
                        }
                    ]
                }
            };
            myMap.setOption(option);
        }, "json");
        /**
         * 将数据填充到地图中
         */
        var fillToMap = function (epidemics) {
            var data = [];//数组接受数据
            $.each(epidemics, function (index, epidemic) {
                var obj = {};//将数据存入对象
                obj.name = epidemic.provinceName;//省份名称
                obj.value = epidemic.affirmedTotal;//确诊人数
                data.push(obj);
            });
            //给地图设置参数
            myMap.setOption({
                series: [
                    {
                        name: '累计确诊人数',
                        type: 'map',
                        mapType: "china",
                        data: data//将数据填充进去，不然就是空白地图
                    }
                ]
            });
        }


        /**
         * 发送请求获取最新疫情数据
         */
        $.get("${pageContext.request.contextPath}/epidemicData/ajax/lastestData", {}, function (resp) {
            if (resp.code < 0) {
                //弹框提示
                alert(resp.msg)
            } else {
                fillToTable(resp.data);//给表格填充数据
                fillToMap(resp.data);//给地图填充数据
                fillToChart(resp.data);//给图标填充数据
            }
        }, "json")
    })

</script>
</body>
</html>
















