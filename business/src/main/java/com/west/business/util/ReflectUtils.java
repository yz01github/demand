package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: [反射工具类]
 * title: ReflectUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/3/3
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtils {

    /**
     * 缓存方法
     */
    private static final Map<Class<?>, Method[]> METHODS_CACHE = new ConcurrentHashMap<>();

    /**
     * description: [反射调用指定方法, 忽略权限修饰符]
     * @param   obj             调用方法的实例对象
     * @param   methodName      方法名
     * @param   parameterTypes  参数列表类型
     * @param   params          参数值
     * @return  Object:调用的方法的返回
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/3/3There is no method B found in Class A
     */
    public static Object invokeMethod(final Object obj,
                                      final String methodName,
                                      final Class<?>[] parameterTypes,
                                      final Object[] params) {
        Method method = findMethod(obj, methodName, parameterTypes);
        if (Objects.isNull(method)) {
            throw new IllegalArgumentException("There is no method["+methodName+"] found in Class:"+obj.getClass().getSimpleName()+".");
        }
        try {
            return method.invoke(obj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * description: [获取对象指定方法(递归获取:向上转型直到第一次找到),并设定强制访问]
     * @param obj
     * @param methodName
     * @param parameterTypes
     * @return
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/3/3
     */
    public static Method findMethod(final Object obj, final String methodName, final Class<?>... parameterTypes) {
        // 从本类
        Class<?> clazz = obj.getClass();
        Class<Object> objClass = Object.class;
        for (; clazz != objClass; clazz = clazz.getSuperclass()) {
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException | SecurityException e) {
                // Method不在当前类定义，继续向上转型
            }
        }
        return null;
    }
}

