package com.west.business.service.demands;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * description: [需求跟踪相关接口]
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class DemandsServiceImpl implements DemandsService {

    private static final String STATE_NEXT = "DEMAND_NEXT_STATE_CONF";

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
    public Object updateDemandState(UpdateDemandsStateVO updateVO) {
        String demandState = updateVO.getDemandState();
        ConfigParamDTO configParamDTO = new ConfigParamDTO(CommonConsts.SYS_CODE, STATE_NEXT, demandState);
        List<ConfigParamDTO> configParamDTOS = paramService.qryConfByValue1(configParamDTO);
        ConfigParamDTO paramDTO = configParamDTOS.get(0);
        String configValue1 = paramDTO.getConfigValue1();
        List<String> nextState = Arrays.asList(configValue1.split(","));
        if(!nextState.contains(demandState)){
            throw new RuntimeException("[]状态不能直接跳转到[]状态");
        }

        return null;
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
