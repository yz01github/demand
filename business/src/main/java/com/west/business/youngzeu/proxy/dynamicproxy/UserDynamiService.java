package com.west.business.youngzeu.proxy.dynamicproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * description: [动态代理：核心业务实现类，必须要实现某一接口]
 * title: UserDynamiInterface
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserDynamiService implements UserDynamiInterface{

    public Object doWork(Object args){
        log.debug("UserDynamiService.doWork: 核心业务实现,args:{}", args);
        return args;
    }
}
