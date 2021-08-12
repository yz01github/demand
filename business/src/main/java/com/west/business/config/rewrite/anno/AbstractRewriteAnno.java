package com.west.business.config.rewrite.anno;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.west.business.controller.DemandController;
import com.west.business.pojo.dto.AnnotationRewriteDTO;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Arrays;

/**
 * description: []
 * title: AbstractRewriteAnno
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/10
 */
@Slf4j
public class AbstractRewriteAnno implements IRewrite<AnnotationRewriteDTO> {

    // 后面改下，用redis做缓存
    private static Map<String, AnnotationRewriteDTO> REWRITE_CACHES = new HashMap();

    @Override
    public void refresh() throws Exception {
        // 1. TODO 从数据库加载数据
        Map<String, AnnotationRewriteDTO> datas = this.loadData();

        // 2. TODO 缓存

        // 3. 修改注解值
        for(Map.Entry<String, AnnotationRewriteDTO> entry : datas.entrySet()){
            AnnotationRewriteDTO rewriteDTO = entry.getValue();
            ElementType annoType = rewriteDTO.getAnnoType();
            // 字段注解修改
            if(ElementType.FIELD.equals(annoType)){
                // 获取注解的代理对象，这其实是一个Proxy对象(java.lang.Proxy, 不是spring的cglib代理)
                Field field = Class.forName(rewriteDTO.getModelClassName()).getDeclaredField(rewriteDTO.getModelName());
                Annotation annotation = Arrays.asList(field.getAnnotations()).stream()
                        .filter(anno -> StringUtils.equals(anno.annotationType().getName(), rewriteDTO.getAnnoClassName()))
                        .findFirst()
                        .orElse(null);

                modifierAnnotition(annotation, rewriteDTO);
            }
            else if(ElementType.METHOD.equals(annoType)){
                // TODO
            }

        }

        Excel annotation = DemandInfoVO.class.getDeclaredField("demandName").getAnnotation(Excel.class);
        log.debug("annotation.orderNum:{}",annotation.orderNum());

        DemandController.class.getDeclaredMethods();
        log.debug("annotation.orderNum:{}",annotation.orderNum());

        // 4.留存历史
    }

    //
    /**
     * description: [处理代理注解对象的值]
     * @param   annotation  注解的代理对象
     * @param   rewriteDTO  配置相关信息
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/6/10
     */
    private void modifierAnnotition(Annotation annotation, AnnotationRewriteDTO rewriteDTO) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        /**
         * @see sun.reflect.annotation.AnnotationInvocationHandler
         */
        Field memberValueField = invocationHandler.getClass().getDeclaredField("memberValues");
        // memberValues 是private final 修饰的,要打开访问权限
        memberValueField.setAccessible(true);
        // 获取Excel注解的代理对象的memberValues instanceof LinkedHashMap
        Map memberValues = (Map)memberValueField.get(invocationHandler);
        memberValues.putAll(rewriteDTO.getNewAnnoAttr());
    }

    @Override
    public Map<String, AnnotationRewriteDTO> loadData() throws Exception {
        // 1. TODO 从数据库加载数据
        DemandInfoVO demandInfoVO = new DemandInfoVO();
        // "com.west.business.pojo.vo.demand.DemandInfoVO";
        AnnotationRewriteDTO annotationRewriteDTO = new AnnotationRewriteDTO();
        annotationRewriteDTO.setAnnoClassName("cn.afterturn.easypoi.excel.annotation.Excel");
        annotationRewriteDTO.setModelClassName("com.west.business.pojo.vo.demand.DemandInfoVO");
        annotationRewriteDTO.setAnnoType(ElementType.FIELD);
        annotationRewriteDTO.setId(UUID.randomUUID().toString());
        annotationRewriteDTO.setModelName("demandName");
        Map<String, Object> newAttrMap = new HashMap<>();
        newAttrMap.put("orderNum", "888");
        annotationRewriteDTO.setNewAnnoAttr(newAttrMap);

        AnnotationRewriteDTO annotationRewriteDTO2 = new AnnotationRewriteDTO();
        annotationRewriteDTO2.setAnnoClassName("org.springframework.web.bind.annotation.PostMapping");
        annotationRewriteDTO2.setModelClassName("com.west.business.controller.DemandController");
        annotationRewriteDTO2.setAnnoType(ElementType.METHOD);
        annotationRewriteDTO2.setId(UUID.randomUUID().toString());
        annotationRewriteDTO2.setModelName("testCreate");
        Map<String, Object> newAttrMap2 = new HashMap<>();
        newAttrMap2.put("value", "infos222");
        annotationRewriteDTO.setNewAnnoAttr(newAttrMap2);

        Map<String, AnnotationRewriteDTO> map = new HashMap<>();
        map.put(annotationRewriteDTO.getId(), annotationRewriteDTO);
        map.put(annotationRewriteDTO2.getId(), annotationRewriteDTO2);
        return map;
    }


}
