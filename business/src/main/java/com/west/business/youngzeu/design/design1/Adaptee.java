package com.west.business.youngzeu.design.design1;

/**
 * description: [Adaptee（适配者类）：适配者即被适配的角色，它定义了一个已经存在的接口，
 * 这个接口需要适配，适配者类一般是一个具体类，包含了客户希望使用的业务方法，在某些情况下可能没有适配者类的源代码。]
 * title: Adaptee 适配器模式顺序: 用户 -> target -> afapter -> Adaptee
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/8
 */
public interface Adaptee<T, P> {

    T doWork(P param);
}
