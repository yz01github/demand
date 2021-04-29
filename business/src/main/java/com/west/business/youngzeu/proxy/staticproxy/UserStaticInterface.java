package com.west.business.youngzeu.proxy.staticproxy;

/**
 * description: [静态代理时，代理类和业务具体类必须要实现同一个接口(或者继承同一个父类)]
 * title: UserStaticInterface
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
public interface UserStaticInterface {

    Object doWork(Object args);
}
