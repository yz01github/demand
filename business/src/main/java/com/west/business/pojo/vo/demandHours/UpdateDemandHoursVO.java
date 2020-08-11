package com.west.business.pojo.vo.demandHours;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdateDemandHoursVO {

    //主键id
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


}
