package com.west.business.pojo.vo.demand;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * description: [展示VO]
 * title: DemandInfo
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Getter
@Setter
@ToString
public class DemandInfoVO {

    // 需求名称
    @NotBlank
    @ApiModelProperty(value = "需求名")
    @Excel(name = "需求名",orderNum = "0", width = 35.0)
    private String demandName;

    // 涉及模块
    @NotBlank
    @ApiModelProperty(value = "涉及模块")
    @Excel(name = "涉及模块", orderNum = "1", width = 15.0)
    private String relatedModules;


    // 需求提出时间
    @ApiModelProperty(value = "需求提出时间,格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "需求提出时间", format = "yyyy-MM-dd", orderNum = "2", width = 15.0)
    private LocalDate demandTime;

    // 要求完成时间
    @ApiModelProperty(value = "要求完成时间,格式:yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "要求完成时间", /*format = "yyyy-MM-dd",*/ orderNum = "3", width = 15.0)
    private String releaseTime;

    // 开发负责人
    @NotBlank
    @ApiModelProperty(value = "开发负责人")
    @Excel(name = "开发负责人", orderNum = "4", width = 12.0)
    private String demandOwner;

    // 分析完成时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分析完成时间,格式:yyyy-MM-dd")
    @Excel(name = "分析完成时间", format = "yyyy-MM-dd", orderNum = "5", width = 15.0)
    private LocalDate analysisEndTime;

    // 开发完成时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开发完成时间,格式:yyyy-MM-dd")
    @Excel(name = "开发完成时间", format = "yyyy-MM-dd", orderNum = "6", width = 15.0)
    private LocalDate devEndTime;

    // 开发进度
    @NotBlank
    @ApiModelProperty(value = "开发进度, 请填入0-100整数")
    @Excel(name = "开发进度", orderNum = "7", type = 1, suffix = "%")
    private String devProgress;

    // 是否上线,0否1是
    @Size(max = 1)
    @NotBlank
    @ApiModelProperty(value = "是否上线,填写:是 or 否")
    @Excel(name = "是否上线", replace = {"否_0", "是_1"}, orderNum = "8")
    private String isOver;

    // 上线是否成功,0否1是
    @ApiModelProperty(value = "上线是否成功,填写:是 or 否")
    @Excel(name = "上线是否成功", replace = {"否_0", "是_1"}, orderNum = "9", width = 15.0)
    private String releaseSuccess;

    // 失败原因  
    @ApiModelProperty(value = "失败原因")
    @Excel(name = "失败原因", orderNum = "10")
    private String failedCause;

    // 跟踪时间
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "跟踪时间,格式:yyyy-MM-dd")
    @Excel(name = "跟踪时间", format = "yyyy-MM-dd", orderNum = "11", width = 15.0)
    private LocalDate trackTime;

    // 实际工作量
    @Size(max = 3)
    @ApiModelProperty(value = "实际工作量,格式:请填入0-100整数")
    @Excel(name = "实际工作量", orderNum = "12", width = 15.0, type = 10)
    private String actualWork;

    // 省份
    @NotBlank
    @ApiModelProperty(value = "省份,格式:青海 or 陕西")
    @Excel(name = "省份",  replace = {"青海_0971", "陕西_0029"}, orderNum = "13")
    private String provName;

    // 需求唯一标识
    @ApiModelProperty(value = "需求唯一标识,不用填")
    private String demandId;

}
