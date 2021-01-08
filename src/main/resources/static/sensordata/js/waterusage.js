let tableIns;
let util;
let myChart;
layui.config({
    base: ctx + '/common/echarts/'
}).extend({
    echarts:'echarts.min'
}).use(['util', 'element', 'form', 'table', 'layer', 'laydate', 'echarts'], function () {
    let table = layui.table;
    let laydate = layui.laydate;
    let element = layui.element;
    util = layui.util;
    let form = layui.form;
    let echarts = layui.echarts;
    tableIns = table.render({
        elem: '#waterUsageTable'
        , url: ctx + '/wc/waterUsage/list'
        , method: 'POST'
        , title: '用水信息'
        , height: 'full-180'
        , cellMinWidth: 200
        , page: false
        , cols: [[
            {field: 'sortNumber', title: '序号', type: 'numbers'}
            , {
                field: 'id', title: '记录日期', templet: function (d) {
                    return util.toDateString(d.id * 1000 * 60 * 60 * 24, "yyyy-MM-dd");
                }
            }
            , {field: 'val', title: '水表读数', sort: true}
        ]]
        , response: {
            statusName: 'flag' //规定数据状态的字段名称，默认：code
            , statusCode: true //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            , countName: 'records' //规定数据总数的字段名称，默认：count
            , dataName: 'rows' //规定数据列表的字段名称，默认：data
        }
        , parseData: function (res) { //res 即为原始返回的数据
            var data = res.data;
            return {
                "flag": res.flag, //解析接口状态
                "msg": res.msg, //解析提示文本
                "records": data.length, //解析数据长度
                "rows": data //解析数据列表
            };
        }
        ,
        done: function (res, curr, count) {
            reloadChart(res, curr, count, echarts);
        }
    });
    initSelect(form);
    // 刷新按钮
    $("#rqueryButton").click(function () {
        let wcId = $('#wcSelector').val().split('|')[0] ? $('#wcSelector').val().split('|')[0] : null;
        let macCode = $('#wcSelector').val().split('|')[1] ? $('#wcSelector').val().split('|')[1] : null;
        let sd = new Date($('#startDatePicker').val());
        let ed = new Date($('#endDatePicker').val());
        let startDate = "";
        let endDate = "";
        if (!isNaN(sd)) {
            startDate = sd.getTime(sd) / 1000 / 60 / 60 / 24;
        }
        if (!isNaN(ed)) {
            endDate = ed.getTime(ed) / 1000 / 60 / 60 / 24;
        }
        let query = {};
        query.where = {macCode: macCode, wcId: wcId, startDate: startDate, endDate: endDate};
        tableIns.reload(query);
        return false;
    })
//日期选择器
    laydate.render({
        elem: '#startDatePicker',
        theme: '#8470FF',
    });
    //日期选择器
    laydate.render({
        elem: '#endDatePicker',
        theme: '#8470FF',
    });
    element.on('tab(waterUsageTabFilter)', function(){
        if (this.getAttribute('lay-id')=='chartLi'){
            myChart.resize({width:$('#chart-panel').width(),height:$('#chart-panel').height()});
        }
    });
});

function reloadChart(res, curr, count, echarts) {
    let dateList = new Array();
    let usageList = new Array();
    let dataList = new Array();
    let temp = 0.0;
    for (var i = 0; i < count; i++) {
        let row = res.rows[i];
        temp = row.val - temp;
        dateList[i] = util.toDateString(row.id * 1000 * 60 * 60 * 24, "yyyy-MM-dd");
        usageList[i] = row.val;
        dataList[i] = temp;
        temp = row.val;
    }
    initChart(dateList, usageList, dataList, echarts)
}
function initChart(dateList, usageList, dataList, echarts) {
    myChart = echarts.init(document.getElementById('chart'));
    myChart.setOption(
        {
            title: {
                text: "数据统计",
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
                data: ["用水量", "水表读数"],
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
            xAxis: [
                {
                    type: "category",
                    boundaryGap: false,
                    data: dateList
                }
            ],
            yAxis: [
                {
                    type: "value"
                }
            ],
            grid: {
                x2: 30,
                x: 50
            },
            series: [
                {
                    name: "用水量",
                    type: "line",
                    smooth: true,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: "default"
                            }
                        }
                    },
                    data: usageList
                },
                {
                    name: "水表读数",
                    type: "line",
                    smooth: true,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: "default"
                            }
                        }
                    },
                    data: dataList
                }
            ]
        }
    );
}
window.onresize = function () {
    myChart.resize({width:$('#chart-panel').width(),height:$('#chart-panel').height()});
}


