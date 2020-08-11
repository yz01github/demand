package com.west.business.service.demandHours;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demandHours.CreateDemandHoursVO;
import com.west.business.pojo.vo.demandHours.SearchDemandHoursVO;
import com.west.business.pojo.vo.demandHours.UpdateDemandHoursVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.entity.DemandHours;

import java.util.List;

public interface DemandHoursService {

    int createDemandHours(CreateDemandHoursVO createDemandHoursVO);

    IPage<CreateDemandHoursVO> qryAll(SearchDemandHoursVO searchDemandHoursVO, PageVO<DemandHours> pageVOe);

    List<CreateDemandHoursVO> searchByName(String name);

    int updateDemandHours(UpdateDemandHoursVO updateDemandHoursInfo);

}
