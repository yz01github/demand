package com.west.business.controller.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * description: []
 * title: BuildModel
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/24
 */
@Data
public class BuildModel {

    @Excel(name = "区域")
    private String areaCode;

    @Excel(name = "区域")
    private String areaName;

    @Excel(name = "归属国家/地区")
    private String parentCode;

    @Excel(name = "归属国家/地区")
    private String parentName;

    @Excel(name = "国家和附属岛屿")
    private String nationName;

    @Excel(name = "Country")
    private String englishNationName;

    @Excel(name = "国家代码")
    private String nationCode;

}
