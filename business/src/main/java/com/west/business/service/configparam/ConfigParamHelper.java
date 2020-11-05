package com.west.business.service.configparam;

import com.west.business.consts.CommonConsts;
import com.west.business.pojo.dto.ConfigParamDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * description: []
 * title: ConfigParamHelper
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/8
 */
@Slf4j
@Lazy(false)
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigParamHelper {

    private static ConfigParamService confParamService;

    @Autowired
    private ConfigParamService confParamServiceInit;

    @PostConstruct
    public void init() {
        confParamService = this.confParamServiceInit;
    }

    /**
     * description: [获取指定节点的下一节点对应的配置]
     * @param   nowNode         当前节点
     * @return  ConfigParamDTO  对应的配置
     * @Exception   RuntimeException    当一节点配置了多条时，抛错
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static ConfigParamDTO getNextNodeConf(String nowNode){
        ConfigParamDTO paramDTO = new ConfigParamDTO();
        paramDTO.setConfigSys(CommonConsts.SYS_CODE);
        paramDTO.setConfigCode(CommonConsts.NODE_CONF);
        paramDTO.setConfigValue1(nowNode);
        List<ConfigParamDTO> configParams = confParamService.qryConfByValue1(paramDTO, true);
        if(configParams.size() > 1){
            throw new RuntimeException("配置有误:["+CommonConsts.NODE_CONF+"]");
        }
        return CollectionUtils.isEmpty(configParams) ? new ConfigParamDTO() : configParams.get(0);
    }

    /**
     * description: [获取配置 by code and syscode]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static List<ConfigParamDTO> getConfByCode(String sysCode, String nodeConf){
        ConfigParamDTO paramDTO = new ConfigParamDTO();
        paramDTO.setConfigSys(sysCode);
        paramDTO.setConfigCode(nodeConf);
        List<ConfigParamDTO> configParams = confParamService.qryConfByCode(paramDTO);
        return configParams;
    }
}
