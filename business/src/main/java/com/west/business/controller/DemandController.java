package com.west.business.controller;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.west.business.consts.CommonConsts;
import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.pub.convert.ConvertYN;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.pojo.vo.demand.SearchDemandVO;
import com.west.business.pojo.vo.demand.UpdateDemandVO;
import com.west.business.pojo.vo.page.PageVO;
import com.west.business.pojo.vo.user.QueryUserVO;
import com.west.business.service.demand.DemandService;
import com.west.business.service.user.UserService;
import com.west.business.util.date.DateUtils;
import com.west.business.util.excel.ColorsStyle;
import com.west.business.util.excel.ExcelUtil;
import com.west.domain.entity.DemandInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private UserService userService;


    @ApiOperation(value="录入",notes="录入周报记录")
    @PostMapping("/infos")
    @ResponseBody
    public ResResult<Integer> putInfo(@ModelAttribute /*@RequestBody*/ @Valid DemandInfoVO demandInfoVO) {
        log.debug("demandInfoVO;{}", demandInfoVO);
        String isOver = demandInfoVO.getIsOver();
        List<String> viewYN = Arrays.asList(CommonConsts.VIEW_Y, CommonConsts.VIEW_N);
        if(!viewYN.contains(isOver)){
            return ResResult.failAddMessage("录入失败,请填写正确的\"是否上线\"数据;[是 or 否]!");
        }
        String releaseSuccess = demandInfoVO.getReleaseSuccess();
        if(CommonConsts.VIEW_Y.equals(isOver) && !viewYN.contains(releaseSuccess)){
            return ResResult.failAddMessage("上线后必须输入\"上线结果\"[是 or 否]!");
        }
        if(CommonConsts.VIEW_N.equals(isOver) && StringUtils.isNotBlank(releaseSuccess)){
            return ResResult.failAddMessage("未上线不可输入\"上线结果\"数据");
        }
        try{
            long actualWork = Long.parseLong(demandInfoVO.getActualWork());
            long devProgress = Long.parseLong(demandInfoVO.getDevProgress());
            if(actualWork <=0 || actualWork > 100){
                return ResResult.failAddMessage("录入失败,请输入正确的实际工作量(1-100的整数)!");
            }
            if(devProgress <=0 || devProgress > 100){
                return ResResult.failAddMessage("录入失败,请输入正确的开发进度(1-100的整数)!");
            }
        }catch (Exception e){
            return ResResult.failAddMessage("录入失败,请输入正确的开发进度/实际工作量(1-100的整数)!");
        }
        // 校验 结束
        replaceValue(demandInfoVO);
        int num = demandService.createDemand(demandInfoVO);
        if(num > 0){
            StringBuilder sb = new StringBuilder(demandInfoVO.getProvName()+"");
            sb.append("[").append(demandInfoVO.getDemandName()).append("]需求记录录入成功");
            return ResResult.successAddMessage(sb.toString());
        }
        return ResResult.failAddMessage("录入失败,请重新录入!");
    }

    @ApiOperation(value="测试录入",notes="测试录入周报记录")
    @PostMapping("/testCreate")
    @ResponseBody
    public ResResult<Integer> testCreate(@ModelAttribute @Valid DemandInfoVO demandInfoVO) {
        return putInfo(demandInfoVO);
    }

    private void replaceValue(DemandInfoVO demandInfoVO) {
        demandInfoVO.setIsOver(ConvertYN.convert(demandInfoVO.getIsOver(),false));
        demandInfoVO.setReleaseSuccess(ConvertYN.convert(demandInfoVO.getReleaseSuccess(),false));
        String relatedModules = demandInfoVO.getRelatedModules();
        if(StringUtils.isNotBlank(relatedModules)){
            demandInfoVO.setRelatedModules(relatedModules.toUpperCase());
        }
    }

    @ResponseBody
    @ApiOperation(value="导出",notes="导出所有记录到Excel")
    @GetMapping("/file")
    public void demand(SearchDemandVO searchVO, PageVO<DemandInfo> pageVO, HttpServletResponse response) throws IOException {
        IPage<DemandInfoVO> iPage = demandService.qryAll(searchVO, pageVO);
        List<DemandInfoVO> collect = iPage.getRecords();
        ExportParams exportParams = getExportParams();
        ExcelUtil.defaultExport(collect, DemandInfoVO.class, "fileNameYZ", response, exportParams);
        log.debug("end...");
    }

    @ResponseBody
    @ApiOperation(value="导出今日",notes="导出今天所有记录到Excel")
    @GetMapping("/fileToday")
    public void demand(DemandInfoVO queryVO, HttpServletResponse response) {
        List<DemandInfoVO> collect = demandService.qryExcelData(queryVO);
        ExportParams exportParams = getExportParams();
        ExcelUtil.defaultExport(collect, DemandInfoVO.class, "西北区全网组周报_"+ DateUtils.getSysDateyyyyMMdd(),
                response, exportParams);
        log.debug("end...");
    }

    private ExportParams getExportParams(){
        return ExcelUtil.defaultExportParams();
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

    @ApiOperation(value="修改",notes="修改已录入的周报记录")
    @PutMapping("/")
    @ResponseBody
    public ResResult<Integer> updateInfo(@RequestBody @Valid UpdateDemandVO demandInfoVO) {
        if(StringUtils.isBlank(demandInfoVO.getDemandId())){
            return ResResult.failAddMessage("更新失败,请输入需求唯一标识");
        }
        demandInfoVO.setIsOver(ConvertYN.convert(demandInfoVO.getIsOver(),false));
        demandInfoVO.setReleaseSuccess(ConvertYN.convert(demandInfoVO.getReleaseSuccess(),false));
        int num = demandService.updateDemand(demandInfoVO);
        return ResResult.successAddData(num);
    }

    @ApiOperation(value="录入(ModelAttribute)",notes="录入周报记录")
    @PostMapping("/input")
    @ResponseBody
    public ResResult<Integer> inputInfo(@ModelAttribute @Valid DemandInfoVO demandInfoVO) {
        return putInfo(demandInfoVO);
    }

    @ApiOperation(value="查询未录入周报员工",notes="查询未录入周报员工")
    @GetMapping("/noInputUser")
    @ResponseBody
    public ResResult qryInput(){
        List<QueryUserVO> userVOS = userService.qryAll();
        List<String> allNames = userVOS.stream().map(QueryUserVO::getUserName).collect(Collectors.toList());
        List<DemandInfoVO> infoVOS = demandService.qryExcelData(new DemandInfoVO());
        Set<String> inputUsers = infoVOS.stream().map(DemandInfoVO::getDemandOwner)
                            .collect(Collectors.toSet());
        allNames.removeAll(inputUsers);
        String resStr = new StringBuilder("当前未录入周报的员工有:").append(allNames)
                            .append("\n已录入的员工有:").append(inputUsers).toString();
        return ResResult.successAddData(resStr);
    }

    @ApiOperation(value="删除",notes="删除已录入的周报记录")
    @DeleteMapping("/{demandId}")
    @ResponseBody
    public ResResult<Integer> deleteInfo(@PathVariable("demandId") String demandId) {
        boolean isSuccess = demandService.deleteDemand(demandId);
        return ResResult.result(isSuccess);
    }
}
