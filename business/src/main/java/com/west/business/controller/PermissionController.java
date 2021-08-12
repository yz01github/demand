package com.west.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.dto.perm.PermDTO;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.perm.CreatePermissionVO;
import com.west.business.service.perm.PermService;
import com.west.domain.entity.PermissionEntity;
import io.swagger.annotations.ApiOperation;
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

/**
 * description: [权限相关接口]
 * title: PermissionController
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/3
 */
@RestController
@RequestMapping("/perm")
public class PermissionController {

    @Autowired
    private PermService permService;

    @ApiOperation(value="新增权限",notes="新增权限")
    @PostMapping("/")
    public ResResult<Integer> createPerm(@RequestBody @Valid CreatePermissionVO permVO){
        // param checked
        int resultNum = permService.createPermission(permVO);
        return ResResult.successAddData(resultNum);
    }

    /**
     * description: [修改需求信息]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="修改权限",notes="修改权限")
    @PutMapping("/")
    public ResResult<Integer> updateDemand(@RequestBody PermDTO permDTO) {
        int num = permService.updatePermission(permDTO);
        return ResResult.successAddData(num);
    }

    /**
     * description: [删除]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="删除权限",notes="权限")
    @DeleteMapping("/{permId}")
    public ResResult deleteDemand(@PathVariable("permId") String permId) {
        int num = permService.deletePermission(permId);
        return ResResult.result(num > 0);
    }

    @ApiOperation("分页查询所有权限")
    @GetMapping("/page")
    public ResResult<IPage<PermDTO>> qryAllPage(PageVO<PermissionEntity> pageVO, PermDTO permDTO){
        IPage<PermDTO> iPage = permService.qryPermList(pageVO, permDTO);
        return ResResult.successAddData(iPage);
    }
}
