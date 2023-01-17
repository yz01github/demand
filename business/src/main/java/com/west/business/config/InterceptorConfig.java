package com.west.business.config;

import com.west.business.intercept.BaseIntercept;
import com.west.business.intercept.login.ContextIntercept;
import com.west.business.intercept.login.LoginIntercept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
        // registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");

        BaseIntercept intercept = getLoginIntercept();
        log.debug("InterceptorConfig.addInterceptors;intercept addPaths:{},excludePaths:{}"
            , intercept.getAddPaths(), intercept.getExcludePaths());
        // 功能(菜单)权限拦截器, 兼顾处理了登录拦截功能
//        registry.addInterceptor(intercept)
//                .excludePathPatterns(intercept.getExcludePaths())
//                .addPathPatterns(intercept.getAddPaths())
//                .order(100);
        // 上下文信息设置拦截器
        ContextIntercept contextIntercept = new ContextIntercept();
        registry.addInterceptor(contextIntercept)
                .excludePathPatterns(contextIntercept.getExcludePaths())
                .addPathPatterns(contextIntercept.getAddPaths())
                .order(101);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    @Lazy(false)
    public BaseIntercept getLoginIntercept(){
        return new LoginIntercept();
    }
}
