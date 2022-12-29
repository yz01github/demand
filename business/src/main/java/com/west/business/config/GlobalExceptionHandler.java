package com.west.business.config;

import com.west.business.pojo.pub.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * description: [RestControllerAdvice: 对controller进行增强，可以：统一异常拦截处理、统一入参更名、入参增加全局数据,
 * @ RestControllerAdvice 包含了 @ControllerAdvice
 * ]
 * title: ExceptionHandler
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/26
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * description: [参数校验失败异常统一拦截]
     * @param   exception   异常信息
     * @return  ResResult   经过包装的统一返回结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/5/10
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)// http状态返回400
    public ResResult globalExceptionDeal(Exception exception) throws Exception {
        try{
            if(exception instanceof MethodArgumentNotValidException){
                MethodArgumentNotValidException validException = (MethodArgumentNotValidException) exception;
                BindingResult result = validException.getBindingResult();
                if(result.hasErrors()){
                    List<FieldError> fieldErrors = result.getFieldErrors();
                    Map<String, String> map = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
                    log.debug("校验结果：{}", map);
                    return ResResult.resResult(ResResult.ResCode.PARAM_ERROR, map);
                }
            }
        }catch (Exception e){
            // TODO 这块后面弄成线程的 sessionId
            String uuid = UUID.randomUUID().toString();
            log.error("{}:全局异常处理失败,原异常:{}", uuid, exception.getMessage());
            log.error("{}:全局异常处理失败,异常处理失败原因:{}", uuid, exception.getMessage());
        }
        // 抛出原异常
        throw exception;
    }

    /**
     * description: [其余异常统一拦截]
     * @param   exception   异常信息
     * @return  ResResult   经过包装的统一返回结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/5/10
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)// http状态返回400
    public ResResult globalExceptionDeal2(Exception exception) throws Exception {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
            exception.printStackTrace(pw);
            System.out.println(sw.toString());
        }catch (Exception e){
            log.error("globalExceptionDeal2.print.exception:"+e);
        }finally {
            sw.close();
            sw=null;
            pw.close();
            pw=null;
        }
        String uuid = UUID.randomUUID().toString();
        log.error("{}:globalExceptionDeal2,原异常:{}", uuid, exception.getMessage());
        log.error("{}:globalExceptionDeal2,异常处理失败原因:{}", uuid, exception.getMessage());
        return ResResult.failAddMessage(exception.getMessage());
    }
}
