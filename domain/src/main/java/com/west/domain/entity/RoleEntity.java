package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(value = "TD_DMP_ROLE")
@EqualsAndHashCode(callSuper = false)
public class RoleEntity extends BaseEntity {

    // 角色唯一标识
    private String roleId;

    // 角色名称
    private String roleName;

    // 父级角色id,顶级角色该编码为 “-1”
    private String parentRoleId;

    // 角色优先级(多权限冲突时,按照orderNo较小的一个为准,可理解为优先级,orderNo越小优先级越高)
    private Integer orderNo;

    // 使用标识(0-未使用,1-在使用)
    private String useTag;

    // 备注
    private String remark;
}