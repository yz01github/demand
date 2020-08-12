package com.west.business.pojo.vo.demandHours;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SearchDemandHoursVO extends CreateDemandHoursVO{

    //id
    @NotNull
    @ApiModelProperty(value = "ID")
    private Long id;

    // 需求名称
    @NotBlank
    @ApiModelProperty(value = "需求名")
    private String demandName;

    // 需求提出时间
    @ApiModelProperty(value = "需求提出时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate demandTime;

    // 开发负责人
    @NotBlank
    @ApiModelProperty(value = "开发负责人")
    private String demandOwnerId;

    // 预估工时
    @NotBlank
    @ApiModelProperty(value = "预估工时")
    private String expectHours;

    // 核算工时
    @NotBlank
    @ApiModelProperty(value = "核算工时")
    private String actualHours;

    // 核算率
    @NotBlank
    @ApiModelProperty(value = "核算率")
    private String accountRate;

    // 人均工时
    @NotBlank
    @ApiModelProperty(value = "人均工时")
    private String percapitaWorkHour;

    // 省份
    @NotBlank
    @ApiModelProperty(value = "省份")
    private String provName;

    // 数据录入时间
    @ApiModelProperty(value = "数据录入时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate inputTime;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate createTIME;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate updateTIME;

    // 需求提出时间
    @ApiModelProperty(value = "查询日期(开始),格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate searchStartTime;

    // 需求提出时间
    @ApiModelProperty(value = "查询日期(结束),格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate searchEndTime;
}
