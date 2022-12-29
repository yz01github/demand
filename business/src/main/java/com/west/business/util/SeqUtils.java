package com.west.business.util;

import com.west.business.service.seq.ISequenceService;
import com.west.business.service.seq.SequenceServiceImpl;
import com.west.business.util.date.DateUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description: [序列生产相关工具类]
 * title: SeqUtils
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/8/11
 */
//@Lazy(false)
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeqUtils {

    private static ISequenceService seqService;

    @Autowired
    private ISequenceService seqServiceInit;

    private static final String DEFAULT_SEQ_ID = "DEFAULT_SEQ_ID";
    public static final String USER_SEQ_ID = "USER_SEQ_ID";

    /**
     * 让工具类使用注入对象的方法之一:
     * 顺序： Constructor >> @Autowired >> @PostConstruct >> 静态方法
     * 所有Autowired完成后自动执行PostConstruct, 未生效则注意:不应懒加载,Component在主类中扫描的范围
     */
    @PostConstruct
    public void init() {
        SeqUtils.seqService = seqServiceInit;
    }

    /**
     * description: [获取指定长度的流水值,左侧0补齐]
     * @param seqLength 流水长度
     * @return String   流水值
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/8/11
     */
    public static String genSeq(int seqLength){
        Long nextval = seqService.nextval(DEFAULT_SEQ_ID);
        return StringUtils.leftPad(String.valueOf(nextval), seqLength, '0');
    }

    public static String genSeq(int seqLength, String seqId){
        Long nextval = seqService.nextval(seqId);
        return StringUtils.leftPad(String.valueOf(nextval), seqLength, '0');
    }

    /**
     * 获取8位流水号,左侧0补齐
     * @see #genSeq(int)
     * @return String类型的8位流水号
     */
    public static String genSeq(){
        return genSeq(8);
    }

    /**
     * description: [获取20位流水: yyyyMMddHHmmss+6位随机数]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/8/11
     */
    public static String genSeqLength20(){
        return genSeqPreYYYYMMddHHmmss(6);
    }

    public static String genSeqPreYYYYMMddHHmmss(int seqLength){
        Long nextval = seqService.nextval(DEFAULT_SEQ_ID);
        String nextValueStr = StringUtils.leftPad(String.valueOf(nextval), seqLength, '0');
        return DateUtils.getSysTimeYYYYMMddHHmmss() + nextValueStr;
    }

}
