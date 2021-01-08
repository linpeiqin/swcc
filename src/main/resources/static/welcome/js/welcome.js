var myChart
layui.config({
    base: ctx + '/common/echarts/'
}).extend({
    echarts:'echarts.min'
}).use(['layer', 'echarts'], function () {
    let layer = layui.layer;
    let echarts = layui.echarts;
    $.ajax({
            url: '/wc/wcInfo/wcInfoForState',
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
    let xAxisData = new Array();
    let dataMaps = new Array();
    let dataMapIndex = 0;
    let wcInfos = new Array();
    myChart = echarts.init(document.getElementById('chart'));
    for (let i = 0; i < data.data.length; i++) {
        let info = data.data[i];
        initBrief(info);
        $.ajax({
                url: '/wc/setData/getTotalUsage',
                type: 'get',
                dataType: 'json',
                data: {wcId: info.wcInfoVo.wcId, macCode: info.wcInfoVo.macCode, day: 6},
                success: function (data) {
                    let tempList = new Array();
                    $.each(data.data, function (dataIndex, data) {
                        xAxisData[dataIndex] = data.date;
                        tempList[dataIndex] = data.number;
                    });
                    wcInfos[dataMapIndex] = info.info;
                    dataMaps[dataMapIndex] = {
                        name: info.wcInfoVo.info,
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
            });
    }
}

function initBrief(info) {
    $('#wcBrief').append("<li class=\"col-sm-12 col-md-6 col-xs-12\">" +
        "<a href=\"/wc/wcInfo/largeScreen?wcInfoId="+ info.wcInfoVo.id+"\" target='_blank' class=\"clearfix\">" +
        "<div class=\"icon-bg bg-org f-l\">" +
        "<span class=\"iconfont\">&#xe606;</span>" +
        "</div>" +
        "<div class=\"right-text-con\">" +
        "<p>" +
        "<span class=\"color-org\">" + info.wcInfoVo.info + "</span>" +
        "<span class=\"color-org\">(" + info.wcInfoVo.statusName + ")</span>" +
        "</p>" +
        "<p>" +
        "<span id=\"wcTotal" + info.wcInfoVo.wcIdAndMacCode + "\">总人次："+info.sumSetDataNumber+";</span>" +
        "<span id=\"wcLeft" + info.wcInfoVo.wcIdAndMacCode + "\">总厕位："+info.sumSetNumber+";</span>" +
        "</p>" +
        "<p>" +
        "<span id=\"wcLeft" + info.wcInfoVo.wcIdAndMacCode + "\">男厕位："+info.sumManSetNumber+";</span>" +
        "<span id=\"wcLeft" + info.wcInfoVo.wcIdAndMacCode + "\">女厕位："+info.sumWomanSetNumber+";</span>" +
        "</p>" +
        "</div>" +
        "</a>" +
        "</li>");
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
            xAxis: {
                type: "category",
                boundaryGap: false
                , data: xAxisDataVar
            },
            yAxis: {
                type: "value"
            }
            , series: dataMapsVar
        });
}

window.onresize = function () {
    myChart.resize();
}