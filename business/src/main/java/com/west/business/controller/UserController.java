package com.west.business.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * description: [用户相关接口]
 * title: UserController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/27
 */
@Api(tags = "用户相关接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiIgnore
    @PostMapping("/{str}")
    public void login(@PathVariable String userId, HttpServletRequest request, HttpServletResponse responce) {
        String loginKey = UUID.randomUUID().toString().replace("-", "l");
        request.getSession().setAttribute(userId+"_token", loginKey);
        request.getSession().setAttribute("userId", userId);
        Cookie cookie = new Cookie(userId+"_token", loginKey);
        cookie.setMaxAge(60 * 60);// 1h 过期
        responce.addCookie(cookie);
    }

    @ApiIgnore
    @GetMapping("/")
    public void testLogin(HttpServletRequest request, HttpServletResponse responce) {
        List<Cookie> cookies = Arrays.asList(request.getCookies());
        if(CollectionUtils.isEmpty(cookies)){
            log.debug("未登录,不允许该操作");
            return;
        }
        Object userId = request.getSession().getAttribute("userId");
        String loginKey = userId+"_token";
        Object token = request.getSession().getAttribute(loginKey);
        boolean isLogin = cookies.stream()
                .anyMatch(c -> (loginKey.equals(c.getName()) && Objects.equals(c.getValue(), token)))
                ;
        if(isLogin){
            // TODO 下面写业务
            log.debug("登陆成功");
        }

    }



}
