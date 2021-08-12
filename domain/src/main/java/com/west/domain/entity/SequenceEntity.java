package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: []
 * title: SequenceEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/21
 */
@Data
@TableName(value = "TO_SEQUENCE")
@EqualsAndHashCode(callSuper = false)
public class SequenceEntity extends Model<BaseEntity> {

    // 序列ID
    private String seqId;

    // 当前序列值
    private Long currentValue;

    // 步长
    private Integer increment;

    // 序列最大值
    private Integer limitMaxValue;

    // 缓存长度
    private Integer cacheSize;

}
