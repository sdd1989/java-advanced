package com.ql;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/8/31 5:58 下午
 **/
public class LocalDateMain {

    public static void main(String[] args) {
        //System.out.println(calcAge("1989-01-16"));
//        Period period = Period.between(LocalDate.of(1989, 01, 16), LocalDate.now());
//        System.out.println(period.getDays());
//        System.out.println(period.getMonths());
//        System.out.println(period.getYears());
//        System.out.println(period.toTotalMonths());
        Instant now = Instant.now();
        Instant instant = now.plus(Duration.ofDays(10));
        Duration duration = Duration.between(now, instant);
        System.out.println(duration.toHours());
        System.out.println(duration.toDays());
        System.out.println(duration.getSeconds());
    }

    private static Byte calcAge(String birthday) {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(birthday);
        return (byte) birthDate.until(now, ChronoUnit.YEARS);
    }
}
