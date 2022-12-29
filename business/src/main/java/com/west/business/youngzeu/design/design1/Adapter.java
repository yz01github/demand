package com.west.business.youngzeu.design.design1;

/**
 * description: [Adapter（适配器类）：适配器可以调用另一个接口，作为一个转换器，对Adaptee和Target进行适配，
 * 适配器类是适配器模式的核心，在对象适配器中，它通过继承Target并关联一个Adaptee对象使二者产生联系。]
 * title: Adapter 适配器模式顺序: 用户 -> target -> afapter -> Adaptee
 *
 * 对象适配器模式:适配器与适配者之间是关联关系
 * 类适配器模式:适配器与适配者之间是继承（或实现）关系, Adapter extends Adaptee implments Target
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/8
 */
public class Adapter<T, P> {

    public T execute(P param) {
        return null;
    }
}
