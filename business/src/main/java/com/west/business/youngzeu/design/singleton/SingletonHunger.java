package com.west.business.youngzeu.design.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: [饿加载单例]
 * title: SingletonHunger
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/9/29
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonHunger {

    // 构造器私有化，内部声明单例对象
    private static SingletonHunger instance;

    static {
        // 饿加载，静态代码块初始化
        instance = new SingletonHunger();
    }

    public static SingletonHunger getInstance(){
        return instance;
    }

}
