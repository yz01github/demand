package com.west.business.controller;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.service.demands.DemandsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * description: [需求录入相关接口]
 * title: DemandController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/22
 */
@Api(tags = "需求录入相关接口")
@Slf4j
@Controller
@RequestMapping("/demands")
public class DemandsController {

    @Autowired
    private DemandsService demandsService;

    @ApiOperation(value="录入",notes="录入工时信息")
    @PostMapping("/addDemands")
    @ResponseBody
    public ResResult<Integer> putInfo(/*@ModelAttribute*/ @RequestBody @Valid CreateDemandsVO createDemandsVO) {
        int num = demandsService.createDemand(createDemandsVO);
        if(num > 0){
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(createDemandsVO.getDemandName()).append("]需求录入成功");
            return ResResult.successAddMessage(sb.toString());
        }
        return ResResult.failAddMessage("录入失败,请重新录入!");
    }
}
