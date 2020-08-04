package com.west.business.pojo.vo.user;

import javax.validation.constraints.NotBlank;

/**
 * description: []
 * title: CreateUserVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
public class CreateUserVO {

    // 用户账号
    @NotBlank
    private String userAccount;

    // 用户密码
    @NotBlank
    private String userPasswd;

    // 用户姓名
    @NotBlank
    private String userName;

    // 用户邮箱
    private String userEmail;

    // 用户部门编码
    private String userDepart;
}
