let tableIns;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    let laydate = layui.laydate;
    tree = layui.tree;
    tableIns = table.render({
        elem: '#sensorDataTable'
        , url: ctx + '/wc/sensorData/page'
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
        , title: '传感器数据查询'
        , cols: [[
            {field: 'sortNumber', title: '序号', type: 'numbers'}
            , {field: 'time', title: '记录时间', sort: true}
            , {field: 'sensorId', title: '传感器ID', sort: true}
            , {field: 'sensorTypeName', title: '传感器类型', sort: true}
            , {field: 'value1Text', title: '值1'}
            , {field: 'value2Text', title: '值2'}
            , {field: 'value3Text', title: '值3'}
            , {field: 'macCode', title: '物理地址'}
        ]]
        , page: true
        , height: 'full-120'
        , cellMinWidth: 60
    });
    initSelect(form);
    // 刷新按钮
    $("#rqueryButton").click(function () {
        let wcId = $('#wcSelector').val().split('|')[0] ? $('#wcSelector').val().split('|')[0] : null;
        let macCode = $('#wcSelector').val().split('|')[1] ? $('#wcSelector').val().split('|')[1] : null;
        let startTime = $('#startTimePicker').val();
        let endTime = $('#endTimePicker').val();
        let query = {};
        query.where = {macCode: macCode, wcId: wcId, startTime: startTime, endTime: endTime};
        tableIns.reload(query);
        return false;
    })
    //日期选择器
    laydate.render({
        elem: '#startTimePicker',
        theme: '#8470FF',
        type: 'datetime', //选择时间
    });
    //日期选择器
    laydate.render({
        elem: '#endTimePicker',
        theme: '#8470FF',
        type: 'datetime', //选择时间
    });
});
