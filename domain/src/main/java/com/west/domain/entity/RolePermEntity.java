package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName(value = "TR_DMP_ROLE_PERM")
@EqualsAndHashCode(callSuper = false)
public class RolePermEntity extends BaseEntity {

    // 唯一标识
    private String relationId;

    // 权限id
    private String permId;

    // 角色id
    private String roleId;

    // 备注
    private String remark;
}