package com.west.business.service.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.CreateUserVO;
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

import java.util.List;
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

    @Autowired
    private UserDao userDao;

    @Override
    public int CreateUser(CreateUserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setUserId(GeneratorUtil.getUUID());
        user.setUserSalt(GeneratorUtil.getUUID());
        if(StringUtils.isBlank(user.getUserPasswd())){
            user.setUserPasswd(MD5Util.md5("1qaz!QAZ"));
        }
        return userDao.insert(user);
    }

    @Override
    public IPage<QueryUserVO> qryAllPage(QueryUserVO userVO, PageVO<QueryUserVO> pageVO) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("USER_NAME", userVO.getUserName());
        Page<User> page = new Page<>(pageVO.getPageNum(), pageVO.getPageSize());
        IPage<User> iPage = userDao.selectPage(page, wrapper);
        IPage<QueryUserVO> convertedIPage = iPage.convert(obj2vo());
        return convertedIPage;
    }

    @Override
    public List<QueryUserVO> searchByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("USER_NAME", name);
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
        List<User> userList = userDao.selectList(null);
        return users2VOs(userList);
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

    private QueryWrapper<User> buildQryWrapper(String userId){
        return new QueryWrapper<User>().eq("USER_ID", userId);
    }
}
