package com.west.business.pojo.dto;

import lombok.Data;
import lombok.ToString;

import java.lang.annotation.ElementType;
import java.util.Map;

/**
 * description: []
 * title: AnnotationRewrite
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/10
 */
@Data
@ToString
public class AnnotationRewriteDTO {
    // 注解类名,注解类型,注解声明的实体名(字段名/方法名),注解属性新值(K,V)

    private String id;

    /**
     * 注解类全限定名
     */
    private String annoClassName;

    /**
     * 注解类型:字段/类/方法等等
     */
    private ElementType annoType;

    /**
     * 注解声明的实体类的全限定名
     */
    private String modelClassName;

    /**
     * 注解声明的实体名(字段名/方法名)
     */
    private String modelName;

    /**
     * 注解属性新值(K,V)
     */
    private Map<String, Object> newAnnoAttr;

}
