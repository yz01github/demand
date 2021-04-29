package com.west.business.youngzeu.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * description: [业务实现类，正常业务场景下，这个作为业务核心功能逻辑]
 * title: UserStaticImpl
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserStaticImpl implements UserStaticInterface {

    @Override
    public Object doWork(Object args) {
        log.debug("UserStaticImpl.doWork: 核心业务实现,args:{}", args);
        return args;
    }
}
