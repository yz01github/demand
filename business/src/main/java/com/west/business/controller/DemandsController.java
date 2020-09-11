package com.west.business.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.demands.CreateDemandsVO;
import com.west.business.pojo.vo.demands.DemandsVO;
import com.west.business.pojo.vo.demands.QueryDemandVO;
import com.west.business.pojo.vo.demands.UpdateDemandsStateVO;
import com.west.business.pojo.vo.demands.UpdateDemandsVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.service.demands.DemandsService;
import com.west.business.util.NumberUtil;
import com.west.domain.entity.DemandInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * description: [需求录入相关接口]
 * title: DemandController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/22
 */
@Api(tags = "需求录入相关接口")
@Slf4j
@RestController
@RequestMapping("/demands")
public class DemandsController {

    @Autowired
    private DemandsService demandsService;

    @ApiOperation(value="录入",notes="录入需求信息")
    @PostMapping("/addDemands")
    public ResResult<Integer> putInfo(/*@ModelAttribute*/ @RequestBody @Valid CreateDemandsVO createDemandsVO) {
        int num = demandsService.createDemand(createDemandsVO);
        if(num > 0){
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(createDemandsVO.getDemandName()).append("]需求录入成功");
            return ResResult.successAddMessage(sb.toString());
        }
        return ResResult.failAddMessage("录入失败,请重新录入!");
    }

    @ApiOperation(value="更新",notes="需求状态更新")
    @PutMapping("/state")
    public ResResult updateDemandState(@RequestBody @Valid UpdateDemandsStateVO demandVO) {
        String nextState = demandVO.getNextState();
        String demandState = demandVO.getDemandState();
        Integer devProgress = demandVO.getDevProgress();

        boolean isUpdateState = (StringUtils.isBlank(nextState) || StringUtils.isBlank(demandState));
        boolean isUpdateProgress = Objects.nonNull(devProgress);

        if(!isUpdateState && !isUpdateProgress){
            log.error("更新参数缺失,请输入节点信息或进度信息;demandState:{};nextState:{}devProgress:{}",
                    demandState, nextState, devProgress);
            ResResult.failAddMessage("更新参数缺失,请输入节点信息或进度信息！");
        }
        if(isUpdateProgress && NumberUtil.notBetween(devProgress, 0, 100)){
            ResResult.failAddMessage("请输入正确的进度数字[0-100]");
        }

        int num = demandsService.updateDemandState(demandVO);
        return ResResult.result(num > 0);
    }

    /**
     * description: [查询指定用户负责的、当前未完成的需求]
     * @param   qryVO   查询条件
     * @param   pageVO  分页条件
     * @return ResResult<IPage<DemandsVO>>  查询结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="查询",notes="查询指定用户负责的、当前未完成的需求")
    @GetMapping("/todoDemand")
    public ResResult<IPage<DemandsVO>> updateDemandState(@Valid QueryDemandVO qryVO, PageVO<DemandInfo> pageVO) {
        IPage<DemandsVO> iPage = demandsService.qryTodoDemandsByOwnerId(qryVO, pageVO);
        return ResResult.successAddData(iPage);
    }

    @ApiOperation(value="查询",notes="查询所有需求,此功能后期应当列入功能权限")
    @GetMapping("/allDemand")
    public ResResult<IPage<DemandsVO>> qryAllByCond(DemandsVO qryVO, PageVO<DemandInfo> pageVO) {
        IPage<DemandsVO> iPage = demandsService.qryAllByCond(qryVO, pageVO);
        return ResResult.successAddData(iPage);
    }

    /**
     * description: [修改需求信息]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="修改需求信息",notes="修改需求信息,无法修改需求状态,此功能后期应当列入功能权限")
    @GetMapping("/allDemand")
    public ResResult<Integer> updateDemand(UpdateDemandsVO demandInfo) {
        int num = demandsService.updateDemand(demandInfo);
        return ResResult.successAddData(num);
    }

    /**
     * description: [删除]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/10
     */
    @ApiOperation(value="删除",notes="删除指定需求,此功能后期应当列入功能权限")
    @GetMapping("/{demandId}")
    public ResResult deleteDemand(@PathVariable("demandId") String demandId) {
        int num = demandsService.deleteDemand(demandId);
        return ResResult.result(num > 0);
    }
}
