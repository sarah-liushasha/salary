layui.define(['layer'], function (exports) {
    var layer = layui.layer;
    var $ = layui.jquery;

    $(document).ajaxError(
        function (event, xhr, options, exc) {
            if (xhr.status === 500) {
                var res = xhr.responseText;
                var resJson = JSON.parse(res);
                if (resJson.msg) {
                    common.msgError(resJson.msg);
                }
            } else if (xhr.status === 401) {
                window.location.href = "/login";
            } else if (xhr.status === 404) {
                common.msgError("404   请求地址：[" + options.url + "]不存在！");
            }
        }
    );
    var common = {
        /**
         * 抛出一个异常错误信息
         * @param {String} msg
         */
        throwError: function (msg) {
            throw new Error(msg);
        },
        /**
         * 弹出一个错误提示
         * @param {String} msg
         */
        msgError: function (msg) {
            layer.msg(msg, {
                icon: 5,
                time: 4000
            });
            return;
        }
    };
    exports('common', common);
});