package com.west.business.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * description: []
 * title: LoginUserVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
