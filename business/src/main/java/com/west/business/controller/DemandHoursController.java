package com.west.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.demandHours.CreateDemandHoursVO;
import com.west.business.pojo.vo.demandHours.SearchDemandHoursVO;
import com.west.business.pojo.vo.demandHours.UpdateDemandHoursVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.service.demandHours.DemandHoursService;
import com.west.domain.entity.DemandHours;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "工时录入")
@Slf4j
@Controller
@RequestMapping("/demandHours")
public class DemandHoursController {

    @Autowired
    private DemandHoursService demandHoursService;


    @ApiOperation(value="录入",notes="录入工时信息")
    @PostMapping("/infos")
    @ResponseBody
    public ResResult<Integer> putInfo(/*@ModelAttribute*/ @RequestBody @Valid CreateDemandHoursVO createDemandHoursVO) {
        int num = demandHoursService.createDemandHours(createDemandHoursVO);
        if(num > 0){
            StringBuilder sb = new StringBuilder(createDemandHoursVO.getProvName()+"");
            sb.append("[").append(createDemandHoursVO.getDemandName()).append("]需求记录录入成功");
            return ResResult.successAddMessage(sb.toString());
        }
        return ResResult.failAddMessage("录入失败,请重新录入!");
    }

    @ApiOperation(value="测试录入",notes="测试录入工时")
    @PostMapping("/testCreate")
    @ResponseBody
    public ResResult<Integer> testCreate(@ModelAttribute @Valid CreateDemandHoursVO createDemandHoursVO) {
        return putInfo(createDemandHoursVO);
    }

    @ApiOperation(value="验证录入",notes="查询今日该员工已录入的周报数据")
    @GetMapping("/{name}")
    @ResponseBody
    @ApiResponse(code = 200, message = "请求正常", response = CreateDemandHoursVO.class)
    public ResResult<List<CreateDemandHoursVO>> validInput(@NotBlank @PathVariable("name") String name) {
        List<CreateDemandHoursVO> infoVOs = demandHoursService.searchByName(name);

        List<String> list = infoVOs.stream().map(o -> o.getProvName()).distinct().collect(Collectors.toList());

        ResResult<List<CreateDemandHoursVO>> result = ResResult.successAddData(infoVOs);
        StringBuilder sb = new StringBuilder("员工[").append(name).append("]今天共提交");
        sb.append(infoVOs.size()).append("条周报;涵盖省份:").append(list).append(";具体记录详情查看下方数据.");
        result.setMessage(sb.toString());
        return result;
    }

    @ApiOperation(value="查询记录",notes="查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", required = false,
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量", required = false,
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandName", value = "需求名", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "provName", value = "省份名", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "searchStartTime", value = "录入时间_开始条件,格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "searchEndTime", value = "录入时间_结束条件,格式:yyyy-MM-dd", required = false,
                    paramType = "query", dataType = "String")
    })
    @GetMapping("/all")
    @ResponseBody
    public ResResult<IPage<CreateDemandHoursVO>> qryDemand(SearchDemandHoursVO searchVO, PageVO<DemandHours> pageVO) {
        IPage<CreateDemandHoursVO> iPage = demandHoursService.qryAll(searchVO, pageVO);
        return ResResult.successAddData(iPage);
    }

    @ApiOperation(value="修改",notes="修改已录入的工时信息")
    @PutMapping("/updateDemandHours")
    @ResponseBody
    public ResResult<Integer> updateInfo(@RequestBody @Valid UpdateDemandHoursVO demandHoursVO) {
        int num = demandHoursService.updateDemandHours(demandHoursVO);
        return ResResult.successAddData(num);
    }
}
