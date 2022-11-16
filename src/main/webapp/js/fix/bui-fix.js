/**
 * Created by Diluka on 2015-06-25.
 */

/**
 * 修复dp永远往下弹出的bug
 * @param $ jQuery
 * @param Calendar BUI.Calendar
 */
function datepickerFix($, Calendar) {
    //DatePicker
    $(".bui-datepicker").remove();

    var datepicker = new Calendar.DatePicker({
        trigger: '.calendar',
        delegateTrigger: true, //如果设置此参数，那么新增加的.calendar元素也会支持日历选择
        autoRender: true
    });

    datepicker.on("show", function (e) {

        var $dp = $(e.target.get("el"));

        if ($(window).height() < $dp.offset().top + $dp.height()) {
            $dp.offset({top: $dp.offset().top - $dp.height() - 32});
            $dp.find(".bui-calendar").addClass("bui-calendar2");
        } else {
            $dp.find(".bui-calendar").removeClass("bui-calendar2");
        }
    });


    var datetimepicker = new Calendar.DatePicker({
        trigger: '.calendar-time',
        showTime: true,
        delegateTrigger: true, //如果设置此参数，那么新增加的.calendar元素也会支持日历选择
        autoRender: true
    });

    datetimepicker.on("show", function (e) {

        var $dp = $(e.target.get("el"));

        if ($(window).height() < $dp.offset().top + $dp.height()) {
            $dp.offset({top: $dp.offset().top - $dp.height() - 32});
            $dp.find(".bui-calendar").addClass("bui-calendar2");
        } else {
            $dp.find(".bui-calendar").removeClass("bui-calendar2");
        }
    });
    //End - DatePicker
}

/**
 * 增强的日期Render
 * @param d Date
 */
function dateRendererEnhance(d) {

    function formatTimeUnit(v) {
        if (v < 10) {
            return '0' + v;
        }
        return v;
    }

    if (!d) {
        return '';
    }

    var date = null;

    try {
        if (BUI.isString(d)) {
            date = new Date(d.replace(/[-年月]/g, '/').replace(/[日]/g, '').replace(/[T\s]\d+([:时分秒]\d+){1,2}([.]\d+){0,1}/g, ''));
        } else {
            date = new Date(d);
        }

    } catch (e) {
        return '';
    }
    if (!date || !date.getFullYear) {
        return '';
    }
    return date.getFullYear() + '-' + formatTimeUnit(date.getMonth() + 1) + '-' + formatTimeUnit(date.getDate());

}
