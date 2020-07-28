package com.west.business.service.demo;

import com.west.business.util.HttpUtil;
import com.west.domain.dao.DemandInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: []
 * title:
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
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

}
