package com.west.business.pojo.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description: []
 * title: LoginUserVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/9
 */
@Data
public class LoginUserVO {

    // 用户账号
    @NotBlank
    private String userAccount;

    // 用户密码
    @NotBlank
    private String userPasswd;

    private String userId;

    private boolean isLoginSuccess;

    private String message;
}
