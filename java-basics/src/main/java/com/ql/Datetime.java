package com.ql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/6/19 4:13 下午
 **/
public class Datetime {

    public static void main(String[] args) {
//        LocalDateTime before = LocalDate.now().minusDays(30 - 1).atStartOfDay();
//        long thirtyDaysBeforeTime = Timestamp.valueOf(before).getTime();
//        System.out.println(thirtyDaysBeforeTime);
//        //今天
//        LocalDate today = LocalDate.now();
//        LocalDate yest = LocalDate.of(2020, 06, 18);
//        //绑定时间
//        LocalDate bindDay = TimeUtil.convert(1589990400000l).toLocalDate();
//        //绑定后第几天（从绑定当天开始算起）
//        int currentDay = Period.between(yest, today).getDays();
//        System.out.println(currentDay);
//        LocalDate today2 = LocalDate.now();
//        LocalDate yest2 = LocalDate.of(2020, 06, 21);
//        System.out.println(yest2.until(today2, ChronoUnit.DAYS));
        LocalDate bindDay = LocalDate.of(2019, 06, 16);
        LocalDate bindDay2 = LocalDate.of(2020, 06, 17);
//        LocalDate today = LocalDate.of(2020, 07, 23);
//        LocalDate today = LocalDate.of(2020, 06, 23);
//        //int currentDay = (int) Period.between(bindDay, today).get(ChronoUnit.DAYS) + 1;
//        int currentDay = (int) ChronoUnit.DAYS.between(bindDay, today) + 1;
//        System.out.println(currentDay);
//        System.out.println(bindDay.plusDays(29));
        System.out.println(bindDay.until(bindDay2, ChronoUnit.DAYS));
    }
}
