package com.west.business.util;

import java.util.UUID;

/**
 * description: []
 * title: GeneratorUtil
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
public class GeneratorUtil {

    public static final String getUUID(){
        return UUID.randomUUID().toString().replace("-", "a");
    }
}
