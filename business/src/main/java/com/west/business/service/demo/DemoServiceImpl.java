package com.west.business.service.demo;

import com.west.business.util.HttpUtil;
import com.west.domain.dao.DemandInfoDao;
import com.west.domain.entity.DemandInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: []
 * title:
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    private DemandInfoDao demandInfoDao;

    @Override
    public Object req(String str) {
        return HttpUtil.sendPostRequest("http://localhost:8071/domain/", str);
    }

    @Override
    public String demoRequest(String reqString) {
        return null;
    }

    @Override
    public int createDemand(DemandInfoVO demandInfo) {
        DemandInfo info = new DemandInfo();
        BeanUtils.copyProperties(demandInfo, info);
        return demandInfoDao.insert(info);
    }

    @Override
    public List<DemandInfo> exportExcel() {
        List<DemandInfo> infos = demandInfoDao.selectList(null);
        return infos;
    }
}
