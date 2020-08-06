package com.west.domain.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * description: [德鲁伊连接池配置]
 * title: DBConfig
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/16
 */
@Slf4j
@Configuration
public class DBConfig {

    // 监控平台地址 http://ip:port/sys_code/druid/index.html

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
        System.out.println("init druidDataSource by domain");
        log.debug("init druidDataSource by domain");
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean startViewServlet()
    {
        //参数可以在StatViewServlet和ResourceServlet中查看
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> params = new HashMap<>();
        // 有空写成配置
        //用户名
        params.put("loginUsername","admin");
        //密码
        params.put("loginPassword","123456");
        //IP白名单 (不填写代表允许所有IP)
        params.put("allow","10.135.125.96");
        //IP黑名单 (存在共同时，deny优先于allow)
        //initParameters.put("deny", "192.168.20.38");
        bean.setInitParameters(params);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        //排除拦截
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}
