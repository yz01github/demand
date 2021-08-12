package com.west.business.pojo.vo.demands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * description: []
 * title: DemandEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/20
 */
@Data
public class UpdateDemandsStateVO {

    // 需求唯一标识
    @NotBlank
    private String demandId;

    // 需求当前状态 见 StateEnum
    private String demandState;

    // 需求下一状态 见 StateEnum
    private String nextState;

    // 需求开发进度(0-100)
    private Integer devProgress;

    // 备注
    private String remark;

}
