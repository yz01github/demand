package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * description: [字符串工具类]
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
        return Arrays.asList(sourceStr.split(splitStr));
    }

    /**
     * description: [将{paramStr}分割为若干条长度为{subLength}的子字符串]
     * @param   subLength   分割长度
     * @param   paramStr    源字符串
     * @return  List<String>    分割后的结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/8/5
     */
    public static List<String> splitStr2List(String paramStr, int subLength) {
        int size = paramStr.length() / subLength;
        if (paramStr.length() % subLength != 0) {
            size += 1;
        }
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(paramStr, index * subLength,(index + 1) * subLength);
            list.add(childStr);
        }
        return list;
    }

    /**
     * description: [分割字符串]
     * @param   str     要分割的字符串
     * @param   begin   开始位置
     * @param   end     结束位置
     * @return  String  分割结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/3/17
     */
    public static String substring(String str, int begin, int end) {
        if (begin > str.length()){
            return "";
        }
        return end > str.length() ? str.substring(begin) : str.substring(begin, end);
    }

    public static List<String> stringSubsection(String source, int byteLength) {
        if (source != null && source.length() != 0) {
            byte[] sByte = source.getBytes();
            char[] sChar = source.toCharArray();
            List<String> dataset = new ArrayList();
            if (sByte.length <= byteLength) {
                dataset.add(source);
            } else {
                int byleCount = 0;
                int first = 0;

                for(int i = 0; i < sChar.length; ++i) {
                    if (sChar[i] > 128) {
                        byleCount += 2;
                    } else {
                        ++byleCount;
                    }

                    if (byleCount == byteLength) {
                        if (first == 0) {
                            dataset.add(new String(sChar, first, i + 1));
                        } else {
                            dataset.add(new String(sChar, first + 1, i - first));
                        }

                        first = i;
                        byleCount = 0;
                    }

                    if (byleCount == byteLength + 1) {
                        if (first == 0) {
                            dataset.add(new String(sChar, first, i));
                        } else {
                            dataset.add(new String(sChar, first + 1, i - first - 1));
                        }

                        first = i - 1;
                        byleCount = 2;
                    }
                }

                if (byleCount != 0) {
                    dataset.add(new String(sChar, first + 1, sChar.length - first - 1));
                }
            }

            return dataset;
        } else {
            return new ArrayList();
        }
    }
}
