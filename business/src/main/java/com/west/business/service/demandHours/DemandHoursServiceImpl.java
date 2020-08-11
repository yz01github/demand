package com.west.business.service.demandHours;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.vo.demandHours.CreateDemandHoursVO;
import com.west.business.pojo.vo.demandHours.SearchDemandHoursVO;
import com.west.business.pojo.vo.demandHours.UpdateDemandHoursVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.dao.DemandHoursDao;
import com.west.domain.entity.DemandHours;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DemandHoursServiceImpl implements DemandHoursService{

    @Autowired
    private DemandHoursDao demandHoursDao;

    @Override
    public int createDemandHours(CreateDemandHoursVO createDemandHoursVO) {
        DemandHours info = new DemandHours();
        BeanUtils.copyProperties(createDemandHoursVO, info);
        return demandHoursDao.insert(info);
    }
    /*
    * 查询所有的需求
    * */
    @Override
    public IPage<CreateDemandHoursVO> qryAll(SearchDemandHoursVO searchDemandHoursVO, PageVO<DemandHours> pageVOe){
        QueryWrapper<DemandHours> wrapper = buildSearchWrapper(searchDemandHoursVO);
        IPage<DemandHours> iPage = demandHoursDao.selectPage(pageVOe.getPage(), wrapper);
        return iPage.convert(o -> o2VO(o));
    }

    @Override
    public List<CreateDemandHoursVO> searchByName(String name) {
        QueryWrapper<DemandHours> wrapper = new QueryWrapper<DemandHours>()
                .eq("DEMAND_OWNER_ID", name)
                .ge("CREATE_TIME", LocalDate.now());
        return qry2VO(wrapper);
    }

    @Override
    public int updateDemandHours(UpdateDemandHoursVO updateDemandHoursInfo) {
        QueryWrapper<DemandHours> wrapper = new QueryWrapper<DemandHours>().eq("ID", updateDemandHoursInfo.getId());
        List<DemandHours> infos = demandHoursDao.selectList(null);
        DemandHours info = new DemandHours();
        BeanUtils.copyProperties(updateDemandHoursInfo, info);
        return demandHoursDao.update(info, wrapper);
    }

    private List<CreateDemandHoursVO> qry2VO(QueryWrapper<DemandHours> wrapper){
        List<DemandHours> infos = demandHoursDao.selectList(wrapper);

        if(CollectionUtils.isEmpty(infos)){
            return Collections.emptyList();
        }
        return infos.stream().map(o -> o2VO(o)).collect(Collectors.toList());
    }

    private CreateDemandHoursVO o2VO(DemandHours o){
        CreateDemandHoursVO vo = new CreateDemandHoursVO();
        BeanUtils.copyProperties(o, vo);
        return vo;
    }

    /**
     * @param searchVO  查询条件入参
     * @return  QueryWrapper 查询wrapper
     */
    private QueryWrapper<DemandHours> buildSearchWrapper(SearchDemandHoursVO searchVO) {
        String demandName = searchVO.getDemandName();
        String provName = searchVO.getProvName();
        LocalDate startTime = searchVO.getSearchStartTime();
        LocalDate endTime = searchVO.getSearchEndTime();
        return new QueryWrapper<DemandHours>()
                .like(StringUtils.isNotBlank(demandName), "DEMAND_NAME", demandName)
                .eq(StringUtils.isNotBlank(provName), "PROV_NAME", provName)
                .ge(Objects.nonNull(startTime), "INPUT_TIME", startTime)
                .le(Objects.nonNull(endTime), "INPUT_TIME", endTime);
    }
}
