package com.west.business.pojo.vo.demand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * description: [查询VO]
 * title: SearchDemandVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/23
 */
@Data
public class SearchDemandVO extends DemandInfoVO {
    // 需求名称
    @ApiModelProperty(value = "需求名")
    private String demandName;

    // 涉及模块
    @ApiModelProperty(value = "涉及模块")
    private String relatedModules;


    // 需求提出时间
    @ApiModelProperty(value = "需求提出时间,格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate demandTime;

    // 要求完成时间
    @ApiModelProperty(value = "要求完成时间,格式:yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String releaseTime;

    // 开发负责人
    @ApiModelProperty(value = "开发负责人")
    private String demandOwner;

    // 分析完成时间
    @ApiModelProperty(value = "分析完成时间,格式:yyyy-MM-dd")
    private LocalDate analysisEndTime;

    // 开发完成时间
    @ApiModelProperty(value = "开发完成时间,格式:yyyy-MM-dd")
    private LocalDate devEndTime;

    // 开发进度
    @ApiModelProperty(value = "开发进度, 请填入0-100整数")
    private String devProgress;

    // 是否上线,0否1是
    @ApiModelProperty(value = "是否上线,填写:是 or 否")
    private String isOver;

    // 上线是否成功,0否1是
    @ApiModelProperty(value = "上线是否成功,填写:是 or 否")
    private String releaseSuccess;

    // 失败原因
    @ApiModelProperty(value = "失败原因")
    private String failedCause;

    // 跟踪时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "跟踪时间,格式:yyyy-MM-dd")
    private LocalDate trackTime;

    // 实际工作量
    @ApiModelProperty(value = "实际工作量,格式:请填入0-100整数")
    private String actualWork;

    // 省份
    @ApiModelProperty(value = "省份,格式:青海 or 陕西")
    private String provName;

    // 需求提出时间
    @ApiModelProperty(value = "查询日期(开始),格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate searchStartTime;

    // 需求提出时间
    @ApiModelProperty(value = "查询日期(结束),格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate searchEndTime;

    // 需求提出时间
    @ApiModelProperty(value = "录入日期(开始),格式:yyyy-MM-dd HH:mm:ss,例:2020-07-01 12:00:00,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime searchInTimeStrat;

    // 需求提出时间
    @ApiModelProperty(value = "录入日期(结束),格式:yyyy-MM-dd HH:mm:ss,例:2020-07-01 12:00:00,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime searchInTimeEnd;
}
