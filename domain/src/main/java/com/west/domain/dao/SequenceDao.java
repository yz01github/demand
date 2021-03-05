package com.west.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.west.domain.entity.SequenceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * description: []
 * title: SequenceDao
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/21
 */
@Mapper
public interface SequenceDao extends BaseMapper<SequenceEntity> {

    @Select("SELECT seq(#{seqId})")
    String callSeq(@Param("seqId") String seqId);
}
