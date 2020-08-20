package com.west.business.service.configparam;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.west.business.pojo.dto.ConfigParamDTO;
import com.west.domain.dao.ConfigParamDao;
import com.west.domain.dao.DemandsDao;
import com.west.domain.entity.ConfigEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: []
 * title:
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class ConfigParamServiceImpl implements ConfigParamService {

    @Autowired
    private ConfigParamDao configParamDao;

    @Override
    public List<ConfigParamDTO> qryConfByValue1(ConfigParamDTO paramDTO) {
        // 默认查询配置为空时报错
        return qryConfByValue1(paramDTO, false);
    }

    @Override
    public List<ConfigParamDTO> qryConfByValue1(ConfigParamDTO paramDTO, boolean canEmpty) {
        Wrapper<ConfigEntity> wrapper = buildWrapper(paramDTO);
        List<ConfigEntity> list = configParamDao.selectList(wrapper);
        if(CollectionUtils.isEmpty(list)){
            if(canEmpty){
                return Collections.emptyList();
            }else{
                String configSys = paramDTO.getConfigSys();
                String configCode = paramDTO.getConfigCode();
                String configValue1 = paramDTO.getConfigValue1();
                throw new RuntimeException("配置["+configSys+"]["+configCode+"]["+configValue1+"]为空");
            }
        }
        return list.stream().map(o -> o2DTO(o)).collect(Collectors.toList());
    }

    // 转换为dto展示
    private ConfigParamDTO o2DTO(ConfigEntity o) {
        ConfigParamDTO paramDTO = new ConfigParamDTO();
        BeanUtils.copyProperties(o, paramDTO);
        return paramDTO;
    }

    // 构建查询条件
    private Wrapper<ConfigEntity> buildWrapper(ConfigParamDTO paramDTO){
        String configSys = paramDTO.getConfigSys();
        String configCode = paramDTO.getConfigCode();
        String configValue1 = paramDTO.getConfigValue1();
        String configValue2 = paramDTO.getConfigValue2();
        String configValue3 = paramDTO.getConfigValue3();
        String configValue4 = paramDTO.getConfigValue4();
        String configValue5 = paramDTO.getConfigValue5();
        QueryWrapper<ConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(configSys),"CONFIG_SYS", configSys)
                .eq(StringUtils.isNotBlank(configCode), "CONFIG_CODE", configCode)
                .eq(StringUtils.isNotBlank(configValue1), "CONFIG_VALUE1", configValue1)
                .eq(StringUtils.isNotBlank(configValue2), "CONFIG_VALUE2", configValue2)
                .eq(StringUtils.isNotBlank(configValue3), "CONFIG_VALUE3", configValue3)
                .eq(StringUtils.isNotBlank(configValue4), "CONFIG_VALUE4", configValue4)
                .eq(StringUtils.isNotBlank(configValue5), "CONFIG_VALUE5", configValue5);
        return wrapper;
    }
}
