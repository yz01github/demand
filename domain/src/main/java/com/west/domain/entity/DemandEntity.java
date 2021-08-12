package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * description: []
 * title: DemandEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/20
 */
@TableName(value = "TD_DMP_DEMANDS")
@Data
@EqualsAndHashCode(callSuper = false)
public class DemandEntity extends BaseEntity {

    // 唯一标识,UUID
    private String demandId;

    // 需求编码
    private String demandCode;

    // 需求名
    private String demandName;

    // 需求负责人(用户id)
    private String demandOwnerId;

    // 需求开发人(大多数情况下和"需求负责人"一致,小部分由负责人分析把控,开发者开发)
    private String demandDevownerId;

    // 需求开发进度(0-100)
    private Integer devProgress;

    // 需求所需开发省份
    private String demandProv;

    // 需求涉及模块
    private String demandAboutModules;

    // 需求提出时间
    private LocalDateTime demandBeginTime;

    // 需求联调开始时间
    private LocalDateTime demandTestBeginTime;

    // 需求要求完成时间
    private LocalDateTime demandEndTime;

    // 需求当前状态(0-待派发,1-问题确认中/分析中,2-开发中,3-自测中,4-联调中,5-已上线)
    private String demandState;

    // 备注
    private String remark;

    // 附件与需求关联关系为: 1需求-多附件 ,所以在附件表中加入demandId

}
