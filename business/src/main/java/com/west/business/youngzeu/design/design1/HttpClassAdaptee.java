package com.west.business.youngzeu.design.design1;

import com.west.business.util.HttpUtil;
import com.west.business.youngzeu.design.Adaptee;
import lombok.extern.slf4j.Slf4j;

/**
 * description: [第三方接口处理业务 适配者（类适配模式）]
 * title: HttpClassAdaptee
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/8
 */
@Slf4j
public class HttpClassAdaptee implements Adaptee<Object,Object> {

    @Override
    public Object doWork(Object param) {
        Object responce = HttpUtil.sendPostRequest("localhost:8080/busi/demo/runtimeSystem", param);
        log.debug("HttpAdaptee. doWork 执行结束;返回:{}", responce);
        return responce;
    }
}
