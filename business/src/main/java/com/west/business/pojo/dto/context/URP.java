package com.west.business.pojo.dto.context;

import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.RolePermEntity;
import com.west.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description: [ThreadLocal上下文对象]
 * title: URP - User/Role/Permission
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/15
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URP {

    @Setter
    private User user;
    @Setter
    private Map<String, RoleEntity> roles;
    @Setter
    private Map<String, PermissionEntity> perms;
    @Setter
    private List<RolePermEntity> relationRP;
    @Setter
    private Map<String, Object> otherData;

    public User getUser() {
        return Objects.isNull(user) ? User.builder().build() : user;
    }

    public Map<String, RoleEntity> getRoles() {
        return Objects.isNull(roles) ? new HashMap<>() : roles;
    }

    public Map<String, PermissionEntity> getPerms() {
        return Objects.isNull(perms) ? new HashMap<>() : perms;
    }

    public List<RolePermEntity> getRelationRP() {
        return Objects.isNull(relationRP) ? new ArrayList<>() : relationRP;
    }

    public Map<String, Object> getOtherData() {
        return Objects.isNull(otherData) ? new HashMap<>() : otherData;
    }

    public URP (URP urp){
        this.user = urp.getUser();
        this.roles = urp.getRoles();
        this.perms = urp.getPerms();
        this.relationRP = urp.getRelationRP();
        this.otherData = urp.otherData;
    }

}
