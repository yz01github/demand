package com.west.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.annotation.ResultAdvice;
import com.west.business.pojo.dto.role.CreateRoleDTO;
import com.west.business.pojo.dto.role.QueryRoleDTO;
import com.west.business.pojo.dto.role.RoleDTO;
import com.west.business.pojo.dto.role.UpdateRoleDTO;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.service.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "角色相关接口")
@Slf4j
@RestController
@ResultAdvice
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value="新增角色",notes="新增角色")
    @PostMapping("/")
    public Integer createPerm(@RequestBody @Valid CreateRoleDTO roleDTO){
        // param checked
        int resultNum = roleService.createRole(roleDTO);
        return resultNum;
    }

    /**
     * description: [修改角色信息]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="修改角色",notes="修改角色")
    @PutMapping("/")
    public Integer updateDemand(@RequestBody UpdateRoleDTO roleDTO) {
        int num = roleService.updateRole(roleDTO);
        return num;
    }

    /**
     * description: [删除]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="删除角色",notes="删除角色")
    @DeleteMapping("/{roleId}")
    public boolean deleteDemand(@PathVariable("roleId") String roleId) {
        int num = roleService.deleteRole(roleId);
        return num > 0;
    }

    @ApiOperation("分页查询所有角色")
    @GetMapping("/page")
    public IPage<QueryRoleDTO> qryAllPage(QueryRoleDTO roleDTO, PageVO<RoleDTO> pageVO) {
        IPage<QueryRoleDTO> iPage = roleService.qryRoles(roleDTO, pageVO);
        return iPage;
    }
}
