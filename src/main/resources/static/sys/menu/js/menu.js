let tree = {};
layui.config({
    base: ctx + '/assets/module/iconPicker/'
}).use(['element', 'form', 'table', 'layer', 'tree', 'util', 'iconPicker'], function () {
    let iconPicker = layui.iconPicker;
    tree = layui.tree;

    //获取菜单数据
    $.post(ctx + "/sys/sysMenu/listByTier", {}, function (data) {
        //数据说明：id对应id，title对应menuName，href对应menuPath
        let treeData = commonUtil.updateKeyForLayuiTree(data.data);

        //开启节点操作图标
        tree.render({
            elem: '#menuTree'
            , id: 'menuTree'
            , data: [{
                title: '系统菜单根节点'
                , href: "/"
                , orderNumber: "0"
                , id: "0"
                , spread: true
                , children: treeData
                , icon: ""
            }]
            , onlyIconControl: true
            , edit: ['add', 'del']
            //节点被点击
            , click: function (obj) {
                $("#menuForm")[0].reset();
                $("#menuForm").form({
                    menuId: obj.data.id,
                    menuName: obj.data.title,
                    menuPath: obj.data.href,
                    orderNumber: obj.data.orderNumber,
                    menuParentName: obj.elem.parent().parent().children(".layui-tree-entry").find(".layui-tree-txt").text(),
                    menuParentId: obj.elem.parent().parent().data("id"),
                    icon: obj.data.icon,
                    treeId: obj.data.id
                });
                iconPicker.checkIcon('iconPicker', obj.data.icon);
            }
            //对节点进行增删改操作回调
            , operate: function (obj) {
                let type = obj.type; //得到操作类型：add、edit、del
                let data = obj.data; //得到当前节点的数据
                let elem = obj.elem; //得到当前节点元素

                if (type === 'add') { //增加节点
                    $("#menuForm")[0].reset();
                    //返回 key 值
                    return "";
                } else if (type === 'del') { //删除节点
                    layer.confirm('确认要删除这个菜单吗？\n注意：删除父节点将会一同删除子节点', function (index) {
                        $.delete(ctx + "/sys/sysMenu/delete/" + data.id, {}, function () {
                            layer.msg("删除成功");
                            elem.remove();
                        });
                        layer.close(index);
                    });

                }
            }
        });
    });
//渲染图标
    iconPicker.render({
        elem: '#iconPicker',
        type: 'fontClass',
        search: true,
        page: true,
        limit: 20
        , click: function (data) {
            $('#icon').val(data.icon);
        }
    });
    iconPicker.checkIcon('icon', '');
});


/**
 * 提交保存
 */
function menuFormSave() {
    var menuForm = $("#menuForm").serializeObject();
    if (menuForm.menuParentId === "") {
        return;
    }
    if (menuForm.menuId === "0") {
        layer.msg("根节点仅用于展示，不可操作！", {icon: 2, time: 2000}, function () {
        });
        return;
    }
    if (menuForm.menuParentId === "0") {
        menuForm.menuParentId = "";
    }
    $.post(ctx + "/sys/sysMenu/save", menuForm, function (data) {
        layer.msg("保存成功", {icon: 1, time: 2000}, function () {

        });
        //更新树组件
        $("div[data-id='" + menuForm.treeId + "']").children(".layui-tree-entry").find(".layui-tree-txt").text(data.data.menuName);
        $("div[data-id='" + menuForm.treeId + "']").attr("data-id", data.data.menuId);
    });
}