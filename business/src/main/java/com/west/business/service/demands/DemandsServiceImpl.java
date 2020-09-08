package com.west.business.service.demands;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.consts.CommonConsts;
import com.west.business.pojo.dto.ConfigParamDTO;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.demand.UpdateDemandVO;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.pojo.vo.demands.UpdateDemandsStateVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.service.configparam.ConfigParamService;
import com.west.business.util.GeneratorUtil;
import com.west.domain.dao.DemandsDao;
import com.west.domain.entity.DemandEntity;
import com.west.domain.entity.DemandInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private ConfigParamService paramService;

    @Override
    public int createDemand(CreateDemandsVO demandVO) {
        DemandEntity demandEntity = new DemandEntity();
        BeanUtils.copyProperties(demandVO, demandEntity);
        demandEntity.setDemandId(GeneratorUtil.getUUID());
        return demandsDao.insert(demandEntity);
    }

    @Override
    public int updateDemandState(UpdateDemandsStateVO updateVO) {
        permCheck(updateVO);//权限校验
        String nowState = updateVO.getDemandState();
        String nextState = updateVO.getNextState();
        List<String> nextStates = StateEnum.getNextCodes(nowState);//获取下一节点
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
     * description: [校验勇士是否具有节点操作权限]
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

    @Override
    public IPage<DemandInfoVO> qryAll(SearchDemandVO searchVO, PageVO<DemandInfo> pageVOe) {
        return null;
    }

    @Override
    public List<DemandInfoVO> searchByName(String name) {
        return null;
    }

    @Override
    public List<DemandInfoVO> qryExcelData(DemandInfoVO queryVO) {
        return null;
    }

    @Override
    public int updateDemand(UpdateDemandVO demandInfo) {
        return 0;
    }


}
