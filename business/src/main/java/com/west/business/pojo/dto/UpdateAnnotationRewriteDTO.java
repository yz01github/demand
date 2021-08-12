package com.west.business.pojo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * description: []
 * title: UpdateAnnotationRewriteDTO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/10
 */
@Data
@ToString(callSuper = true)
public class UpdateAnnotationRewriteDTO extends AnnotationRewriteDTO {

    private String updateSysTime;

    private String updateUser;

}
