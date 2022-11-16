package cn.net.withub.ums.action.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cypress on 2016/5/24.
 */
@Component
@Configurable
@EnableScheduling
public class TimerSetting {
//
//    @Autowired
//    XmlAction xmlAction;
//
//    @Scheduled(cron = "0 0 20 1 * ?")
//    public void job1(){
//        xmlAction.setGtype(false);
//        xmlAction.generateXml();
//    }

//    static int count = 0;
//
//    public static void showTimer() {
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                ++count;
//                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次
//            }
//        };
//
//
//        //设置执行时间
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
//        //定制每天的21:09:00执行，
//        calendar.set(year, month, day, 14, 23, 00);
//        Date date = calendar.getTime();
//        Timer timer = new Timer();
//        System.out.println(date);
//
//        int period = 2 * 1000;
////        //每天的date时刻执行task，每隔2秒重复执行
//        timer.schedule(task, date, period);
//        //每天的date时刻执行task, 仅执行一次
////        timer.schedule(task, date);
//    }
//
//
//    public static void main(String[] args) {
//        showTimer();
//    }


}
