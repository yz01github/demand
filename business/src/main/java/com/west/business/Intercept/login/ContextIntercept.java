package com.west.business.intercept.login;

import com.west.business.config.ThreadContext;
import com.west.business.intercept.BaseIntercept;
import com.west.business.pojo.dto.context.URP;
import com.west.domain.entity.PermissionEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * description: []
 * title: ContentIntercept
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2022/12/27
 */
@Slf4j
public class ContextIntercept  extends BaseIntercept {

    @PostConstruct
    public void initPath(){
        log.debug("ContextIntercept.initPath begin...");
        // 拦截器路径加载, 优先缓存获取,这块理论上应该新建一个拦截器的拦截路径配置表,目前暂时写死
        super.setAddPaths(java.util.Arrays.asList("/**"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ThreadContext.setVisitRequest(request);
        ThreadContext.setVisitResponse(response);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadContext.remove();
    }
}
