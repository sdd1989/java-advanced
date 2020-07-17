package com.ql;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by ChenChaohuang on 18-5-25.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String REMOVE_MYSQL_POSTFIX = ".0";

    public static String getMidnight(LocalDateTime now) {
        return LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT).format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parse(String text) {
        if (text.contains(REMOVE_MYSQL_POSTFIX)) {
            text = text.substring(0, text.indexOf(REMOVE_MYSQL_POSTFIX));
        }
        return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
    }

    public static String getTime(LocalDateTime now) {
        return now.format(TIME_FORMATTER);
    }

    /**
     * 把时间戳转换为LocalDateTime
     *
     * @param timestamp 毫秒
     * @return
     */
    public static LocalDateTime convert(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static long maxTimeOfDay(LocalDateTime now) {
        return convert(now.with(LocalTime.MAX));
    }

    public static long minTimeOfDay(LocalDateTime now) {
        return convert(now.with(LocalTime.MIN));
    }

    /**
     * 相隔几个自然天
     * @param from
     * @param to
     * @return
     */
    public static long gap(LocalDateTime from, LocalDateTime to) {
        return to.toLocalDate().toEpochDay() - from.toLocalDate().toEpochDay();
    }

    /**
     * 把LocalDateTime转换为时间戳
     *
     * @param localDateTime
     * @return 时间戳（毫秒）
     */
    public static long convert(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取前一天
     *
     * @return
     */
    public static Date beforeDay(int days) {
        DateTime dateTime = new DateTime();
        dateTime.minusDays(days);
        return dateTime.toDate();
    }

    /**
     * 获取当前日期，格式yyyyMMdd
     *
     * @return
     */
    public static int getDate() {
        DateTime dateTime = new DateTime();
        String data = dateTime.toString("yyyyMMdd");
        return Integer.valueOf(data);
    }
}
