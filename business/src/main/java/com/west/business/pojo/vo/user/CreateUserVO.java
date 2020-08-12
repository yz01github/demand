package com.west.business.pojo.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * description: []
 * title: CreateUserVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
@Data
public class CreateUserVO {

    // 用户账号
    @Size(min = 4, max = 16)
    private String userAccount;

    // 用户密码
    private String userPasswd;

    // 用户姓名
    @NotBlank
    private String userName;

    // 用户邮箱
    private String userEmail;

    // 用户部门编码
    private String userDepart;
}
