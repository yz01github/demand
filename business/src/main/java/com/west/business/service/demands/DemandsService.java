package com.west.business.service.demands;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.UpdateDemandVO;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.pojo.vo.demands.DemandsVO;
import com.west.business.pojo.vo.demands.QueryAllDemandVO;
import com.west.business.pojo.vo.demands.QueryDemandVO;
import com.west.business.pojo.vo.demands.UpdateDemandsStateVO;
import com.west.business.pojo.vo.demands.UpdateDemandsVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.entity.DemandInfo;

import java.util.List;

public interface DemandsService {

    // 创建需求信息
    int createDemand(CreateDemandsVO demandVO);

    // 更新需求状态
    int updateDemandState(UpdateDemandsStateVO demandVO);

    // 查询负责人为指定用户的并且不是结束状态的需求信息
    IPage<DemandsVO> qryTodoDemandsByOwnerId(QueryDemandVO qryVO, PageVO<DemandInfo> pageVOe);

    // 查询所有需求信息,支持多种条件,此接口纳入权限管控
    IPage<DemandsVO> qryAllByCond(QueryAllDemandVO qryVO, PageVO<DemandInfo> pageVO);

    List<DemandsVO> searchByName(String name);

    List<DemandsVO> qryExcelData(DemandInfoVO queryVO);

    // 更新需求信息(不能更新需求状态)
    int updateDemand(UpdateDemandsVO demandInfo);

    int deleteDemand(String demandId);
}
