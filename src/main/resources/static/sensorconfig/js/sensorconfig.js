let tableIns;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    tree = layui.tree;
    tableIns = table.render({
        elem: '#sensorConfigTable'
        , url: ctx + '/wc/sensorConfig/page'
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
        , title: '传感器列表'
        , cols: [[
            {field: 'sortNumber', title: '序号', type: 'numbers'}
            , {field: 'wcInfoInfo', title: '厕所信息', templet: '<div>{{d.wcInfoVo.info}}</div>'}
            , {
                field: 'status', title: '连接状态', sort: true, templet: function (d) {
                    if (d.status == 1) {
                        return "<span  style='background: rgba(13,185,51,0.72); padding: 6px; border-radius: 3px; color: #ffffff;'>"+d.statusName+"</span>";
                    } else {
                        return "<span  style='background: rgba(0,0,0,0.37); padding: 6px; border-radius: 3px; color: #ffffff;'>"+d.statusName+"</span>";
                    }
                }
            }
            , {field: 'modbusId', title: '总线ID', sort: true}
            , {field: 'sensorTypeName', title: '传感器类型', sort: true}
            , {field: 'outId', title: '输出ID', sort: true}
            , {field: 'limitVal', title: '动作值', sort: true}
            , {field: 'limitDownVal', title: '解除值', sort: true}
            , {fixed: 'right', title: '操作', toolbar: '#sensorConfigTableBarDemo'}
        ]]
        , page: true
        , height: 'full-120'
        , cellMinWidth: 60
    });
    initSelect(form);
    form.on('submit(addData)', function (obj) {
        $("#setInfoForm")[0].reset();
        form.render();
        layer.msg("请填写右边的表单并保存！");
        return false;
    });
    table.on('tool(sensorConfigFilter)', function (obj) {
        let data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                $.delete(ctx + "/wc/sensorConfig/delete/" + data.id, {}, function (data) {
                    obj.del();
                    layer.close(index);
                })
            });
        } else if (obj.event === 'edit') {
            $("#sensorConfigForm").form(data);
            form.render();
        }
    });

    form.on('select(wcInfoSelector)', function (data) {
        let wcId = $('#wcSelector').val().split('|')[0] ? $('#wcSelector').val().split('|')[0] : null;
        let macCode = $('#wcSelector').val().split('|')[1] ? $('#wcSelector').val().split('|')[1] : null;
        let query = {};
        query.where = {wcId: wcId, macCode: macCode};
        tableIns.reload(query);
    })
});

/**
 * 提交保存
 */
function sensorConfigFormSave() {
    let sensorConfigForm = $("#sensorConfigForm").serializeObject();
    $.post(ctx + "/wc/sensorConfig/save", sensorConfigForm, function (data) {
        layer.msg("保存成功", {icon: 1, time: 2000}, function () {
        });
        tableIns.reload();
    });
}