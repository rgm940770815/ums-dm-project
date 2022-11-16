/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (jQuery) {
    if (!jQuery || jQuery.ChartHelper) {
        return;
    }

    var _ChartHelper = {};

    /**
     * 饼图基本配置
     * @param {type} data
     * @returns {chart_helper_L8._ChartHelper.createPieChartConfig.config}
     */
    _ChartHelper.createPieChartConfig = function (data, config) {
        data = data || [];
        config = config || {};

        return jQuery.extend(true, {
            forceFit: true,
            fitRatio: 0.45,
//            plotCfg: {
//                margin: [50, 50, 100]
//            },
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
                    return point.value + '人';
                }
            },
            series: [{
                type: 'pie',
                name: '饼图',
                data: data
            }]
        }, config);
    };

    _ChartHelper.createColumnChartYConfig = function (data, config) {
        data = data || [];
        config = config || {};

        var categories = [];
        var dataY = [];

        jQuery(data).each(function (index, item) {
            categories.push(item.name);
            dataY.push(item.y);
        });

        return jQuery.extend(true, {
            forceFit: true,
            fitRatio: 0.4,
            plotCfg: {
                margin: [50, 50, 80] //画板的边距
            },
            xAxis: {
                categories: categories,
                labels: {
                    label: {
                        rotate: -45,
                        'text-anchor': 'end'
                    }
                }
            },
            yAxis: {
                min: 0
            },
            seriesOptions: {//设置多个序列共同的属性
                /*columnCfg : { //公共的样式在此配置

                 }*/
            },
            tooltip: {
                valueSuffix: '人'
            },
            series: [{
                name: '柱状图',
                type: 'column',
                data: dataY,
                labels: {//显示的文本信息
                    label: {
                        rotate: -90,
                        y: -30,
                        'fill': '#666',
                        'text-anchor': 'end',
                        textShadow: '0 0 3px black',
                        'font-size': '12px'
                    }
                }

            }]

        }, config);
    }

    jQuery.ChartHelper = _ChartHelper;
})(jQuery);