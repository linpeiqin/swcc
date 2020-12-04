let tableIns;
layui.config({
    base: ctx + '/common/echarts/'
}).use(['util','element', 'form', 'table', 'layer', 'laydate','echarts'], function () {
    let table = layui.table;
    let laydate = layui.laydate;
    let util = layui.util;
    let form = layui.form;
    let echarts = layui.echarts;
    tableIns = table.render({
        elem: '#waterUsageTable'
        , url: ctx + '/wc/waterUsage/page'
        , method: 'POST'
        //请求前参数处理
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'flag' //规定数据状态的字段名称，默认：code
            , statusCode: true //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            , countName: 'records' //规定数据总数的字段名称，默认：count
            , dataName: 'rows' //规定数据列表的字段名称，默认：data
        }
        //响应后数据处理
        , parseData: function (res) { //res 即为原始返回的数据
            var data = res.data;
            return {
                "flag": res.flag, //解析接口状态
                "msg": res.msg, //解析提示文本
                "records": data.records, //解析数据长度
                "rows": data.rows //解析数据列表
            };
        }
        , title: '用水信息'
        , cols: [[
            {field: 'sortNumber', title: '序号',type:'numbers'}
            , {field: 'id', title: '记录日期',templet:function(d){return util.toDateString(d.id*1000* 60 * 60 * 24, "yyyy-MM-dd");}}
            , {field: 'val', title: '水表读数',sort: true}
        ]]
        , height: 'full-220'
        , cellMinWidth: 80
        ,//在表格加载完毕后执行的方法
        done: function (res, curr, count) {
            var dateList =  new Array();
            var usageList =  new Array();
            var dataList =  new Array();
            var temp = 0.0;
            for (var i=0;i<count;i++){
                var row = res.rows[i];
                temp = row.val - temp;
                dateList[i] = util.toDateString(row.id*1000* 60 * 60 * 24, "yyyy-MM-dd");
                usageList[i] = row.val;
                dataList[i] = temp;
                temp = row.val;
            }
            initChart(dateList,usageList,dataList,echarts)
        }

    });
    initSelect(form);
    // 刷新按钮
    $("#rqueryButton").click(function() {
        let wcId = $('#wcSelector').val();
        let startDate = $('#startDatePicker').val();
        let endDate = $('#endDatePicker').val();
        let query = {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , done: function (res, curr, count) {
                //完成后重置where，解决下一次请求携带旧数据
                this.where = {};
            }
        };
        if (wcId) {
            //设定异步数据接口的额外参数
            query.where = {wcInfoWcId: wcId};
        }
        tableIns.reload(query);
        return false;

    })
//日期选择器
    laydate.render({
        elem: '#startDatePicker',
        theme:'#8470FF',
    });
    //日期选择器
    laydate.render({
        elem: '#endDatePicker',
        theme:'#8470FF',
    });


});

function initChart(dateList,usageList,dataList,echarts){
    var myChart  = echarts.init(document.getElementById('chart'));
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
                data: ["用水量","水表读数"],
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
    $(window).resize(function(){
        myChart.resize();
    })
}



