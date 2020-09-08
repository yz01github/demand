package com.west.business.service.configparam;


import com.west.business.pojo.dto.ConfigParamDTO;

import java.util.List;

/**
 * description: []
 * title: ConfigParamService
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/16
 */
public interface ConfigParamService {

    List<ConfigParamDTO> qryConfByValue1(ConfigParamDTO paramDTO);

    List<ConfigParamDTO> qryConfByValue1(ConfigParamDTO paramDTO, boolean canEmpty);

    List<ConfigParamDTO> qryConfByCode(ConfigParamDTO paramDTO);
}
