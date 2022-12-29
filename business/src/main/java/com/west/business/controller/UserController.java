package com.west.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.annotation.ResultAdvice;
import com.west.business.config.ThreadContext;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.pojo.vo.user.LoginUserVO;
import com.west.business.pojo.vo.user.QueryUserVO;
import com.west.business.pojo.vo.user.UpdateUserVO;
import com.west.business.service.user.UserService;
import com.west.business.util.GeneratorUtil;
import com.west.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private int loginTTL = 60 * 60 * 30;

    @Autowired
    private UserService userService;

    /**
     * description: [ TODO 这个接口尚待完善,目前已知问题: 登录时产生了两个cookie_token, 另外,退出登录接口还没写]
     * [登录后session:{userId:${userId}, ${userId}_token:UUID},
     *  登录后cookie:{${userId}_token:UUID}]
     * @param   userVO  登录信息
     * @return  ResResult   登录结果;成功时返回用户相关信息
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/8/9
     */
    @PostMapping("/login")
    public ResResult login(@RequestBody LoginUserVO userVO, HttpServletRequest request, HttpServletResponse responce) {
        // 1. 验证用户密码是否正确
        LoginUserVO loginUserVO = userService.login(userVO, request, responce);
        boolean success = loginUserVO.isLoginSuccess();

        // 处理 session,cookie,redis
        if(success){
            String token = GeneratorUtil.getUUID();
            String userId = loginUserVO.getUserId();
            HttpSession session = request.getSession();
            // 失效旧登录信息
            Object oldUser = session.getAttribute("userId");
            if(Objects.nonNull(oldUser)){
                String oldUserId = oldUser.toString();
                String oldUserToken = oldUserId+"_token";
                session.setAttribute(oldUserToken, null);
                Cookie[] cookies = request.getCookies();
                if(Objects.nonNull(cookies)){
                    Cookie cookie = Arrays.asList(cookies).stream()
                            .filter(o -> Objects.equals(o.getName(), oldUserToken))
                            .map(o -> {o.setValue(null);return o;})
                            .findFirst()
                            .orElse(new Cookie(oldUserToken, null));
                    responce.addCookie(cookie);
                }
            }

            // 新增登录信息
            String userToken = userId+"_token";
            request.getSession().setAttribute("userId", userId);
            request.getSession().setAttribute(userToken, token);
            Cookie cookie = new Cookie(userToken, token);
            cookie.setMaxAge(loginTTL);// 单位:秒
            responce.addCookie(cookie);
            // TODO 后期要放在redis中, 每次有访问时,拦截器刷新该生命周期
            return ResResult.successAddMessage(loginUserVO.getMessage());
        }else{
            return ResResult.failAddMessage(loginUserVO.getMessage());
        }

    }

    @GetMapping("/")
    public ResResult testLogin(HttpServletRequest request, HttpServletResponse responce) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.debug("未登录,不允许该操作");
            return ResResult.successAddMessage("未登录,不允许该操作");
        }
        String userId = (String) request.getSession().getAttribute("userId");
        if(StringUtils.isBlank(userId)){
            return ResResult.successAddMessage("登录过期,请重新登录");
        }
        String loginKey = userId+"_token";
        Object token = request.getSession().getAttribute(loginKey);
        boolean isLogin = Arrays.asList(cookies).stream()
                .anyMatch(c -> (loginKey.equals(c.getName()) && Objects.equals(c.getValue(), token)))
                ;
        if(isLogin){
            // TODO 下面写业务
            log.debug("登陆成功");
        }
        QueryUserVO userVO = new QueryUserVO();
        userVO.setUserId(userId.toString());
        List<QueryUserVO> queryUserVOS = userService.qryByCond(userVO);
        return ResResult.successAddData(queryUserVOS.get(0));
    }

    /**
     * description: [退出出登录]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/11
     */
    @GetMapping("/logout")
    public ResResult logout(HttpServletRequest request, HttpServletResponse responce) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(StringUtils.isBlank(userId)){
            ResResult.success("未登录或登录过期,无需退出登录!");
        }
        String userToken = userId+"_token";
        request.getSession().setAttribute("userId", null);
        request.getSession().setAttribute(userToken, null);
        Cookie cookie = new Cookie(userToken, null);
        responce.addCookie(cookie);
        return ResResult.success();
    }

    @ApiOperation("新增用户接口")
    @PostMapping("/info")
    public ResResult<Integer> craeteUser(@Valid @RequestBody CreateUserVO userVO){
        int resNum = userService.CreateUser(userVO);
        return ResResult.successAddData(resNum);
    }

    @ApiOperation("分页查询所有用户")
    @GetMapping("/page")
    public ResResult<IPage<QueryUserVO>> qryAllPage(@Valid @RequestParam QueryUserVO userVO, PageVO<QueryUserVO> pageVO){
        IPage<QueryUserVO> iPage = userService.qryAllPage(userVO, pageVO);
        return ResResult.successAddData(iPage);
    }

    @ApiOperation("查询所有用户(不分页)")
    @GetMapping("/all")
    public ResResult<List<QueryUserVO>> qryAll(){
        List<QueryUserVO> userVOs = userService.qryAll();
        return ResResult.successAddData(userVOs);
    }

    @ApiOperation("更新单个用户")
    @PutMapping("/")
    public ResResult<Integer> updateUser(@Valid @RequestBody UpdateUserVO userVO){
        int updateNum = userService.updateUser(userVO);
        return ResResult.successAddData(updateNum);
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("/{userId}")
    public ResResult<Integer> updateUser(@PathVariable("userId") String userId){
        int deleteNum = userService.deleteUser(userId);
        return ResResult.successAddData(deleteNum);
    }

    @ApiOperation("查询单个用户")
    @GetMapping("/one/{name}")
    public ResResult<List<QueryUserVO>> searchByName(@NotBlank @PathVariable String name, HttpServletRequest request, HttpServletResponse response){
        log.debug("当前是否登录:{}", testLogin(request, response));
        log.debug("当前登录用户:{}", request.getSession().getAttribute("userName"));
        QueryUserVO userVO = new QueryUserVO();
        userVO.setUserName(name);
        List<QueryUserVO> voList = userService.qryByCond(userVO);
        return ResResult.successAddData(voList);
    }

    @ApiOperation("重置密码接口")
    @PostMapping("/reset")
    public ResResult resetPassword(@Valid CreateUserVO userVO){
        boolean isSuccess = userService.resetPassword(userVO);
        return ResResult.result(isSuccess);
    }
    @ApiOperation("一键重置所有用户密码接口,慎用!!!")
    @PostMapping("/resetAll")
    public ResResult<Boolean> resetPassword(){
        boolean isSuccess = userService.resetAllPass();
        return ResResult.result(isSuccess);
    }


}
