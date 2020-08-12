package com.west.business.service.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.pojo.vo.user.LoginUserVO;
import com.west.business.pojo.vo.user.QueryUserVO;
import com.west.business.pojo.vo.user.UpdateUserVO;
import com.west.business.util.GeneratorUtil;
import com.west.business.util.MD5Util;
import com.west.domain.dao.UserDao;
import com.west.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * description: []
 * title: UserServiceImpl
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/5
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_PASSWORD = "1qaz!QAZ";

    @Autowired
    private UserDao userDao;

    @Override
    public int CreateUser(CreateUserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setUserId(GeneratorUtil.getUUID());
        String userSalt = GeneratorUtil.getUUID();
        user.setUserSalt(userSalt);
        if(StringUtils.isBlank(user.getUserPasswd())){
            user.setUserPasswd(MD5Util.md5(DEFAULT_PASSWORD, userSalt));
        }else{
            user.setUserPasswd(MD5Util.md5(user.getUserPasswd(), userSalt));
        }
        return userDao.insert(user);
    }

    @Override
    public IPage<QueryUserVO> qryAllPage(QueryUserVO userVO, PageVO<QueryUserVO> pageVO) {
        QueryWrapper<User> wrapper = buildQryWrapper(userVO);
        Page<User> page = new Page<>(pageVO.getPageNum(), pageVO.getPageSize());
        IPage<User> iPage = userDao.selectPage(page, wrapper);
        IPage<QueryUserVO> convertedIPage = iPage.convert(obj2vo());
        return convertedIPage;
    }

    @Override
    public List<QueryUserVO> qryByCond(QueryUserVO userVO) {
        QueryWrapper<User> wrapper = buildQryWrapper(userVO);
        List<User> users = userDao.selectList(wrapper);
        List<QueryUserVO> userVOS = users2VOs(users);
        return userVOS;
    }

    @Override
    public int updateUser(UpdateUserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        String userId = user.getUserId();
        user.setIsDelete(null);// 防止恶意调用
        user.setUserPasswd(null);// 密码修改走单独接口逻辑
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("USER_ID", userId);
        return userDao.update(user, wrapper);
    }

    @Override
    public int deleteUser(String userId) {
        Wrapper<User> wrapper = new QueryWrapper<User>().eq("USER_ID", userId);
        return userDao.delete(wrapper);
    }

    @Override
    public List<QueryUserVO> qryAll() {
        return qryByCond(new QueryUserVO());
    }

    @Override
    public LoginUserVO login(LoginUserVO userVO) {
        String userAccount = userVO.getUserAccount();
        // 1. 查找用户信息
        QueryUserVO qryVO = new QueryUserVO();
        qryVO.setUserAccount(userAccount);
        List<User> users = userDao.selectList(buildQryWrapper(qryVO));

        // 匹配用户信息
        User userInfo = new User();
        if(CollectionUtils.isEmpty(users)){
            userVO.setLoginSuccess(false);
            userVO.setMessage("无该用户,登录失败!");
        }else if(users.size() > 1){
            userVO.setLoginSuccess(false);
            userVO.setMessage("处理失败;查询到多条用户信息,请反馈管理员处理!");
        }else {
            userInfo = users.get(0);
            String md5Passwd = MD5Util.md5(userVO.getUserPasswd() + userInfo.getUserSalt());
            boolean isLoginResult = Objects.equals(userInfo.getUserPasswd(), md5Passwd);
            userVO.setLoginSuccess(isLoginResult);
            userVO.setMessage(isLoginResult ? "登录成功!" : "登陆失败, 账号密码不匹配!");
            userVO.setUserId(userInfo.getUserId());
        }

        // TODO salt 处理

        return userVO;
    }

    // 重置密码
    @Override
    public boolean resetPassword(CreateUserVO userVO) {
        // 1. 查找用户信息
        QueryUserVO qryVO = new QueryUserVO();
        qryVO.setUserAccount(userVO.getUserAccount());
        User user = userDao.selectOne(buildQryWrapper(qryVO));
        if(Objects.isNull(user)){
            log.error("重置密码失败,无该用户:{}", userVO.getUserAccount());
            return false;
        }
        String userSalt = user.getUserSalt();
        String userId = user.getUserId();
        user.setUserPasswd(MD5Util.md5(DEFAULT_PASSWORD, userSalt));
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>().eq("USER_ID", userId);
        return userDao.update(user, wrapper) > 0;
    }

    // 一键初始化
    public boolean resetAllPass() {
        List<User> users = userDao.selectList(null);
        users.forEach(o -> {
            log.error("用户id:{}; 原加密后密码:{}", o.getId(), o.getUserPasswd());
            o.setUserPasswd(MD5Util.md5(DEFAULT_PASSWORD, o.getUserSalt()));
            userDao.updateById(o);
        });
        return true;
    }

    private List<QueryUserVO> users2VOs(List<User> users){
        return users.stream().map(obj2vo()).collect(Collectors.toList());
    }

    private Function<User, QueryUserVO> obj2vo(){
        return o -> {
            QueryUserVO userVO = new QueryUserVO();
            BeanUtils.copyProperties(o, userVO);
            return userVO;
        };
    }

    private QueryWrapper<User> buildQryWrapper(QueryUserVO userVO){
        String userId = userVO.getUserId();
        String userName = userVO.getUserName();
        String userAccount = userVO.getUserAccount();
        return new QueryWrapper<User>()
                .eq(StringUtils.isNotBlank(userId), "USER_ID", userId)
                .eq(StringUtils.isNotBlank(userName), "USER_NAME", userName)
                .eq(StringUtils.isNotBlank(userAccount), "USER_ACCOUNT", userAccount);
    }
}
