/**
 * Created by D.Yang on 2014/12/27 0027.
 */
//function countage() {
//    var nowYear = new Date().getFullYear();
//    var age = (nowYear - new Date(data["birthday"]).getFullYear());
//    var totalSeniority = nowYear - new Date(data["workDate"]).getFullYear() + data["extraSeniority"] - data["deductionSeniority"];
//    var totalCourtYear = nowYear - new Date(data["enterDate"]).getFullYear() + data["beforeCourtWorkYear"];
//
//    $("#age").text(age);
//    $("#totalSeniority").text(totalSeniority);
//    $("#totalCourtYear").text(totalCourtYear);
//}

/**
 * 计算年份差
 * @param {type} start
 * @param {type} end
 * @returns {Number}
 */
function calcYears(start, end) {
    if (start)
        start = start.replace(/-/g, "/");
    if (end)
        end = end.replace(/-/g, "/");
    var s = new Date(start);
    var e = end ? new Date(end) : new Date();

    var result = Math.floor((e.getTime() - s.getTime()) / 3600000 / 24 / 365.25);
    return result || 0;
}

/**
 * 计算连续工龄
 * @param {Date} start
 * @param {int} inc
 * @param {int} dec
 * @returns {int}
 */
function workTotalYears(start, inc, dec) {
    inc = inc ? inc : 0;
    dec = dec ? dec : 0;

    return calcYears(start) + inc * 1 - dec * 1;
}