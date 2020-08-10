package com.west.business.consts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * description: [运行环境枚举]
 * title: RuntimeEnum
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/9
 */
@Getter
public enum RuntimeSystemEnum {
    DEV("dev", "开发环境", null),
    TEST("test", "测试环境", null),
    PRE("pre", "灰度环境", null),
    PRD("prd", "正式环境", null);

    private String code;
    private String desc;
    private Map<String, Object> data;

    RuntimeSystemEnum(String code, String desc, Map<String, Object> data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }
}
