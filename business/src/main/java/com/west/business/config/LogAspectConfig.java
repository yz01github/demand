package com.west.business.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * description: []
 * title: LogAspectConfig
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 *
 * created 2020/8/9
 */
@Slf4j
@Aspect
@Configuration
public class LogAspectConfig {

    private static HttpServletRequest BLANK_REQUEST = new RequestFacade(new Request());

    public int getOrder() {
        return 10;// 切面优先级,越小越优先
    }


    // com.west.business.controller包下所有 *Mapping标注的方法
    @Pointcut("execution(* com.west.business.controller..*.*(..)) && (" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping))" +
            ")")
    public void requestPoint(){}

    /**
     * description: [环绕切面,在切点前后均可执行某些逻辑]
     * @param   pjp     切面入参
     * @return  Object  切点返回值
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/5/11
     */
    @Order(2)// 切面优先级,越小越优先
    @Around("requestPoint()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        /* 通过AOP可以修改切点入参,方法如下
        //获取方法参数值数组
        Object[] args = pjp.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();
        log.info("请求入参:{} ", Arrays.toString(pjp.getArgs()));
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = pjp.proceed(args);
        */
        String uuid = GeneratorUtil.getUUID();
        HttpServletRequest request = getRequest();
        log.info("{}-请求URL:{} 开始!", uuid, request.getRequestURL());
        log.info("{}-请求入参:{} ", uuid, Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        log.info("{}-请求结果:{} ", uuid, result);
        log.info("{}-请求URL:{} 结束!", uuid, request.getRequestURL());
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

    // 获取请求对象
    private HttpServletRequest getRequest(){
        try{
            // 开启Feign的熔断功能 之后,可能会导致获取失败
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return attributes.getRequest();
        }catch(Exception e){
            log.debug("日志切面获取请求对象时发生异常情况,不影响");
            // 非最终最优解决方案:获取失败后,返回一个空的 HttpServletRequest 防止此切面后续打印日志报空指针异常
            return BLANK_REQUEST;
        }
    }
    //@Before("requestPoint()")
    /*public void doBefor(JoinPoint point){
        HttpServletRequest request = getRequest();
        log.info("请求URL:{} 开始!",request.getRequestURL());
        log.info("请求入参:{} ", Arrays.toString(point.getArgs()));
    }*/

    //后置正常通知
    //@AfterReturning(returning = "ret", pointcut = "requestPoint()")
    /*public void doAfterReturning(Object ret) throws Throwable {
        HttpServletRequest request = getRequest();
        log.info("请求URL:{} 结束!",request.getRequestURL());
        log.info("请求结果:{} ", ret);
    }*/

    //后置异常通知
    /*@AfterThrowing("requestPoint()")
    public void throwss(JoinPoint jp){
        log.debug("执行异常 log");
    }*/

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    /*@After("requestPoint()")
    public void doAfter(JoinPoint point){
        log.info("请求finally log");
    }*/

    // 获取请求对象
    private HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }
}
