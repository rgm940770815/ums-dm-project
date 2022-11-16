package cn.net.withub.ums.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 16/5/7 0007.
 */
public class DateUtil {

    public static String getDate(Date date){
        return DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);
    }

    public static String getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age+"";
    }

    public static String getAge(String birthDay) throws Exception {
        if (StringUtils.isEmpty(birthDay)) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        String[] day = birthDay.split("-");
        if (day.length == 1) day = birthDay.split(".");
        if (day.length == 1) day = birthDay.split("/");

        int yearBirth = 1;
        int monthBirth = 1;
        int dayOfMonthBirth = 1;

        if (day.length == 1) {
            if (birthDay.length() == 6) {
                yearBirth = Integer.parseInt(birthDay.substring(0, 4));
                monthBirth = Integer.parseInt(birthDay.substring(4, 6));
            } else if (birthDay.length() == 8) {
                yearBirth = Integer.parseInt(birthDay.substring(0, 4));
                monthBirth = Integer.parseInt(birthDay.substring(4, 6));
                dayOfMonthBirth = Integer.parseInt(birthDay.substring(6, 8));
            }
        } else {
            yearBirth = Integer.parseInt(day[0]);
            monthBirth = Integer.parseInt(day[1]);
            dayOfMonthBirth = day.length > 2 ? Integer.parseInt(day[2]) : 1;
        }

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age+"";
    }

}
