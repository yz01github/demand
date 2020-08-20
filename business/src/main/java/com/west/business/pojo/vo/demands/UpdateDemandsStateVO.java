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

    // 需求当前状态(0-待派发,1-问题确认中/分析中,2-开发中,2_1-问题未完全确认,开发中,3-自测中,4-联调中,5-已上线)
    @NotBlank
    private String demandState;

    // 备注
    private String remark;

}
