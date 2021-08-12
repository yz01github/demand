package com.west.business.service.perm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.business.pojo.dto.perm.PermDTO;
import com.west.business.pojo.dto.role.RoleDTO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.perm.CreatePermissionVO;
import com.west.business.util.GeneratorUtil;
import com.west.business.util.redis.RedisUtil;
import com.west.domain.dao.PermissionDao;
import com.west.domain.dao.RoleDao;
import com.west.domain.dao.RolePermDao;
import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.RolePermEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: []
 * title: PermServiceImpl
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/2
 */
@Slf4j
@Service
public class PermServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermService {

    @Autowired
    private PermissionDao permDao;

    @Autowired
    private RolePermDao rolePermDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * description: [创建权限]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    @Override
    public int createPermission(CreatePermissionVO permVO) {
        PermissionEntity permEntity = new PermissionEntity();
        BeanUtils.copyProperties(permVO, permEntity);
        return permDao.insert(permEntity);
    }

    /**
     * description: [删除权限]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    @Override
    public int deletePermission(String permId) {
        // 是否有角色仍在使用该权限
        List<RolePermEntity> rpList = qryPerm(permId);
        if(!CollectionUtils.isEmpty(rpList)){
            List<String> roleIds = rpList.stream().map(RolePermEntity::getRoleId).collect(Collectors.toList());
            log.error("该权限仍有角色正在使用,无法删除; cause by:{}", roleIds);
            throw new RuntimeException("该权限仍有角色正在使用,无法删除");
        }
        return permDao.delete(new UpdateWrapper<PermissionEntity>().eq("PERM_ID", permId));
    }

    /**
     * description: [更新角色]
     * @param   permDTO 更新条件
     * @return  int     更新成功条数
     * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
     * created 2020/7/2
     */
    @Override
    public int updatePermission(PermDTO permDTO) {
        PermissionEntity permissionEntity = new PermissionEntity();
        BeanUtils.copyProperties(permDTO, permissionEntity);
        UpdateWrapper<PermissionEntity> wrapper = new UpdateWrapper<PermissionEntity>().eq("PERM_ID", permDTO.getPermId());
        return permDao.update(permissionEntity, wrapper);
    }

    /**
     * description: [使用该权限的角色]
     * @param   permId  权限id
     * @return  权限对应的角色id
     * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
     * created 2020/7/2
     */
    @Override
    public List<RoleDTO> permIsUsing(String permId) {
        List<RolePermEntity> rpList = qryPerm(permId);
        if(CollectionUtils.isEmpty(rpList)){
            return Collections.emptyList();
        }
        // 查询对应的角色信息
        List<String> roleIds = rpList.stream().map(rp -> rp.getRoleId()).collect(Collectors.toList());
        List<RoleEntity> reList = roleDao.selectList(new QueryWrapper<RoleEntity>().in("ROLE_ID", roleIds));
        if(CollectionUtils.isEmpty(reList)){
            return Collections.emptyList();
        }

        // 封装返回
        List<RoleDTO> roleDTOs = reList.stream().map(re -> {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(re, roleDTO);
            return roleDTO;
        }).collect(Collectors.toList());
        return roleDTOs;
    }

    /**
     * description: [查询所有权限，分页展示]
     * @param   pageVO  分页参数
     * @param   permDTO 查询参数
     * @return  IPage<PermDTO>  查询结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    @Override
    public IPage<PermDTO> qryPermList(PageVO<PermissionEntity> pageVO, PermDTO permDTO) {
        PermissionEntity entity = new PermissionEntity();
        BeanUtils.copyProperties(permDTO, entity);
        QueryWrapper<PermissionEntity> wrapper = new QueryWrapper<PermissionEntity>()
                .like("PERM_NAME", permDTO.getPermName());
        IPage<PermissionEntity> iPage = permDao.selectPage(pageVO.getPage(), wrapper);
        IPage<PermDTO> result = iPage.convert(pe -> {
            PermDTO returnDTO = new PermDTO();
            BeanUtils.copyProperties(pe, returnDTO);
            return returnDTO;
        });
        return result;
    }

    /**
     * description: [查询传入的权限,不分页,优先缓存中获取]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    @Override
    public List<PermissionEntity> qryPermList(List<String> permIds) {
        /**/
        return null;
    }

    /**
     * description: [查询所有权限,优先缓存获取]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/15
     */
    @Override
    public List<PermissionEntity> qryAllPermList() {
        String allPermCacheId = GeneratorUtil.generaAllPermCacheId();
        // 权限的缓存:所有权限的详情,组成Map<permId, permInfo>放在一个key中
        Map<Object, Object> cachedPerm = RedisUtil.hmgetMap(allPermCacheId);
        if(!CollectionUtils.isEmpty(cachedPerm)){
            List<Object> values = (new ArrayList<>(cachedPerm.values()));
            return values.stream().map(o -> (PermissionEntity)o).collect(Collectors.toList());
        }
        // 如果没查到, 则查表并存储
        List<PermissionEntity> allPerms = permDao.selectList(null);
        Map<String, Object> permMap = allPerms.stream().collect(Collectors.toMap(PermissionEntity::getPermId, o -> o));
        RedisUtil.hmsetMap(allPermCacheId, permMap);
        return allPerms;
    }

    // 查权限对应的角色id
    public List<RolePermEntity> qryPerm(String permId) {
        return rolePermDao.selectList(new QueryWrapper<RolePermEntity>().eq("PERM_ID", permId));
    }
}
