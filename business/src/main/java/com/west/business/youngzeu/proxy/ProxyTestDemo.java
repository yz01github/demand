package com.west.business.youngzeu.proxy;

import com.west.business.youngzeu.proxy.cglibproxy.UserCglibProxy;
import com.west.business.youngzeu.proxy.cglibproxy.UserCglibService;
import com.west.business.youngzeu.proxy.dynamicproxy.UserDynamiInterface;
import com.west.business.youngzeu.proxy.dynamicproxy.UserDynamiProxy;
import com.west.business.youngzeu.proxy.dynamicproxy.UserDynamiService;
import com.west.business.youngzeu.proxy.staticproxy.UserStaticImpl;
import com.west.business.youngzeu.proxy.staticproxy.UserStaticInterface;
import com.west.business.youngzeu.proxy.staticproxy.UserStaticProxy;

/**
 * description: []
 * title: ProxyTestDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
public class ProxyTestDemo {

    public static void main(String[] args) {
        // staticDemo();
        // dynamicDemo();
        cglibDemo();
    }

    private static void cglibDemo() {
        UserCglibService proxyInstance = (UserCglibService)UserCglibProxy.getProxyInstance(new UserCglibService());
        proxyInstance.doWork("cglib入参");
    }

    private static void dynamicDemo() {
        UserDynamiService dynamiService = new UserDynamiService();
        UserDynamiInterface proxyInstace = UserDynamiProxy.getProxyInstace(dynamiService);
        proxyInstace.doWork("动态代理入参");
    }

    private static void staticDemo() {
        UserStaticInterface userStatic = new UserStaticImpl();
        UserStaticInterface proxy = new UserStaticProxy(userStatic);
        proxy.doWork("参数列表");
    }
}
