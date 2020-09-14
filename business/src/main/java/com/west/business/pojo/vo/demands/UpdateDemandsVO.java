package com.west.business.pojo.vo.demands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * description: [修改VO]
 * title: DemandInfo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Getter
@Setter
@ToString
public class UpdateDemandsVO extends DemandsVO {

    // 需求唯一标识
    @NotBlank
    private String demandId;

    // 需求提出时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime demandBeginTime;

    // 需求联调开始时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime demandTestBeginTime;

    // 需求要求完成时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime demandEndTime;
}
