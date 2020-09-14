package com.west.business.service.demands;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.pojo.vo.demands.DemandsVO;
import com.west.business.pojo.vo.demands.QueryAllDemandVO;
import com.west.business.pojo.vo.demands.QueryDemandVO;
import com.west.business.pojo.vo.demands.UpdateDemandsStateVO;
import com.west.business.pojo.vo.demands.UpdateDemandsVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.util.GeneratorUtil;
import com.west.domain.dao.DemandsDao;
import com.west.domain.entity.DemandEntity;
import com.west.domain.entity.DemandInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * description: [需求跟踪相关接口]
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Slf4j
@Service
public class DemandsServiceImpl implements DemandsService {

    @Autowired
    private DemandsDao demandsDao;

    @Override
    public int createDemand(CreateDemandsVO demandVO) {
        DemandEntity demandEntity = new DemandEntity();
        BeanUtils.copyProperties(demandVO, demandEntity);
        demandEntity.setDemandId(GeneratorUtil.getUUID());
        return demandsDao.insert(demandEntity);
    }

    @Override
    public int updateDemandState(UpdateDemandsStateVO updateVO) {
        //权限校验
        permCheck(updateVO);
        //获取下一节点,并校验合法性
        String nowState = updateVO.getDemandState();
        String nextState = updateVO.getNextState();
        List<String> nextStates = StateEnum.getNextCodes(nowState);
        if(!nextStates.contains(nextState)){
            throw new RuntimeException("["+StateEnum.getStateName(nowState)+"]状态不能直接跳转到["
                    +StateEnum.getStateName(nextState)+"]状态");
        }

        // 更新需求状态
        DemandEntity entity = new DemandEntity();
        entity.setDemandState(nextState);
        entity.setRemark(updateVO.getRemark());
        UpdateWrapper<DemandEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("DEMAND_ID", updateVO.getDemandId());
        return demandsDao.update(entity, wrapper);
    }

    /**
     * description: [校验用户是否具有节点操作权限]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    private void permCheck(UpdateDemandsStateVO updateVO) {
        String nextState = updateVO.getNextState();
        Map<String, String> permNode = StateEnum.getPermNode();
        if(!permNode.containsKey(nextState)){// 下一节点不需要权限的,不做校验
            return;
        }

        // get permCodes by userId
        Map<String, Object> permCodes = new HashMap<>();
        List<StateEnum> permNodes = Arrays.asList(StateEnum.BUSI_5_0, StateEnum.BUSI_5_1);
        if(!permNodes.contains(nextState)){// 不具备权限则报错 TODO 这部分后面应使用拦截器拦截请求
            log.error("用户不具备更新改状态的权限!用户:{};状态编码{}","TODO_USER_ACOUNT",nextState);
            throw new RuntimeException("用户不具备更新改状态的权限!");
        }
    }

    /**
     * description: [查询负责人为指定用户的并且不是结束状态的需求信息]
     * @param   qryVO   查询入参
     * @param   pageVO   分页入参
     * @return  IPage<DemandInfoVO> 分页查询结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @Override
    public IPage<DemandsVO> qryTodoDemandsByOwnerId(QueryDemandVO qryVO, PageVO<DemandInfo> pageVO) {
        String demandName = qryVO.getDemandName();
        String ownerId = qryVO.getDemandOwnerId();
        QueryWrapper<DemandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("DEMAND_OWNER_ID", ownerId);
        wrapper.notIn("DEMAND_STATE", StateEnum.BUSI_E.getStateCode());
        wrapper.like(StringUtils.isNotBlank(demandName),"DEMAND_NAME", demandName);
        IPage<DemandEntity> iPage = demandsDao.selectPage(pageVO.getPage(), wrapper);
        return iPage.convert(obj2vo());
    }

    /**
     * description: [查询所有需求信息,支持多种条件,此接口纳入权限管控]
     * @param   qryVO   查询条件
     * @param   pageVO  分页条件
     * @return  IPage<DemandsVO>    查询结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @Override
    public IPage<DemandsVO> qryAllByCond(QueryAllDemandVO qryVO, PageVO<DemandInfo> pageVO) {
        String demandOwnerId = qryVO.getDemandOwnerId();
        String demandCode = qryVO.getDemandCode();
        String demandState = qryVO.getDemandState();
        String demandName = qryVO.getDemandName();
        String demandProv = qryVO.getDemandProv();
        QueryWrapper<DemandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(demandOwnerId),"DEMAND_OWNER_ID", demandOwnerId);
        wrapper.eq(StringUtils.isNotBlank(demandCode),"DEMAND_CODE", demandCode);
        wrapper.eq(StringUtils.isNotBlank(demandState),"DEMAND_STATE", demandState);
        wrapper.eq(StringUtils.isNotBlank(demandProv),"DEMAND_PROV", demandProv);
        wrapper.like(StringUtils.isNotBlank(demandName),"DEMAND_NAME", demandName);
        IPage<DemandEntity> iPage = demandsDao.selectPage(pageVO.getPage(), wrapper);
        return iPage.convert(obj2vo());
    }

    @Override
    public List<DemandsVO> searchByName(String name) {
        return null;
    }

    @Override
    public List<DemandsVO> qryExcelData(DemandInfoVO queryVO) {
        return null;
    }

    /**
     * description: [更新需求信息, 禁止更新需求状态]
     * @param   demandInfo  更新入参
     * @return  int 更新成功条数
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @Override
    public int updateDemand(UpdateDemandsVO demandInfo) {
        demandInfo.setDemandState(null);// 此接口禁止更新状态
        DemandEntity entity = new DemandEntity();
        BeanUtils.copyProperties(demandInfo, entity);
        String demandId = demandInfo.getDemandId();
        UpdateWrapper<DemandEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("DEMAND_ID", demandId);
        return demandsDao.update(entity, wrapper);
    }

    /**
     * description: [do something]
     * @param   demandId    删除的需求Id
     * @return  int         操作影响的条数
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @Override
    public int deleteDemand(String demandId) {
        QueryWrapper<DemandEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("DEMAND_ID", demandId);
        return demandsDao.delete(wrapper);
    }

    /**
     * description: [转换vo]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    private Function<DemandEntity, DemandsVO> obj2vo(){
        return o -> {
            DemandsVO vo = new DemandsVO();
            BeanUtils.copyProperties(o, vo);
            return vo;
        };
    }

}
