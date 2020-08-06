package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * description: []
 * title: DemandHours
 *
 * @author
 * created
 */

@TableName(value = "TD_DMP_DEMAND_HOURS")// resultMap="xml 中 resultMap 的 id"
@Data
@EqualsAndHashCode(callSuper = false)
public class DemandHours extends BaseEntity {


    // 需求名称
    private String demandName;

    // 需求提出时间
    private LocalDate demandTime;

    // 开发负责人
    private String demandOwnerId;

    // 预估工时
    private String expectHours;

    // 核算工时
    private String actualHours;

    // 核算率
    private String accountRate;

    // 人均工时
    private String percapitaWorkHour;

    // 省份
    private String provName;

    // 数据录入时间
    private LocalDate inputTime;



}
