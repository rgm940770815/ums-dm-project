<%--
    Document   : position_pie
    Created on : 2015-1-26, 11:52:02
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>职务饼图</title>
        <jsp:include page="/basic_import.jsp"></jsp:include>
        <script src="<%=basePath%>js/common/achart_factory.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="detail-section">
            <div id="pos_pie">
            </div>
            <div id="pos_col">
            </div>
        </div>
        <script>
            AChartFactory.createChart('pie', '<%=basePath%>stat/positionPie', 'pos_pie');
            AChartFactory.createChart('column', '<%=basePath%>stat/positionPie', 'pos_col');
        </script>
        <%--
            <script type="text/javascript">
                BUI.use(['bui/chart', 'bui/data'], function (Chart, Data) {
                    var store = new Data.Store({
                        url: "<%=basePath%>stat/positionPie"
                    });

                store.load({}, function (d) {
                    chart.changeData(d, true);
                });

                var chart = new Chart.Chart({
                    theme: Chart.Theme.SmoothBase,
                    id: 'pos_pie',
                    width: 950,
                    height: 500,
                    legend: null, //不显示图例
                    autoRender: true,
                    seriesOptions: {//设置多个序列共同的属性
                        pieCfg: {
                            allowPointSelect: true,
                            labels: {
                                distance: 40,
                                label: {
                                    //文本信息可以在此配置
                                },
                                renderer: function (value, item) { //格式化文本
                                    return value + ' ' + (item.point.percent * 100).toFixed(2) + '%';
                                }
                            }
                        }
                    },
                    tooltip: {
                        pointRenderer: function (point) {
                            return (point.percent * 100).toFixed(2) + '%';
                        }
                    },
                    series: [{
                            type: 'pie',
                            name: '职位占比'
                        }]
                });

            });

        </script>
        --%>
    </body>
</html>
