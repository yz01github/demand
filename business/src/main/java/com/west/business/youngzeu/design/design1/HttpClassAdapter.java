package com.west.business.youngzeu.design.design1;

import com.west.business.youngzeu.design.design1.HttpClassAdaptee;
import com.west.business.youngzeu.design.design1.Target;

import java.util.Map;

/**
 * description: [类适配器模式：适配器类]
 * title: HttpClassAdapter
 * 类适配器模式:适配器与适配者之间是继承（或实现）关系, Adapter extends Adaptee implments Target
 * 统一管理 Target 子类，适配器继承/实现统一接口方法
 * Target -> HttpClassAdapter -> HttpClassAdaptee;
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/22
 */
public class HttpClassAdapter extends HttpClassAdaptee
        implements Target<Map<String,Object>,String> {

    @Override
    public Map<String, Object> execute(String params) {
        // before do something
        Object result = super.doWork(params);
        // after do something
        return (Map<String, Object>)result;
    }
}
