package com.west.domain.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * description: []
 * title: DemandInfo
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/17
 */
@Data
public class DemandInfo {

    // 需求名称
    private String demandName;

    // 需求提出时间
    private LocalDate demandTime;

    // 需求提出月份
    private String demandMonth;

    // 需求归属人
    private String demandOwner;

    // 需求派单人
    private String appointSatff;

    // 需求跟踪人
    private String superVisor;

    // 分析开始时间
    private LocalDate analysisStartTime;

    // 分析完成时间
    private LocalDate analysisEndTime;

    // 开发开始时间
    private LocalDate devStartTime;

    // 开发结束时间
    private LocalDate devEndTime;

    // 测试开始时间
    private LocalDate testStartTime;

    // 测试结束时间
    private LocalDate testEndTime;

    // 上线时间
    private LocalDate releaseTime;

    // 上线是否成功
    private String releaseSuccess;

    // 省份
    private String provName;

}
