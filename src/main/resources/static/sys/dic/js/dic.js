let tableIns;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    tree = layui.tree;
    tableIns = table.render({
        elem: '#dicTable'
        , url: ctx + '/sys/dic/page'
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
        , title: '字典信息'
        , cols: [[
            {field: 'title', width: 200, title: '字典名称', align: 'center'}
            , {field: 'tag', width: 200, title: '内部标签', align: 'center'}
            , {field: 'value', width: 100, title: '字典值', align: 'center'}
            , {field: 'typeId', width: 100, title: '字典类型ID', align: 'center'}
            , {field: 'status', width: 100, title: '状态', align: 'center', templet: '#statusTpl'}
            , {field: 'note', width: 100, title: '备注', align: 'center'}
            , {field: 'sort', width: 100, title: '显示顺序', align: 'center'}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', title: '操作', width: 120, toolbar: '#dicTableBarDemo'}
        ]]
        , page: true
        , height: 'full-120'
        , cellMinWidth: 60
    });
    //头工具栏事件
    form.on('submit(addData)', function (obj) {
        $("#dicForm")[0].reset();
        form.render();
        layer.msg("请填写右边的表单并保存！");
        return false;
    });

    //监听行工具事件
    table.on('tool(dicFilter)', function (obj) {
        let data = obj.data;
        //删除
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                //向服务端发送删除指令
                $.delete(ctx + "/sys/dic/delete/" + data.id, {}, function (data) {
                    obj.del();
                    layer.close(index);
                })
            });
        }
        //编辑
        else if (obj.event === 'edit') {
            //回显操作表单
            $("#dicForm").form(data);
            form.render();
        }
    });
});

/**
 * 提交保存
 */
function dicFormSave() {
    let dicForm = $("#dicForm").serializeObject();
    dicForm.updateTime = commonUtil.getNowTime();
    $.post(ctx + "/sys/dic/save", dicForm, function (data) {
        if (!data.flag) {
            layer.msg(data.msg, {icon: 2, time: 2000}, function () {
            });
            return;
        }
        layer.msg("保存成功", {icon: 1, time: 2000}, function () {
        });
        tableIns.reload();
    });
}


