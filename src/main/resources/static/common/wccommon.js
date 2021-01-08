function initSelect(form) {
    // 刷新厕所选择器
    $.ajax({
        url: '/wc/wcInfo/wcInfoSelect',
        type: 'get',
        dataType: 'json',
        success: function (returnMsg) {
            $.each(returnMsg.data, function (n, info) {
                $('#wcSelector').append('<option value="' + info.wcIdAndMacCode + '">' + info.info + '(' + info.wcIdAndMacCode + ')' + '</option>');
            })
            form.render();
        },
        error: function (XMLHttpRequest) {
            layer.msg("请求失败");
        },
    })
}
