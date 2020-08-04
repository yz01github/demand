package com.west.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.west.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

@Data
@TableName(value = "TD_DMP_USER")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    // 用户唯一标识
    private String userId;

    // 用户账号
    private String userAccount;

    // 用户密码
    private String userPasswd;

    // 用户姓名
    private String userName;

    // salt
    private String userSalt;

    // 用户邮箱
    private String userEmail;

    // 用户部门编码
    private String userDepart;

}

