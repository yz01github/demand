package com.west.business.util;

import io.swagger.models.auth.In;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * description: [数字工具类]
 * title: NumberUtil
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/10
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil {

    /**
     * description: [判断指定数字是否在某一区间,区间前闭后开]
     * @param value     需要判断的数字
     * @param beginNum  区间开始值
     * @param endNum    区间开始值
     * @return 四舍五入后的结果
     */
    public static boolean between(Number value, Number beginNum, Number endNum) {
        Class<? extends Number> valueClass = value.getClass();
        Class<? extends Number> beginNumClass = beginNum.getClass();
        Class<? extends Number> endNumClass = endNum.getClass();
        boolean classEQ1 = Objects.equals(valueClass, beginNumClass);
        boolean classEQ2 = Objects.equals(valueClass, endNumClass);

        boolean result = false;
        if(classEQ1 && classEQ2){
            if(value instanceof Byte){
                result =  ((Byte)value >= (Byte)beginNum) && ((Byte)value < (Byte)endNum);
            }
            else if(value instanceof Short){
                result =  ((Short)value >= (Short)beginNum) && ((Short)value < (Short)endNum);
            }
            else if(value instanceof Integer){
                result =  ((Integer)value >= (Integer)beginNum) && ((Integer)value < (Integer)endNum);
            }
            else if(value instanceof Long){
                result =  ((Long)value >= (Long)beginNum) && ((Long)value < (Long)endNum);
            }
            else if(value instanceof Double){
                result =  ((Double)value >= (Double)beginNum) && ((Double)value < (Double)endNum);
            }
            else if(value instanceof Float){
                result =  ((Float)value >= (Float)beginNum) && ((Float)value < (Float)endNum);
            }
            else {
                throw new IllegalArgumentException("无法比较的数字类型");
            }
            return result;
        }else{
            throw new IllegalArgumentException("数字类型不完全相同,无法比较");
        }
    }

    @Test
    public void test(){
        between(1.2, 1, 2);
    }

    /**
     * description: [判断指定数字是否不在某一区间,区间前闭后开]
     * @param value     需要判断的数字
     * @param beginNum  区间开始值
     * @param endNum    区间开始值
     * @return 四舍五入后的结果
     */
    public static boolean notBetween(Number value, Number beginNum, Number endNum) {
        return !between(value, beginNum, endNum);
    }

    /**
     * description: [提供精确的小数位四舍五入处理]
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bdValue = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return bdValue.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * description: [四舍五入取整处理]
     * @param value 需要四舍五入的数字
     * @return 四舍五入后的结果
     */
    public static int roundUp(double value) {
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 0, BigDecimal.ROUND_UP).intValue();
    }
}
