let tableIns;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    tree = layui.tree;
    tableIns = table.render({
        elem: '#setInfoTable'
        , url: ctx + '/wc/setInfo/page'
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
        , title: '厕位列表'
        , cols: [[
            {field: 'sortNumber', title: '序号',type:'numbers'}
            , {field: 'wcInfoInfo', title: '厕所信息', templet: '<div>{{d.wcInfoVo.info}}</div>'}
            , {field: 'setId', title: '厕位ID', sort: true}
            , {field: 'zigbeeMId', title: '总线ID', sort: true}
            , {field: 'zigbeeBId', title: '厕位标识ID', sort: true}
            , {field: 'createTime', title: '创建时间', sort: true}
            , {field: 'wcType', title: '厕所类型', sort: true}
            , {fixed: 'right', title: '操作', toolbar: '#setInfoTableBarDemo', fixed: 'right'}
        ]]
        , page: true
        , height: 'full-155'
        , cellMinWidth: 60
    });
    initSelect(form);
    //头工具栏事件
    form.on('submit(addData)', function (obj) {
        $("#setInfoForm")[0].reset();
        form.render();
        layer.msg("请填写右边的表单并保存！");
        return false;
    });

    //监听行工具事件
    table.on('tool(setInfoFilter)', function (obj) {
        let data = obj.data;
        //删除
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                //向服务端发送删除指令
                $.delete(ctx + "/wc/setInfo/delete/" + data.id, {}, function (data) {
                    obj.del();
                    layer.close(index);
                })
            });
        }
        //编辑
        else if (obj.event === 'edit') {
            //回显操作表单
            $("#setInfoForm").form(data);
            form.render();
        }
    });
    //厕所切换
    form.on('select(wcInfoSelector)', function (data) {
        let wcId = data.value;
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
    })
});

/**
 * 提交保存
 */
function setInfoFormSave() {
    let setInfoForm = $("#setInfoForm").serializeObject();
    $.post(ctx + "/wc/setInfo/save", setInfoForm, function (data) {
        layer.msg("保存成功", {icon: 1, time: 2000}, function () {
        });
        tableIns.reload();
    });
}

