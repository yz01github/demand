package com.west.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.west.domain.entity.DemandInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * description: []
 * title: DemandInfoDao
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Mapper
public interface DemandInfoDao extends BaseMapper<DemandInfo> {
}
