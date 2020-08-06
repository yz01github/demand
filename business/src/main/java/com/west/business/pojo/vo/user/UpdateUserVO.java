package com.west.business.pojo.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description: []
 * title: CreateUserVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
@Data
public class UpdateUserVO {

    // 用户唯一标识
    @NotBlank
    private String userId;

    // 用户账号
    private String userAccount;

    // 用户密码
    private String userPasswd;

    // 用户姓名
    private String userName;

    // 用户邮箱
    private String userEmail;

    // 用户部门编码
    private String userDepart;
}
