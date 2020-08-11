package com.west.business.pojo.vo.demandHours;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class CreateDemandHoursVO {

    //id
    @ApiModelProperty(value = "ID")
    private Long id;

    // 需求名称
    @NotBlank
    @ApiModelProperty(value = "需求名")
    @Excel(name = "需求名",orderNum = "0", width = 35.0)
    private String demandName;

    // 需求提出时间
    @ApiModelProperty(value = "需求提出时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "需求提出时间", format = "yyyy-MM-dd", orderNum = "2", width = 15.0)
    private LocalDate demandTime;

    // 开发负责人
    @NotBlank
    @ApiModelProperty(value = "开发负责人")
    @Excel(name = "开发负责人",orderNum = "0", width = 15.0)
    private String demandOwnerId;

    // 预估工时
    @NotBlank
    @ApiModelProperty(value = "预估工时")
    @Excel(name = "预估工时",orderNum = "0", width = 15.0)
    private String expectHours;

    // 核算工时
    @NotBlank
    @ApiModelProperty(value = "核算工时")
    @Excel(name = "核算工时",orderNum = "0", width = 15.0)
    private String actualHours;

    // 核算率
    @NotBlank
    @ApiModelProperty(value = "核算率")
    @Excel(name = "开发进度", orderNum = "7", type = 1, suffix = "%")
    private String accountRate;

    // 人均工时
    @NotBlank
    @ApiModelProperty(value = "人均工时")
    @Excel(name = "人均工时",orderNum = "0", width = 15.0)
    private String percapitaWorkHour;

    // 省份
    @NotBlank
    @ApiModelProperty(value = "省份")
    @Excel(name = "省份",orderNum = "0", width = 15.0)
    private String provName;

    // 数据录入时间
    @ApiModelProperty(value = "数据录入时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "数据录入时间", format = "yyyy-MM-dd", orderNum = "2", width = 15.0)
    private LocalDate inputTime;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "创建时间", format = "yyyy-MM-dd", orderNum = "2", width = 15.0)
    private LocalDate createTIME;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "修改时间", format = "yyyy-MM-dd", orderNum = "2", width = 15.0)
    private LocalDate updateTIME;
}
