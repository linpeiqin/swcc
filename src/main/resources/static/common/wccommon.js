function initSelect(form) {
    // 刷新厕所选择器
    $.ajax({
        url: '/wc/wcInfo/wcInfoSelect',
        type: 'get',
        dataType: 'json',
        success: function (returnMsg) {
            $.each(returnMsg.data, function (n, info) {
                var value = info.wcId + "|" + info.macCode;
                $('#wcSelector').append('<option value="' + value + '">' + info.info + '(' + '厕所ID:' + value + ')' + '</option>');
            })
            form.render();
        },
        error: function (XMLHttpRequest) {
            layer.msg("请求失败");
        },
    })
}
