package com.west.business.Intercept.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.west.business.Intercept.BaseIntercept;
import com.west.business.config.ThreadContext;
import com.west.business.pojo.dto.context.URP;
import com.west.business.pojo.pub.ResResult;
import com.west.business.service.perm.PermService;
import com.west.business.util.date.DateUtils;
import com.west.domain.dao.PermissionDao;
import com.west.domain.dao.RoleDao;
import com.west.domain.dao.RolePermDao;
import com.west.domain.dao.UserDao;
import com.west.domain.dao.UserRoleDao;
import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.RolePermEntity;
import com.west.domain.entity.User;
import com.west.domain.entity.UserRoleEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: []
 * title: LoginIntercept
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/11
 */
@Slf4j
public class LoginIntercept extends BaseIntercept {

    @Getter
    @Setter
    private String fieledMessage;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermDao rolePermDao;

    @Autowired
    private PermService permService;

    /**
     * description: [初始化写入 AddPaths 和 ExcludePaths]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    @PostConstruct
    public void initPath(){
        log.debug("LoginIntercept.initPath begin...");
        // 拦截器路径加载, 优先缓存获取
        List<PermissionEntity> allPerms = permService.qryAllPermList();
        if(CollectionUtils.isEmpty(allPerms)){
            throw new IllegalArgumentException("权限配置为空,系统禁止运行!");
        }
        List<String> permPaths = allPerms.stream().filter(o -> Objects.equals(o.getPermType(), "0"))
                .map(PermissionEntity::getPermPath).collect(Collectors.toList());
        super.setAddPaths(permPaths);
        super.setExcludePaths(Arrays.asList("/excludePaths"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("LoginInterceptor.preHandle begin...;request uri:" + request.getRequestURI());
        // 1. 未登录则直接返回请求无权限,并匹配用户token, 不匹配则返回失败
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        String currUserId = (String) session.getAttribute("userId");
        Cookie[] cookies = request.getCookies();
        // 无 cookie 或 session无userId, 返回请求无权限
        if(StringUtils.isBlank(currUserId) || Objects.isNull(cookies)){
            setFieledMessage("用户未登录!");
            returnJson(request, response);
            return false;
        }
        // 匹配用户token
        String loginKey = currUserId+"_token";
        Object token = session.getAttribute(loginKey);
        boolean loginOk = Arrays.asList(cookies).stream()
                .anyMatch(c -> (loginKey.equals(c.getName()) && Objects.equals(c.getValue(), token)));
        // 登录信息有问题, 也返回请求无权限
        if(!loginOk){
            setFieledMessage("用户登录令牌异常,请重新登录!");
            returnJson(request, response);
            return false;
        }

        // 2. 已登录且token匹配,则匹配用户权限,并存入threadLocal
        initURP(currUserId);
        URP urp = ThreadContext.currURP.get();
        Map<String, PermissionEntity> allHasPerms = urp.getAllHasPerms();
        boolean canRequest = allHasPerms.containsValue(requestURI);
        log.debug("LoginInterceptor.preHandle end...; canRequest:{}", canRequest);
        return canRequest;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

    @Override
    protected ResResult<Map<String, Object>> returnResult(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("USER_ID", request.getSession().getAttribute("userId"));
        resultMap.put("REQ_URI", request.getRequestURI());
        resultMap.put("REQ_TIME", DateUtils.getSysDateyyyyMMdd());
        return ResResult.resResult(HttpStatus.FORBIDDEN.value(), getFieledMessage(), resultMap);
    }


    // 设置当前用户相关信息（角色/权限等）
    private void initURP(String currUserId) {
        URP urp = new URP();
        User user = buildUser(currUserId);
        Map<String, RoleEntity> hasRoles = buildHasRoles(currUserId);
        List<RolePermEntity> rpList = buildRelationRP(hasRoles);
        Map<String, PermissionEntity> allHasPerms = buildAllHasPerms(rpList);

        urp.setUser(user);//当前登录user
        urp.setHasRoles(hasRoles);// 当前用户拥有的所有角色
        urp.setRelationRP(rpList);// 当前用户用的的所有角色对应权限编码
        urp.setAllHasPerms(allHasPerms);// 用户拥有的所有权限

        ThreadContext.currURP.set(urp);
    }

    /**
     * description: [用户拥有的所有权限]
     * @param   rpList  用户拥有的角色list
     * @return  Map<String, PermissionEntity>   用户拥有的所有权限 Map<permId, PermissionEntity>
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    private Map<String, PermissionEntity> buildAllHasPerms(List<RolePermEntity> rpList) {
        if(CollectionUtils.isEmpty(rpList)){
            return Collections.emptyMap();
        }
        List<PermissionEntity> allPerms = permService.qryAllPermList();
        List<String> permIds = rpList.stream().map(RolePermEntity::getPermId).distinct().collect(Collectors.toList());
        // 匹配权限,包装返回
        if(CollectionUtils.isEmpty(allPerms)){
            return Collections.emptyMap();
        }
        return allPerms.stream().filter(o -> permIds.contains(o.getPermId()))
                        .collect(Collectors.toMap(PermissionEntity::getPermId, o->o));
    }

    private List<RolePermEntity> buildRelationRP(Map<String, RoleEntity> hasRoles) {
        if(CollectionUtils.isEmpty(hasRoles)){
            return Collections.emptyList();
        }
        // 查找关联关系
        Set<String> roleIds = hasRoles.keySet();
        QueryWrapper<RolePermEntity> wrapper = new QueryWrapper<>();
        wrapper.in("ROLE_ID", roleIds);
        return rolePermDao.selectList(wrapper);
    }

    /**
     * description: [构建当前用户拥有的所有角色]
     * @param   currUserId  userId
     * @return  Map<String, RoleEntity> 所有该用户拥有的角色信息<roleId, roleInfo>
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    private Map<String, RoleEntity> buildHasRoles(String currUserId) {
        // 查找关联关系
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID", currUserId);
        List<UserRoleEntity> urList = userRoleDao.selectList(wrapper);
        if(CollectionUtils.isEmpty(urList)){
            return Collections.emptyMap();
        }
        // 查找角色信息
        List<String> roleIds = urList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        QueryWrapper<RoleEntity> reWrapper = new QueryWrapper<>();
        reWrapper.in("USER_ID", roleIds);
        List<RoleEntity> roleList = roleDao.selectList(reWrapper);
        if(CollectionUtils.isEmpty(roleList)){
            return Collections.emptyMap();
        }
        // 包装返回
        return roleList.stream().collect(Collectors.toMap(RoleEntity::getRoleId, o -> o));
    }

    /**
     * description: [构建User信息]
     * @param   currUserId  userId
     * @return  User        当前登录的User
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    private User buildUser(String currUserId) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .select("USER_ID", "USER_ACCOUNT", "USER_NAME", "USER_EMAIL", "USER_DEPART")
                .eq("USER_ID", currUserId);
        return userDao.selectOne(wrapper);
    }




}
