package com.west.business.pojo.dto.role;

import com.west.business.pojo.dto.perm.PermDTO;
import com.west.business.pojo.vo.user.QueryUserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryRoleDTO extends RoleDTO {

    // 是否查询角色对应的User
    private boolean qryUser;

    // 是否查询角色对应的权限
    private boolean qryPerm;

    // 对应的User集合
    private List<QueryUserVO> userList;

    // 对应的Permission集合
    private List<PermDTO> permList;
}
