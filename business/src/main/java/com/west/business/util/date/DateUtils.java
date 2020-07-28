package com.west.business.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
        return LocalDateTime.now().format(FormatUtil.getDateFormat());
    }
    void test(){
        System.out.println(getSysTime());
        System.out.println(getSysDate());
    }
}
