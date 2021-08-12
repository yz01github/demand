package com.west.business.service.perm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.west.business.pojo.dto.perm.PermDTO;
import com.west.business.pojo.dto.role.RoleDTO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.perm.CreatePermissionVO;
import com.west.domain.entity.PermissionEntity;

import java.util.List;

/**
 * description: [权限相关service]
 * title: PermService
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
public interface PermService extends IService<PermissionEntity> {

    int createPermission(CreatePermissionVO permVO);

    int deletePermission(String permId);

    int updatePermission(PermDTO permDTO);

    List<RoleDTO> permIsUsing(String permId);

    IPage<PermDTO> qryPermList(PageVO<PermissionEntity> pageDTO, PermDTO permDTO);

    List<PermissionEntity> qryPermList(List<String> permIds);

    List<PermissionEntity> qryAllPermList();
}
