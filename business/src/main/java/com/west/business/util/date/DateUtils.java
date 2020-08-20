package com.west.business.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * description: [日期工具类]
 * title: DateUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2019/6/16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static String getSysTime(){
        return LocalDateTime.now().format(FormatUtil.getTimeFormat());
    }

    public static String getSysDate(){
        return LocalDate.now().format(FormatUtil.getDateFormat());
    }

    public static String getSysDateyyyyMMdd(){
        return LocalDate.now().format(FormatUtil.getDateFormatyyyyMMdd());
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

    /*void test(){
        System.out.println(getSysTime());
        System.out.println(getSysDate());
    }*/
}
