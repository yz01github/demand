package com.west.business.youngzeu.design.design1;

/**
 * description: [Target（目标抽象类）：目标抽象类定义客户所需接口，可以是一个抽象类或接口，也可以是具体类。]
 * title: Target 就是我们自己调用方自己, 适配器模式顺序: 用户 -> target -> afapter -> Adaptee
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/8
 */
public interface Target<T, P> {

    T execute(P params);
}
