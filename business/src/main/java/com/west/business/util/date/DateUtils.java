package com.west.business.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * description: [日期工具类]
 * title: DateUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2019/6/16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    /**
     * @return yyyy-MM-dd HH:mm:ss 格式 的时间
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/2
     */
    public static String getSysTime(){
        return LocalDateTime.now().format(FormatUtil.getTimeFormat());
    }

    public static String getSysDate(){
        return LocalDateTime.now().format(FormatUtil.getDateFormat());
    }

    // 获取几天前日期
    public static LocalDate dateAddDay(long days2Add){
        return LocalDate.now().plusDays(days2Add);
    }

    public static LocalDate dateAddDay(LocalDate date, long days2Add){
        return date.plusDays(days2Add);
    }

    // 获取昨天的LocalDate对象
    public static LocalDate getYesterday(){
        return LocalDate.now().plusDays(-1L);
    }

    /**
     * eg: 2020-01-01T10:23:23 ->  2020-01-01
     * @param time
     * @return
     */
    public static LocalDate timeToDate(LocalDateTime time) {
        return time.toLocalDate();
    }

    /**
     * eg: 2020-01-01  ->  2020-01-01T10:23:23
     * @param date
     * @return
     */
    public static LocalDateTime dateToTime(LocalDate date) {
        LocalTime time = LocalDateTime.now().toLocalTime();
        return LocalDateTime.of(date, time);
    }

    public static LocalDateTime stringToTime(String time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * 比较时间先后
     * @param one       时间1
     * @param another   时间2
     * @return 1:one在another时间之后;  -1:one在another时间之前;  0-时间相同
     */
    public static int compare(LocalDateTime one, LocalDateTime another) {
        if (one.isAfter(another)) {
            return 1;
        }
        return one.isBefore(another) ? -1 : 0;
    }

    /**
     * @see #compare(LocalDateTime, LocalDateTime)
     */
    public static int compare(LocalDate one, LocalDate another) {
        if (one.isAfter(another)) {
            return 1;
        }
        return one.isBefore(another) ? -1 : 0;
    }

    /**
     * 获取指定时间所在月份第一天
     * @param time
     * @return
     */
    public static LocalDate getFirstDayMonth(LocalDateTime time) {
        return time.toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * @see #getFirstDayMonth(LocalDate)
     */
    public static LocalDate getFirstDayMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定时间所在月的最后一天
     * @param date
     * @return
     */
    public static LocalDate getLastDayMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * @see #getLastDayMonth(LocalDate)
     */
    public static LocalDate getLastDayMonth(LocalDateTime time) {
        return time.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
    }

    /*void test(){
        System.out.println(getSysTime());
        System.out.println(getSysDate());
    }*/
}
