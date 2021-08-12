package com.west.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.west.domain.entity.ConfigEntity;
import com.west.domain.entity.DemandEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * description: []
 * title: ConfigDao
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Mapper
public interface ConfigParamDao extends BaseMapper<ConfigEntity> {

}
