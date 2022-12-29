package com.west.business.controller.demo;

/**
 * description: []
 * title: ExceptionInterceptorDemoController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2022/12/27
 */

import com.west.business.annotation.ResultAdvice;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "异常Demo")
@Slf4j
@RestController
@RequestMapping("/exception")
@ResultAdvice
public class ExceptionInterceptorDemoController {

    @GetMapping("/{str}")
    public String aopTest(@PathVariable String str) throws MethodArgumentNotValidException {
        if ("exception1".equals(str)){
            throw new MethodArgumentNotValidException(null,null);
        }
        if ("exception2".equals(str)){
            throw new RuntimeException(str);
        }
        return str;
    }
}
