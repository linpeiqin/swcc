let tableIns;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate','tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    let laydate = layui.laydate;
    tree = layui.tree;
    let height = document.documentElement.clientHeight - 160;

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
        , toolbar: '#sensorDataTableToolbarDemo'
        , title: '传感器数据查询'
        , cols: [[
            {field: 'time', title: '记录时间'}
            , {field: 'sensorId', title: '传感器ID'}
            , {field: 'sensorType', title: '传感器类型'}
            , {field: 'value1', title: '值1'}
            , {field: 'value2', title: '值2'}
            , {field: 'value3', title: '值3'}
        ]]
        , defaultToolbar: ['', '', '']
        , page: true
        , height: height
        , cellMinWidth: 60
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        switch (obj.event) {
            case 'query':
                let queryBySensorDataInfo = $("#queryBySensorDataInfo").val();
                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        this.where = {};
                    }
                };
                if (queryBySensorDataInfo) {
                    //设定异步数据接口的额外参数
                    query.where = {info: queryBySensorDataInfo};
                }
                tableIns.reload(query);
                $("#queryBySensorDataInfo").val(queryBySensorDataInfo);
                break;
        }
    });
});
