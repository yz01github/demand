package com.west.business.util;

import org.springframework.context.ApplicationContext;

/**
 * description: [Spring上下文工具类]
 * title: SpringContextUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/3
 */
public class SpringContextUtils {

    private static ApplicationContext context;

    public static void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }
}
