package com.west.business.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.pojo.vo.user.QueryUserVO;
import com.west.business.pojo.vo.user.UpdateUserVO;

import java.util.List;

/**
 * description: [用户相关接口]
 * title: UserService
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
public interface UserService {

    // 创建用户
    int CreateUser(CreateUserVO userVO);

    // 查询所有用户,分页
    IPage<QueryUserVO> qryAllPage(QueryUserVO userVO, PageVO<QueryUserVO> pageVO);

    // 搜索单个用户
    List<QueryUserVO> searchByName(String name);

    // 更新用户信息
    int updateUser(UpdateUserVO userVO);

    int deleteUser(String userId);

    List<QueryUserVO> qryAll();
}
