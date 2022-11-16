<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>部门检查</title>
        <link href="js/bui/css/bs3/dpl.css" rel="stylesheet" type="text/css"/>
        <link href="js/bui/css/bs3/bui.css" rel="stylesheet" type="text/css"/>
        <style>
            .bui-queue-item{
                width: 200px;
                height: 200px;
            }
            .success{
                width: 100%;
                height: 100%;
            }
        </style>
    </head>
    <body>
        <div class="demo-content">
            <div class="row">
                <div class="span8">
                    <div id="J_Uploader">
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery/jquery.js"></script>
        <script type="text/javascript" src="js/bui/bui.js"></script>
        <script type="text/javascript" src="js/bui/config.js"></script>
        <script type="text/javascript">
            BUI.use('bui/uploader', function (Uploader) {

                /**
                 * 返回数据的格式
                 *
                 *  默认是 {url : 'url'},否则认为上传失败
                 *  可以通过isSuccess 更改判定成功失败的结构
                 */
                var uploader = new Uploader.Uploader({
                    //指定使用主题
                    theme: 'imageView',
                    render: '#J_Uploader',
                    url: 'photo/upload',
                    name: "photo",
                    queue: {
                        resultTpl: {
                            'success': '<div class="success"><img src="{url}" title="{name}"/></div>',
                            'error': '<div class="error"><span class="uploader-error">{msg}</span></div>'
                        }
                    },
                    rules: {
                        //文的类型
                        ext: ['.png,.jpg,.jpeg,.gif,.bmp', '文件类型只能为{0}'],
                        //上传的最大个数
                        max: [1, '文件的最大个数不能超过{0}个'],
                        //文件大小的最小值,这个单位是kb
                        minSize: [10, '文件的大小不能小于{0}KB'],
                        //文件大小的最大值,单位也是kb
                        maxSize: [10240, '文件大小不能大于10M']
                    }
                }).render();

                var queue = uploader.get("queue");

            });
        </script>
    </body>

</html>
