package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName(value = "TD_DMP_PERMISSION")
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionEntity extends BaseEntity {

    // 唯一标识
    private String permId;

    // 权限编码
    private String permCode;

    // 权限名称
    private String permName;

    // 权限类型【0-功能权限, 1-数据权限】
    private String permType;

    // 功能路径, permType=0 时不为空
    private String permPath;

    // 启用标识【0-未启用,1-启用】
    private String useTag;

    // 备注
    private String remark;

}