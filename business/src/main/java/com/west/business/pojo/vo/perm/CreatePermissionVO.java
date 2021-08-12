package com.west.business.pojo.vo.perm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * description: []
 * title: CreatePermissionVO
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
@Data
public class CreatePermissionVO {

    private String permId;

    // 权限编码
    private String permCode;

    // 权限类型【0-功能权限, 1-数据权限】
    @NotNull
    private String permType;

    @NotNull
    private String permName;

    // 功能路径, permType=0 时不为空
    private String permPath;

    // 启用标识【0-未启用,1-启用】
    private String useTag;

    private String remark;

    // TODO 后期加上新建权限时同时指定部分角色( 只能指定自己权限内的角色)
    private String roleIds;
}
