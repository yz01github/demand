package com.west.business.util;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * description: [日志打印工具类, 暂不提供ERROR级别方法(陕西生产出过问题,不建议使用)]
 * title: LogUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/7/28
 */
@Slf4j
public final class LogUtils {

    // 工具类不建议实例化
    private LogUtils(){}

    public static void main(String[] args) {
        LogUtils.debug(log, "aaa");
        LogUtils.debug(log, "aaa", "bbb");
    }

    private static final String BLANK_STR = "";

    /**
     * description: [单条debug日志打印, 如果message由多条字符串拼接，建议使用 {@link #debug(Logger, String...)}]
     * Exemple: LoggerUtils.debug(log, "这是一段日志"); // 打印结果: 这是一段日志
     * @param   log         日志对象
     * @param   message    需拼装打印的日志信息
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/7/28
     */
    public static void debug(Logger log, String message){
        if(log.isDebugEnabled()){
            log.debug(message);
        }
    }

    /**
     * description: [考虑字符拼接性能消耗，拼接操作不在调用方操作，由此方法封装]
     * Exemple: LoggerUtils.debug(log, "这是", "一段", "日志"); // 打印结果: 这是一段日志
     * @param   log         日志对象
     * @param   messages    需拼装打印的日志信息
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/7/28
     */
    public static void debug(Logger log, String... messages){
        if(log.isDebugEnabled()){
            String message = buildMessage(messages);
            log.debug(message);
        }
    }

    /**
     * @see #debug(Logger, String)
     */
    public static void info(Logger log, String message){
        if(log.isInfoEnabled()){
            log.info(message);
        }
    }

    /**
     * @see #debug(Logger, String...)
     */
    public static void info(Logger log, String... messages){
        if(log.isInfoEnabled()){
            String message = buildMessage(messages);
            log.info(message);
        }
    }

    /**
     * description: [构建日志信息]
     * @param   messages    多个要拼接的字符串，顺序拼接
     * @return  String      拼接完成的字符串
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/7/28
     */
    private static String buildMessage(String... messages) {
        // 缩减对其余项目、jar的依赖，提高多项目通用性
        if(messages == null || messages.length == 0){
            return BLANK_STR;
        }

        /**
         * 经过10W数量级别循环测试，当拼接数小于等于3时,由于创建SringBuilder也存在开销，直接拼接较快
         * 大于3条String拼接时,即使存在创建对象开销,也比直接拼接要快,且这个快的程度与拼接字符数量正相关
         */
        if(messages.length <= 3){
            String resStr = BLANK_STR;
            for(String str : messages){
                resStr += str;
            }
            return resStr;
        }

        StringBuilder builder = new StringBuilder();
        for(String str : messages){
            builder.append(str);
        }
        return builder.toString();
    }
}
