package com.west.business.youngzeu.design.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * description: [业务代理类,正常场景中,这个编写核心功能之外的增强功能]
 * title: UserStaticProxy
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserStaticProxy implements UserStaticInterface {

    private UserStaticInterface userStaticImpl;

    public UserStaticProxy(UserStaticInterface userStaticImpl){
        this.userStaticImpl = userStaticImpl;
    }

    @Override
    public Object doWork(Object args) {
        log.debug("UserStaticProxy.doWork: 增强实现begin,args:{}", args);
        userStaticImpl.doWork(args);
        log.debug("UserStaticProxy.doWork: 增强实现end,args:{}", args);
        return null;
    }
}
