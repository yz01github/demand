package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName(value = "TR_DMP_USER_ROLE")
@EqualsAndHashCode(callSuper = false)
public class UserRoleEntity extends BaseEntity {

    // 唯一标识
    private String relationId;

    // 用户唯一标识
    private String userId;

    // 角色唯一标识
    private String roleId;

    // 备注
    private String remark;

}