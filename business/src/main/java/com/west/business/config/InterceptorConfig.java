package com.west.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * description: []
 * title: InterceptorConfig
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/11
 */
@Slf4j
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns(用于添加拦截规则);excludePathPatterns(用户排除拦截)
        /* // 功能(菜单)权限拦截器, 兼顾处理了登录拦截功能
        BaseIntercept intercept = getLoginIntercept();
        log.debug("InterceptorConfig.addInterceptors;intercept addPaths:{},excludePaths:{}"
            , intercept.getAddPaths(), intercept.getExcludePaths());
        registry.addInterceptor(intercept)
                .excludePathPatterns(intercept.getExcludePaths())
                .addPathPatterns(intercept.getAddPaths());
                */
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}
