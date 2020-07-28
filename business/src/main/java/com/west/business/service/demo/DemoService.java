package com.west.business.service.demo;


/**
 * description: []
 * title: IDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/16
 */
//@FeignClient(name = "demo", url = "http://localhost:8071/domain")
public interface DemoService {

    Object req(String str);

    //@PostMapping(value = "/toUp")
    String demoRequest(String reqString);

}
