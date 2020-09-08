package com.west.business.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.west.domain.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description: []
 * title: ConfigParamDTO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/20
 */
@Data
public class ConfigParamDTO {

    // 系统编码
    private String configSys;

    // 配置编码
    private String configCode;

    // 配置描述
    private String configDesc;

    // 配置值1
    private String configValue1;

    // 配置值2
    private String configValue2;

    // 配置值3
    private String configValue3;

    // 配置值4
    private String configValue4;

    // 配置值5
    private String configValue5;

    // 权限编码(预留)
    private String permCode;

    // 配置值5
    private String remark;

    public ConfigParamDTO(){
    }

    public ConfigParamDTO(String configSys, String configCode){
        this.configSys = configSys;
        this.configCode = configCode;
    }

    public ConfigParamDTO(String configSys, String configCode, String configValue1){
        this.configSys = configSys;
        this.configCode = configCode;
        this.configValue1 = configValue1;
    }

}
