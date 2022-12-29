package com.west.business.youngzeu.design.singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: [懒加载单例]
 * title: SingletonLazy
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/9/29
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonLazy {

    private static /*volatile*/ SingletonLazy instance;

    public static SingletonLazy getInstance() {
        if(instance == null){
            // 类锁,因为此时instance还是null，所以锁住整个类
            synchronized (SingletonLazy.class){
                if(instance == null){
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}
