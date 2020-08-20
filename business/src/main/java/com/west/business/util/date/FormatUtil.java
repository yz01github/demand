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

    public static final String DATE_PATTERN_8 = "yyyyMMdd";
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
    private static final DateTimeFormatter TIME_FORMATTER
            = DateTimeFormatter.ofPattern(TIME_PATTERN);

    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final DateTimeFormatter DATE_FORMATTER_8
            = DateTimeFormatter.ofPattern(DATE_PATTERN_8);

    // 本地电脑不行,懒加载一下
    public static DateTimeFormatter getTimeFormat(){
        return TIME_FORMATTER;
    }

    public static DateTimeFormatter getDateFormat(){
        return DATE_FORMATTER;
    }

    public static DateTimeFormatter getDateFormatyyyyMMdd(){
        return DATE_FORMATTER_8;
    }
}
