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

    public int getOrder() {
        return 10;// 切面优先级,越小越优先
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            " @annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping))")
    public void requestPoint(){}


    @Before("requestPoint()")
    public void doBefor(JoinPoint point){
        HttpServletRequest request = getRequest();
        log.info("请求URL:{} 开始!",request.getRequestURL());
        log.info("请求入参:{} ", Arrays.toString(point.getArgs()));
    }

    //后置正常通知
    @AfterReturning(returning = "ret", pointcut = "requestPoint()")
    public void doAfterReturning(Object ret) throws Throwable {
        HttpServletRequest request = getRequest();
        log.info("请求URL:{} 结束!",request.getRequestURL());
        log.info("请求结果:{} ", ret);
    }

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
