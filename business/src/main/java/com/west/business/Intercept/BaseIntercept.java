package com.west.business.Intercept;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

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

    private List<String> addPaths;

    private List<String> excludePaths;
}
