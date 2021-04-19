package com.west.business.config;

import com.west.business.annotation.ResultAdvice;
import com.west.business.pojo.pub.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Objects;
import java.util.Arrays;

/**
 * description: [拦截 Controller 返回参数，做统一返回数据处理，配合 ResResult.class 使用]
 * title: ResResultAdvice
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/16
 */
@Slf4j
@RestControllerAdvice
public class ResResultAdvice implements ResponseBodyAdvice {

    /**
     * description: [是否拦截此方法]
     * @param   methodParameter 方法参数
     * @param   clazz 类对象
     * @return  true-拦截此返回并处理， false-不拦截处理
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/16
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        Class<?> declaringClass = methodParameter.getDeclaringClass();
        RequestMapping annotation = declaringClass.getAnnotation(RequestMapping.class);
        if(Objects.nonNull(annotation)){
            String[] controPrefixURI = annotation.value();
            List<String> list = Arrays.asList("/user", "/demand");
            // 如果包含，就不处理返回结果
            if(list.contains(controPrefixURI[0])){
                return false;
            }
        }
        return methodParameter.hasMethodAnnotation(ResultAdvice.class);
    }

    /**
     * description: [拦截处理逻辑]
     * @param   body   返回的数据
     * @param   methodParameter
     * @param   mediaType
     * @param   clazz
     * @param   serverHttpRequest
     * @param   serverHttpResponse
     * @return  Object  包装后的数据
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/19
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class clazz,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        return ResResult.successAddData(body);
    }
}
