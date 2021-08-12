package com.west.business.util;

import com.west.business.service.seq.ISequenceService;
import com.west.business.util.date.DateUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * description: [序列生产相关工具类]
 * title: SeqUtils
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/8/11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SeqUtils {

    private static ISequenceService seqService;

    private static final String DEFAULT_SEQ_ID = "DEFAULT_SEQ_ID";

    @Autowired
    private ISequenceService sequenceService;

    @PostConstruct
    public void init(){
        SeqUtils.seqService = sequenceService;
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
