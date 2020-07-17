package com.west.business.service.demo;

import com.west.business.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * description: []
 * title:
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class DemoServiceImpl implements DemoService{

    @Override
    public Object req(String str) {
        return HttpUtil.sendPostRequest("http://localhost:8071/domain/", str);
    }

    @Override
    public String demoRequest(String reqString) {
        return null;
    }
}
