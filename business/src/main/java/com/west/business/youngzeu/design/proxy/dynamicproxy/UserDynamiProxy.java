package com.west.business.youngzeu.design.proxy.dynamicproxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: [动态代理类，编写动态代理额外的增强逻辑
 * 1. 实现 InvocationHandler
 * 2. 构造器传入被代理的对象
 * 3. 获取增强了逻辑的代理对象
 * ]
 * title: UserDynamiProxy
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserDynamiProxy implements InvocationHandler {

    private UserDynamiService userDynamiService;

    public UserDynamiProxy(UserDynamiService userDynamiService){
        // 1. 构造器传入被代理对象
        this.userDynamiService = userDynamiService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // a. before 增强逻辑
        log.debug("UserDynamiProxy.doWork: 增强逻辑实现before,args:{}", args);

        // b. 调用核心逻辑,注意这里是调用被代理对象的方法
        Object result = method.invoke(userDynamiService, args);

        // c. after 增强逻辑
        log.debug("UserDynamiProxy.doWork: 增强逻辑实现after,args:{}", args);

        return result;
    }

    /**
     * description: [传入业务核心逻辑对象,返回带有增强逻辑的代理对象
     * 增强逻辑见{@link #invoke(Object, Method, Object[])}]
     * @param   userCoreImpl    业务核心逻辑对象
     * @return  UserDynamiInterface 增强了实现的代码
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/29
     */
    public static UserDynamiInterface getProxyInstace(UserDynamiService userCoreImpl){
        ClassLoader classLoader = userCoreImpl.getClass().getClassLoader();
        Class<?>[] interfaces = userCoreImpl.getClass().getInterfaces();
        // 这里通过核心逻辑实现类对象,创建一个代理类对象
        UserDynamiProxy proxy = new UserDynamiProxy(userCoreImpl);
        UserDynamiInterface userProxy = (UserDynamiInterface)Proxy.newProxyInstance(classLoader, interfaces, proxy);
        return userProxy;
    }
}
