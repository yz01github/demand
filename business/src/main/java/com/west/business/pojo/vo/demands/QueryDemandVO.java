package com.west.business.pojo.vo.demands;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * description: [查询VO]
 * title: SearchDemandVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/23
 */
@Getter
@Setter
@ToString
public class QueryDemandVO extends DemandsVO{

    // 需求负责人(用户id)
    @NotBlank
    @ApiModelProperty(value = "需求负责人ID")
    private String demandOwnerId;

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
