package com.west.business.service.demands;

import com.west.business.consts.CommonConsts;
import com.west.business.pojo.dto.ConfigParamDTO;
import com.west.business.service.configparam.ConfigParamHelper;
import com.west.business.util.CommonStrUtil;
import com.west.domain.dao.ConfigParamDao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * description: []
 * title: StateEnum
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/7
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum StateEnum {
    BUSI_0("0","初始化","1,E",""),
    BUSI_1("1","待分派","2_0,2_1,2_2,E",""),
    BUSI_2_0("2_0","分析中","2_1,2_2,E",""),
    BUSI_2_2("2_2","开发中(分析完成)","2_2,3_0,3_1,E",""),
    BUSI_2_1("2_1","开发中(分析尚有遗留问题)","2_2,3_0,3_1,E",""),
    BUSI_3_0("3_0","开发完成(联调中)","3_1,E",""),
    BUSI_3_1("3_1","开发完成(联调完成)","4_0,4_1,E",""),
    BUSI_4_0("4_0","上线(失败)","4_1,5_0,E",""),
    BUSI_4_1("4_1","上线(成功)","5_0,5_1,E",""),
    BUSI_5_0("5_0","上级审阅(备份)","5_1,E",""),
    BUSI_5_1("5_1","上级审阅(结束)","E",""),
    BUSI_E("E","流程结束","","")
    ;

    private String stateCode;
    private String stateDesc;
    private String nextState;
    // 权限编码预留
    private String permCode;

    /**
     * description: [获取下一节点List,所有本类的枚举只应当用于参考或stateCode常量,不应参与逻辑,逻辑处理以配置为准!!!]
     * @param   nowState        当前节点
     * @return  List<String>    下一节点可执行的节点
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/7
     */
    public static List<String> getNextCodes(String nowState){
        // 前置校验
        if (StringUtils.isBlank(nowState) || StateEnum.BUSI_E.equals(nowState)){
            throw new RuntimeException("当前节点["+nowState+"]无法查询下一节点");
        }

        // 获取配置并转化为list
        ConfigParamDTO nextNodeConf = ConfigParamHelper.getNextNodeConf(nowState);
        String nextCodesStr = nextNodeConf.getConfigValue2();
        if(StringUtils.isBlank(nextCodesStr)){
            return Collections.emptyList();
        }
        return CommonStrUtil.splitStr2List(nextCodesStr);
    }

    /**
     * description: [获取有权限控制的节点] PS:此方法预留,后期权限实现后使用
     * @return Map<String, String> Map<nodeCode, permCode>
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static Map<String, String> getPermNode(){
        List<ConfigParamDTO> list = ConfigParamHelper.getConfByCode(CommonConsts.SYS_CODE, CommonConsts.NODE_CONF);
        return list.stream().filter(o -> Objects.nonNull(o.getPermCode()))
                .collect(Collectors.toMap(ConfigParamDTO::getConfigValue1, ConfigParamDTO::getPermCode));
    }

    /**
     * description: [根据stateCode获取描述]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/8
     */
    public static String getStateName(String stateCode){
        for (StateEnum stateEnum : values()){
            if(stateEnum.getStateCode().equals(stateCode)){
                return stateEnum.getStateDesc();
            }
        }
        throw new RuntimeException("无法识别的需求状态标识:["+stateCode+"]");
    }
}
