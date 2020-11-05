package com.west.business.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.west.business.controller.demo.BuildModel;
import com.west.business.pojo.pub.ResResult;
import com.west.business.service.demo.DemoService;
import com.west.business.util.excel.ExcelUtil;
import com.west.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: Demo</p>  
 * <p>Description: []</p>  
 * @author youngZeu  
 * created 2019年8月6日
 */
@Api(tags = "接口Demo")
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${spring.profiles.active}")
    private String profilesActive;

	@Autowired
	private DemoService demoService;

	@Autowired
	private RedisTemplate redisTemplate;

	@GetMapping("/redistest")
	public String test(String testStr) {
		User user = new User();
		user.setUserName(testStr);
		return "";
	}

	@ApiIgnore
	@GetMapping("/{str}")
	public String test(@PathVariable String str, HttpServletRequest request) {
		return demoService.demoRequest(str + request.getRequestURI());
	}

	@ApiIgnore
	@ApiOperation(value = "作用:接收多条数据", notes = "info备注")
	@PostMapping("/info")
	public String addUser(@RequestBody List<User> user) {
		//System.out.println(user.get(0).getDate().toLocaleString());
		System.out.println(user);

		return null;
	}

    @ApiOperation(value = "查看运行环境", notes = "查看当前所运行的环境(dev/prd/pre/test)")
    @GetMapping("/runtimeSystem")
    public ResResult<String> lookNowSys() {
        return ResResult.successAddData(profilesActive);
    }

    @PostMapping("/excel")
	public void aopTest(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams params = new ImportParams();
        List<BuildModel> result = ExcelImportUtil.importExcel(multipartFile.getInputStream(),BuildModel.class, params);
	    log.debug(result.toString());
	}

	private static String sql = "insert into uop_cen1.td_s_commpara (SUBSYS_CODE, PARAM_ATTR, PARAM_CODE, PARAM_NAME, PARA_CODE1, PARA_CODE2, PARA_CODE3, PARA_CODE4, PARA_CODE5, PARA_CODE6, PARA_CODE7, PARA_CODE8, PARA_CODE9, PARA_CODE10, PARA_CODE11, PARA_CODE12, PARA_CODE13, PARA_CODE14, PARA_CODE15, PARA_CODE16, PARA_CODE17, PARA_CODE18, PARA_CODE19, PARA_CODE20, PARA_CODE21, PARA_CODE22, PARA_CODE23, PARA_CODE24, PARA_CODE25, PARA_CODE26, PARA_CODE27, PARA_CODE28, PARA_CODE29, PARA_CODE30, START_DATE, END_DATE, EPARCHY_CODE, UPDATE_STAFF_ID, UPDATE_DEPART_ID, UPDATE_TIME, REMARK)\n" +
			"values ('CSM', '1025', 'AREA_NATION_CONFIG', '自选国漫优惠国向数据配置', 'nationCode', 'nationName', 'areaCode', 'areaName', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, null, null, null, sysdate, to_date('31-12-2050', 'dd-mm-yyyy'), 'ZZZZ', '', '', sysdate, '国家编码{1},国家名{2},所属州编码{3},所属州名{4}');";

	private static String nationNames = "澳大利亚、新西兰、斯图尔特岛、巴布亚新几内亚、斐济、瑙鲁、瓦努阿图、汤加、萨摩亚、塞班岛、天宁岛、关岛";

	private static String areaCode = "OCEANIA";

	private static String areaName = "大洋洲";

	// "ASIA" 亚洲, "EUROPE"欧洲,  "SOUTH"美洲, "AFRICA"非洲, "OCEANIA"大洋洲, "ANTARCTICA"南极洲

	public static void main(String[] args) {

		List<String> list = Arrays.asList(nationNames.split("、"));

		for (int i = 0; i < list.size(); i++) {
			String nationCode = buildNationCode(areaCode, i);
			String nationName = list.get(i);
			String sqlStr = buildSQL(areaCode, areaName, nationCode, nationName);
			System.out.println(sqlStr);
		}
	}

	private static String buildNationCode(String areaCode, int i) {
		String strNum = String.valueOf(i);
		while (strNum.length() != 4){
			strNum = "0"+strNum;
		}
		return areaCode+"["+strNum+"]";
	}

	private static String buildSQL(String areaCode, String areaName, String nationCode, String nationName){
		String sqlCopy = new String(sql);
		String result1 = sqlCopy.replace("areaCode", areaCode);
		String result2 = result1.replace("areaName", areaName);
		String result3 = result2.replace("nationCode", nationCode);
		String result4 = result3.replace("nationName", nationName);
		return result4;
	}

}
