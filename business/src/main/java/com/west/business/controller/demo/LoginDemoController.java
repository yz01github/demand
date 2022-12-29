package com.west.business.controller.demo;

import com.west.business.annotation.ResultAdvice;
import com.west.business.pojo.vo.user.LoginUserVO;
import com.west.business.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: []
 * title: LoginDemoController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2022/12/27
 */
@Api(tags = "登录测试Demo")
@Slf4j
@RestController
@RequestMapping("/login")
@ResultAdvice
public class LoginDemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/{str}")
    public LoginUserVO aopTest(@PathVariable("str") String str, HttpServletRequest request, HttpServletResponse responce) throws MethodArgumentNotValidException {
        LoginUserVO loginUserVO = LoginUserVO.builder()
                .userAccount(StringUtils.isBlank(str) ? "yangzhi" : str)
                .userPasswd("1qaz!QAZ").build();
        LoginUserVO userVO = userService.login(loginUserVO,request,responce);
        return userVO;
    }
}
