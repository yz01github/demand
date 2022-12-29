package com.west.business.intercept;

import com.west.business.pojo.pub.ResResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: []
 * title: BaseIntercept
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/11
 */
@Getter
@Setter
@ToString
public abstract class BaseIntercept implements HandlerInterceptor {

    // 需要拦截的路径
    private List<String> addPaths = new ArrayList<>();

    // 排除的路径(不拦截的)
    private List<String> excludePaths = new ArrayList<>();

    /**
     * description: [拦截器中需要自定义返回时,调用该方法,
     * 并重写{@link #returnResult(HttpServletRequest, HttpServletResponse)}方法即可]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    protected void returnJson(HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(returnResult(request, response));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    /**
     * description: [拦截器返回的结果]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    protected <T> ResResult<T> returnResult(HttpServletRequest request, HttpServletResponse response){
        return ResResult.success();
    }
}
