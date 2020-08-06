package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * description: [Http请求工具类]
 * title: HttpUtil
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class HttpUtil {

    private static RestTemplate client = new RestTemplate();

    public static <P> Object sendPostRequest(String url, P params) {
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // json方式提交, 也可以表单,需要的自己再封装一个
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 构建请求对象
        HttpEntity<P> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用Response类格式化
        ResponseEntity<Object> response = client.exchange(url, method, requestEntity, Object.class);
        return response.getBody();
    }
}
