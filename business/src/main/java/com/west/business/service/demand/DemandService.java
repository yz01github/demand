package com.west.business.service.demand;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.demand.UpdateDemandVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.entity.DemandInfo;

import java.util.List;

public interface DemandService {

    int createDemand(DemandInfoVO demandInfo);

    IPage<DemandInfoVO> qryAll(SearchDemandVO searchVO, PageVO<DemandInfo> pageVOe);

    List<DemandInfoVO> searchByName(String name);

    List<DemandInfoVO> qryExcelData(DemandInfoVO queryVO);

    int updateDemand(UpdateDemandVO demandInfo);

    boolean deleteDemand(String demandId);
}
