package com.west.domain.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * description: [所有实体Bean的基类]
 * title: BaseEntity
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity extends Model<BaseEntity> {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "ID")
    @TableId(type= IdType.AUTO)
    @JsonIgnore
    protected Long id;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(value = "CREATE_TIME",  fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField(value = "UPDATE_TIME",  fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
//    @JsonIgnore
//    @TableField(value = "CREATE_USER",  fill = FieldFill.INSERT)
//    protected String createUser;
//
//    /**
//     * 修改人
//     */
//    @JsonIgnore
//    @TableField(value = "UPDATE_USER",  fill = FieldFill.UPDATE)
//    protected String updateUser;

    /**
     * 是否删除 : 0 未删除,1 已删除
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "IS_DELETE",  fill = FieldFill.INSERT)
    protected Character isDelete;
}
