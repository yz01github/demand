package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: []
 * title: ConfigEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/20
 */
@TableName("TS_DMP_CONFIG")
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigEntity extends BaseEntity {

    // 系统编码
    private String configSys;

    // 配置编码
    private String configCode;

    // 配置值1
    private String configValue1;

    // 配置值2
    private String configValue2;

    // 配置值3
    private String configValue3;

    // 配置值4
    private String configValue4;

    // 配置值5
    private String configValue5;

}
