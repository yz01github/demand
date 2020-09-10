package com.west.business.pojo.vo.demands;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description: [修改VO]
 * title: DemandInfo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Data
public class UpdateDemandsVO extends DemandsVO {

    // 需求唯一标识
    @NotBlank
    private String demandId;

}
