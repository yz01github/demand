package com.west.business.config;

import com.west.business.pojo.pub.ResResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: []
 * title: ExceptionHandler
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/26
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResResult globalExceptionDeal(Exception e){
        return null;
    }
}
