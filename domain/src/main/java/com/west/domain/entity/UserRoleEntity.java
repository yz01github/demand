package com.west.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * description: []
 * title: UserRoleEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/2/22
 */

@Data
@Builder
@NoArgsConstructor
@TableName(value = "TD_DMP_USER_ROLE")
@EqualsAndHashCode(callSuper = false)
public class UserRoleEntity extends BaseEntity {
}
