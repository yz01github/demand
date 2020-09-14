package com.west.business.controller;

/**
 * description: [动态的Excel统计相关接口]
 * title: DynamicExcelController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/14
 */
public class DynamicExcelController {

    // 功能设想:
    // 通过后台配置, 实现不同表头的excel数据录入, 免去手工整理步骤, 成员通过页面各自录入数据库, 负责人一键导出

    // 实现方案:
    // 1. 通过配置固定的参数表, 生成前台页面的动态表格
    // 2. 参数表中应具有: 动态Excel唯一标识, 描述, 表头列名
    // 3. 后台代码收到数据后, 匹配参数表中配置的表头, 生成相应的model
    // 4. 通过统一的固定样式, 导出相应数据的excel
}
