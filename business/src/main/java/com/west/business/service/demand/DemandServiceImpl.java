package com.west.business.service.demand;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.domain.dao.DemandInfoDao;
import com.west.domain.entity.DemandInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * description: [周报相关接口实现类]
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    private DemandInfoDao demandInfoDao;

    @Override
    public int createDemand(DemandInfoVO demandInfo) {
        DemandInfo info = new DemandInfo();
        BeanUtils.copyProperties(demandInfo, info);
        return demandInfoDao.insert(info);
    }

    @Override
    public IPage<DemandInfoVO> qryAll(SearchDemandVO searchVO, PageVO<DemandInfo> pageVO) {
        QueryWrapper<DemandInfo> wrapper = buildSearchWrapper(searchVO);
        IPage<DemandInfo> iPage = demandInfoDao.selectPage(pageVO.getPage(), wrapper);
        return iPage.convert(o -> {
            DemandInfoVO vo = new DemandInfoVO();
            BeanUtils.copyProperties(o, vo);
            return vo;
        });
    }

    @Override
    public List<DemandInfoVO> searchByName(String name) {
        QueryWrapper<DemandInfo> wrapper = new QueryWrapper<DemandInfo>()
                .eq("DEMAND_OWNER", name)
                .ge("CREATE_TIME", LocalDate.now());
        return qry2VO(wrapper);
    }

    private List<DemandInfoVO> qry2VO(QueryWrapper<DemandInfo> wrapper){
        List<DemandInfo> infos = demandInfoDao.selectList(wrapper);

        if(CollectionUtils.isEmpty(infos)){
            return Collections.emptyList();
        }
        return infos.stream().map(o -> {
            DemandInfoVO vo = new DemandInfoVO();
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DemandInfoVO> qryExcelData() {
        QueryWrapper<DemandInfo> wrapper = new QueryWrapper<DemandInfo>()
                .ge("CREATE_TIME", LocalDate.now());
        return qry2VO(wrapper);
    }

    /**
     * @param searchVO  查询条件入参
     * @return  QueryWrapper 查询wrapper
     */
    private QueryWrapper<DemandInfo> buildSearchWrapper(SearchDemandVO searchVO) {
        String demandName = searchVO.getDemandName();
        String releaseSuccess = searchVO.getReleaseSuccess();
        String provName = searchVO.getProvName();
        LocalDate startTime = searchVO.getSearchStartTime();
        LocalDate endTime = searchVO.getSearchEndTime();
        return new QueryWrapper<DemandInfo>()
                .like(StringUtils.isNotBlank(demandName), "DEMAND_NAME", demandName)
                .eq(StringUtils.isNotBlank(releaseSuccess), "RELEASE_SUCCESS", releaseSuccess)
                .eq(StringUtils.isNotBlank(provName), "PROV_NAME", provName)
                .ge(Objects.nonNull(startTime), "DEMAND_TIME", startTime)
                .le(Objects.nonNull(endTime), "DEMAND_TIME", endTime);
    }
}
