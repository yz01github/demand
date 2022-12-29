package com.west.business.youngzeu.design.proxy.cglibproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * description: [Cglib代理增强实现类
 * 需要实现 org.springframework.cglib.proxy.MethodInterceptor
 * ]
 * title: UserCglibProxy
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@Slf4j
public class UserCglibProxy implements MethodInterceptor {

    private UserCglibService userCglibService;

    public UserCglibProxy(UserCglibService userCglibService){
        this.userCglibService = userCglibService;
    }

    /**
     * description: [增强实现代码逻辑]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/29
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.debug("UserCglibProxy.doWork.obj:{},objects:{},methodProxy:{}", obj, objects, methodProxy);
        log.debug("UserCglibProxy.doWork: 增强逻辑实现 begin,args:{}", objects);
        Object invokeRes = method.invoke(userCglibService, objects);
        log.debug("UserCglibProxy.doWork: 增强逻辑实现 end,args:{}", objects);
        return invokeRes;
    }

    //给目标对象创建一个代理对象
    public static Object getProxyInstance(UserCglibService userCglibService){
        UserCglibProxy userCglibProxy = new UserCglibProxy(userCglibService);
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(userCglibService.getClass());
        //3.设置回调函数
        enhancer.setCallback(userCglibProxy);
        //4.创建子类(代理对象)
        return enhancer.create();

    }
}
