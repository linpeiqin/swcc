var myChart
layui.config({
    base: ctx + '/common/echarts/'
}).use(['layer', 'echarts'], function () {
    let layer = layui.layer;
    let echarts = layui.echarts;
    $.ajax({
            url: '/wc/wcInfo/wcInfoSelect',
            type: 'get',
            dataType: 'json',
            success: function (data) {
                initPage(data, echarts);
            },
            error: function (XMLHttpRequest) {
                layer.msg("请求失败");
            }
        }
    );
})

function initPage(data, echarts) {
    var xAxisData = new Array();
    var dataMaps = new Array();
    var dataMapIndex = 0;
    var wcInfos = new Array();
    myChart = echarts.init(document.getElementById('chart'));
    $.each(data.data, function (n, info) {
        $('#wcBrief').append("<li class=\"col-sm-12 col-md-6 col-xs-12\">" +
            "<a href=\"javascript:;\" class=\"clearfix\">" +
            "<div class=\"icon-bg bg-org f-l\">" +
            "<span class=\"iconfont\">&#xe606;</span>" +
            "</div>" +
            "<div class=\"right-text-con\">" +
            "<p><span class=\"color-org\">" + info.info + "</span></p>" +
            "<p id=\"wc_total_" + info.wcId + "\">总人次：0</p>" +
            "<p id=\"wc_left_" + info.wcId + "\">剩余厕位：0</p>" +
            "</div>" +
            "</a>" +
            "</li>");
        $.ajax({
                url: '/wc/setData/getTotalUsage',
                type: 'get',
                dataType: 'json',
                data: {wcId: info.wcId, day: 7},
                success: function (data) {
                    var tempList = new Array();
                    $.each(data.data, function (dataIndex, data) {
                        xAxisData[dataMapIndex] = data.date;
                        tempList[dataIndex] = data.number
                    });
                    wcInfos[dataMapIndex] = info.info;
                    dataMaps[dataMapIndex] = {
                        name: info.info,
                        type: "line",
                        smooth: true,
                        itemStyle: {
                            normal: {
                                areaStyle: {
                                    type: "default"
                                },
                                color: "rgb(" + Math.random() * 255 + ", " + Math.random() * 255 + ", " + Math.random() * 255 + ")"
                            }
                        },
                        data: tempList
                    };
                    dataMapIndex = dataMapIndex + 1;
                    updateCharts(xAxisData, dataMaps, wcInfos);
                },
                error: function (XMLHttpRequest) {
                    layer.msg("请求失败");
                }
            }
        );

    })
}

function updateCharts(xAxisDataVar, dataMapsVar, names) {
    myChart.setOption(
        {
            title: {
                text: "近七天使用次数统计",
                textStyle: {
                    color: "rgb(85, 85, 85)",
                    fontSize: 18,
                    fontStyle: "normal",
                    fontWeight: "normal"
                }
            },
            tooltip: {
                trigger: "axis"
            },
            legend: {
                data: names,
                selectedMode: false,
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {
                        show: false,
                        readOnly: true
                    },
                    magicType: {
                        show: false,
                        type: ["line", "bar", "stack", "tiled"]
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    },
                    mark: {
                        show: false
                    }
                }
            },
            calculable: false,
            xAxis: [{
                type: "category",
                boundaryGap: false
                , data: xAxisDataVar
            }],
            yAxis: [{
                type: "value"
            }],
            grid: {
                x2: 30,
                x: 50
            }
            , series: dataMapsVar
        });
}
window.onresize = function () {
    myChart.resize();
}