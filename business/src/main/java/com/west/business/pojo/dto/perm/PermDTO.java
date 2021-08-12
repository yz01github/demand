package com.west.business.pojo.dto.perm;

import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermDTO extends BaseEntity {

    private String permId;

    private String permType;

    private String permName;

    private String remark;
}
