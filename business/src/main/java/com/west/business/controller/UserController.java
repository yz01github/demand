package com.west.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.pojo.vo.user.QueryUserVO;
import com.west.business.pojo.vo.user.UpdateUserVO;
import com.west.business.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

    @Autowired
    private UserService userService;

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

    @ApiOperation("删除单个用户")
    @GetMapping("/one")
    public ResResult<List<QueryUserVO>> searchByName(@NotBlank String name){
        List<QueryUserVO> voList = userService.searchByName(name);
        return ResResult.successAddData(voList);
    }

}
