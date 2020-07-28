package com.west.business.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Objects;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.service.demand.DemandService;
import com.west.business.util.excel.ColorsStyle;
import com.west.business.util.excel.ExcelUtil;
import com.west.domain.entity.DemandInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: [周报统计相关接口]
 * title: DemandController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/22
 */
@Api(tags = "周报统计相关接口")
@Slf4j
@Controller
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private DemandService demandService;


    @ApiOperation(value="录入",notes="录入周报记录")
    @PostMapping("/infos")
    @ResponseBody
    public ResResult<Integer> putInfo(/*@ModelAttribute*/ @RequestBody @Valid DemandInfoVO demandInfoVO) {
        replaceValue(demandInfoVO);
        log.debug("demandInfoVO;{}", demandInfoVO);
        int num = demandService.createDemand(demandInfoVO);
        if(num > 0){
            StringBuilder sb = new StringBuilder(demandInfoVO.getProvName()+"");
            sb.append("[").append(demandInfoVO.getDemandName()).append("]需求记录录入成功");
            return ResResult.successAddMessage(sb.toString());
        }
        return ResResult.failAddMessage("录入失败,请重新录入!");
    }

    private void replaceValue(DemandInfoVO demandInfoVO) {
        if(StringUtils.isNotBlank(demandInfoVO.getIsOver())){
            demandInfoVO.setIsOver(Objects.equal(demandInfoVO.getIsOver(), "是") ? "1" : "0");
        }else{
            demandInfoVO.setIsOver("");
        }
        if(StringUtils.isNotBlank(demandInfoVO.getReleaseSuccess())){
            demandInfoVO.setReleaseSuccess(Objects.equal(demandInfoVO.getReleaseSuccess(), "是") ? "1" : "0");
        }else{
            demandInfoVO.setReleaseSuccess("");
        }
    }

    @ResponseBody
    @ApiOperation(value="导出",notes="导出所有记录到Excel")
    @GetMapping("/file")
    public void demand(HttpServletResponse response) throws IOException {
        List<DemandInfoVO> collect = demandService.qryExcelData();
        ExportParams exportParams = new ExportParams();
        exportParams.setHeight((short) 7);
        exportParams.setTitleHeight((short)7);
        exportParams.setColor(HSSFColor.HSSFColorPredefined.LIGHT_TURQUOISE.getIndex());
        exportParams.setStyle(ColorsStyle.class);
        ExcelUtil.defaultExport(collect, DemandInfoVO.class, "fileNameYZ", response, exportParams);
        log.debug("end...");
    }

    @ApiOperation(value="查询记录",notes="查询所有未逻辑删除的已有数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = false,
                paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页数据量", required = false,
                paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandName", value = "需求名", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "releaseSuccess", value = "上线是否成功,0否1是", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "provName", value = "省份名", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "searchStartTime", value = "需求提出时间_开始条件,格式:yyyy-MM-dd,例:2020-07-01,请勿省略日期中的0,下同", required = false,
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "searchEndTime", value = "需求提出时间_结束条件,格式:yyyy-MM-dd", required = false,
                    paramType = "query", dataType = "String")
    })
    @GetMapping("/all")
    @ResponseBody
    public ResResult<IPage<DemandInfoVO>> qryDemand(SearchDemandVO searchVO, PageVO<DemandInfo> pageVO) {
        IPage<DemandInfoVO> iPage = demandService.qryAll(searchVO, pageVO);
        return ResResult.successAddData(iPage);
    }

    @ApiOperation(value="验证录入",notes="查询今日该员工已录入的周报数据")
    @GetMapping("/{name}")
    @ResponseBody
    @ApiResponse(code = 200, message = "请求正常", response = DemandInfoVO.class)
    public ResResult<List<DemandInfoVO>> validInput(@NotBlank @PathVariable("name") String name) {
        List<DemandInfoVO> infoVOs = demandService.searchByName(name);

        List<String> list = infoVOs.stream().map(o -> o.getProvName()).distinct().collect(Collectors.toList());

        ResResult<List<DemandInfoVO>> result = ResResult.successAddData(infoVOs);
        StringBuilder sb = new StringBuilder("员工[").append(name).append("]今天共提交");
        sb.append(infoVOs.size()).append("条周报;涵盖省份:").append(list).append(";具体记录详情查看下方数据.");
        result.setMessage(sb.toString());
        return result;
    }

}
