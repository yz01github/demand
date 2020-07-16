package com.west.business.service.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * description: []
 * title: IDemo
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/16
 */
@FeignClient(name = "demo", url = "http://localhost:8071/domain")
public interface DemoService {

    @PostMapping(value = "/toUp")
    String demoRequest(String reqString);
}
