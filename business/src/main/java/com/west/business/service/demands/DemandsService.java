package com.west.business.service.demands;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.demand.UpdateDemandVO;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.pojo.vo.demands.UpdateDemandsStateVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.entity.DemandInfo;

import java.util.List;

public interface DemandsService {

    int createDemand(CreateDemandsVO demandVO);

    int updateDemandState(UpdateDemandsStateVO demandVO);

    IPage<DemandInfoVO> qryAll(SearchDemandVO searchVO, PageVO<DemandInfo> pageVOe);

    List<DemandInfoVO> searchByName(String name);

    List<DemandInfoVO> qryExcelData(DemandInfoVO queryVO);

    int updateDemand(UpdateDemandVO demandInfo);
}
