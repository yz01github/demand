package com.west.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.west.domain.entity.SequenceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * description: [序列Dao]
 * title: SequenceDao
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/21
 */
@Mapper
public interface SequenceDao extends BaseMapper<SequenceEntity> {

    @Select("SELECT fo_seq(#{seqId})")
    String callSeq(@Param("seqId") String seqId);

    @Select("SELECT fo_cycleSeq(#{seqId})")
    String callCycleSeq(@Param("seqId") String seqId);
}
