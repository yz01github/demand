package com.west.business.youngzeu.design.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * description: [CGlib代理核心业务实现类, 不同于动态和静态代理,cglib的核心业务类不需要实现接口
 * cglib也叫子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展,它可以在运行期扩展java类与实现java接口
 * ]
 * title: UserCglibService
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserCglibService {

    public Object doWork(Object args){
        log.debug("UserCglibService.doWork: 核心逻辑实现,args:{}", args);
        return args;
    }
}
