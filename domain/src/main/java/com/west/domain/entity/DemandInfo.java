package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * description: []
 * title: DemandInfo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@TableName(value = "TD_DMP_DEMAND")// resultMap="xml 中 resultMap 的 id"
@Data
@EqualsAndHashCode(callSuper = false)
public class DemandInfo extends BaseEntity {

    // 需求唯一标识
    private String DemandId;

    // 需求名称
    private String demandName;

    // 涉及模块
    private String relatedModules;

    // 需求提出时间
    private LocalDate demandTime;

    // 要求完成时间
    private String releaseTime;

    // 开发负责人
    private String demandOwner;

    // 分析完成时间
    private LocalDate analysisEndTime;

    // 开发完成时间
    private LocalDate devEndTime;

    // 开发进度
    private String devProgress;

    // 是否上线,0否1是
    private String isOver;

    // 上线是否成功,0否1是
    private String releaseSuccess;

    // 失败原因
    private String failedCause;

    // 跟踪时间
    private LocalDate trackTime;

    // 省份
    private String provName;

    // 实际工作量
    private String actualWork;

}
