package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * description: []
 * title: CommonStrUtil
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonStrUtil {

    /**
     * description: [根据 "," 分割字符串]
     * @see #splitStr2List(String, String)
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static List<String> splitStr2List(String str){
        return splitStr2List(str, ",");
    }

    /**
     * description: [字符串依照指定字符分割]
     * @param   sourceStr       需要分割的字符串
     * @param   splitStr        分割标识
     * @return  List<String>    分割后的结果,sourceStr未空时,返回空集合,不报错
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static List<String> splitStr2List(String sourceStr, String splitStr){
        if(StringUtils.isBlank(sourceStr)){
            return Collections.emptyList();
        }
        return Arrays.asList(sourceStr.split(","));
    }
}
