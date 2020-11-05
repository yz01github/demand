package com.west.business.pojo.dto.context;

import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.RolePermEntity;
import com.west.domain.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * description: [ThreadLocal上下文对象]
 * title: URP - User/Role/Permission
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/15
 */
@Data
public class URP {

    private User user;

    private Map<String, RoleEntity> hasRoles;

    private Map<String, PermissionEntity> allHasPerms;

    private List<RolePermEntity> relationRP;

    private Map<String, Object> otherData;
}
