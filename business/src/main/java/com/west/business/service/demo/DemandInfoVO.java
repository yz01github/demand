package com.west.business.service.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.west.domain.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * description: []
 * title: DemandInfo
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/7/17
 */
@Data
public class DemandInfoVO {

    // 需求名称
    @ApiModelProperty(value = "需求名")
    @Excel(name = "需求名",orderNum = "1")
    private String demandName;

    // 涉及模块 TODO
    @ApiModelProperty(value = "涉及模块")
    @Excel(name = "涉及模块", orderNum = "2")
    private String relatedModules;


    // 需求提出时间
    @ApiModelProperty(value = "需求提出时间,格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "需求提出时间", format = "yyyy-MM-dd", orderNum = "3")
    private LocalDate demandTime;

    // 要求完成时间
    @ApiModelProperty(value = "要求完成时间,格式:yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "要求完成时间", format = "yyyy-MM-dd", orderNum = "4")
    private LocalDate releaseTime;

    // 需求提出月份
    // private String demandMonth;

    // 开发负责人 TODO
    @ApiModelProperty(value = "开发负责人")
    @Excel(name = "开发负责人", orderNum = "5")
    private String demandOwner;

    // 分析完成时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分析完成时间,格式:yyyy-MM-dd")
    @Excel(name = "分析完成时间", format = "yyyy-MM-dd", orderNum = "6")
    private LocalDate analysisEndTime;

    // 开发完成时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开发完成时间,格式:yyyy-MM-dd")
    @Excel(name = "开发完成时间", format = "yyyy-MM-dd")
    private LocalDate devEndTime;

    // 开发进度 TODO
    @ApiModelProperty(value = "开发进度, 请填入0-100整数")
    @Excel(name = "开发进度")
    private String devProgress;

    // 是否上线,0否1是 TODO
    @ApiModelProperty(value = "是否上线,填写:是 or 否")
    @Excel(name = "是否上线", replace = {"否_0", "是_1"})
    private String isOver;

    // 上线是否成功,0否1是
    @ApiModelProperty(value = "上线是否成功,填写:是 or 否")
    @Excel(name = "上线是否成功", replace = {"否_0", "是_1"})
    private String releaseSuccess;

    // 失败原因 TODO
    @ApiModelProperty(value = "失败原因")
    @Excel(name = "失败原因")
    private String failedCause;

    // 跟踪时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "跟踪时间,格式:yyyy-MM-dd")
    @Excel(name = "跟踪时间", format = "yyyy-MM-dd")
    private LocalDate trackTime;

    // 省份
    @ApiModelProperty(value = "省份,格式:青海 or 陕西")
    @Excel(name = "省份",  replace = {"青海_0971", "陕西_0029"})
    private String provName;

    // 实际工作量
    @ApiModelProperty(value = "实际工作量,格式:请填入0-100整数")
    @Excel(name = "实际工作量")
    private String actualWork;

}
