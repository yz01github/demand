package com.west.business.config.cache;

import java.util.Arrays;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


/**
 * description: []
 * title: RedisKeyGeneratorConf
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2023/1/16
 */
@Configuration
public class RedisKeyGeneratorConf {

    @Bean("defaultKeyGenerator")
    public KeyGenerator defaultKeyGenerator(){
        return new KeyGenerator(){

            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"^"+ Arrays.asList(objects).toString()+"^";
            }
        };
    }


}
