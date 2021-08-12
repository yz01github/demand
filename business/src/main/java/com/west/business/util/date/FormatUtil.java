package com.west.business.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

/**
 * description: [格式化工具类]
 * title: DateFormat
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2019/6/16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormatUtil {

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";

    public static final String TIME_PATTERN_D = "yyyyMMdd";
    public static final String TIME_PATTERN_H = "yyyyMMddHH";
    public static final String TIME_PATTERN_M = "yyyyMMddHHmm";
    public static final String TIME_PATTERN_S = "yyyyMMddHHmmss";
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";

    /**
     * SimpleDateFormat并不是线程安全的,因为在 SimpleDateFormat 中持有一个Calendar类对象在Parse 和Format方法时会调用calendar.setTime(date)方法,如果在多线程环境下运行,会出现线程安全问题
     * 解决方法之一:使用ThreadLocal初始化一个 SimpleDateFormat
     * JDK8及以上：DateTimeFormatter 替换 SimpleDateFormat；DateTimeFormatter是线程安全的；
     *
     * <code>
     *     LocalDateTime.now().format(TIME_FORMATTER) // 日期转字符
     *     LocalDateTime.parse("2020-06-16 10:52:31", TIME_FORMATTER); // 字符转日期
     *</code>
     */
    public static DateTimeFormatter getTimeFormat(){
        return DateTimeFormatter.ofPattern(TIME_PATTERN);
    }

    public static DateTimeFormatter getDateFormat(){
        return DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    public static DateTimeFormatter getFormatYYYYMMddHHmmss(){
        return DateTimeFormatter.ofPattern(TIME_PATTERN_S);
    }
}
