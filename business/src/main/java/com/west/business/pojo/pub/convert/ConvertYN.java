package com.west.business.pojo.pub.convert;

import com.west.business.consts.CommonConsts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: [页面与后台静态参数转换,0-否,1-是]
 * title: ConvertYN
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertYN implements ViewConvert {

    private static ConvertYN convertYN = new ConvertYN();

    // 页面数据->数据库数据,
    /**
     * description: [do something]
     * @param   str         要转换的数据
     * @param   is2View     数据库->页面 为true; 页面-> 数据库 为false;
     * @return  转换后的数据
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/7/30
     */
    public static String convert(String str, boolean is2View){
        if(is2View){
            return convertYN.toView(str);
        }
        return convertYN.parse(str);
    }

    @Override
    public String parse(String str) {
        if(CommonConsts.VIEW_Y.equals(str)){
            return CommonConsts.CODE_Y;
        }
        if(CommonConsts.VIEW_N.equals(str)){
            return CommonConsts.CODE_N;
        }
        return str;
    }

    @Override
    public String toView(String str) {
        if(CommonConsts.CODE_Y.equals(str)){
            return CommonConsts.VIEW_Y;
        }
        if(CommonConsts.CODE_N.equals(str)){
            return CommonConsts.VIEW_N;
        }
        return str;
    }


}
