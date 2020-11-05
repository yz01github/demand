package com.west.business.service.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.west.business.pojo.dto.role.CreateRoleDTO;
import com.west.business.pojo.dto.role.QueryRoleDTO;
import com.west.business.pojo.dto.role.RoleDTO;
import com.west.business.pojo.dto.role.UpdateRoleDTO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.entity.RoleEntity;

public interface RoleService extends IService<RoleEntity> {

    int createRole(CreateRoleDTO roleDTO);

    int deleteRole(String roleId);

    int updateRole(UpdateRoleDTO roleDTO);

    IPage<QueryRoleDTO> qryRoles(QueryRoleDTO roleDTO, PageVO<RoleDTO> pageVO);

}
