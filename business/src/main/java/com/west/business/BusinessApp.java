package com.west.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.util.CommonStrUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// @EnableEurekaClient		// 将当前项目标记为客户端
// @EnableFeignClients
@ComponentScan(basePackages = {"com.west.business.config","com.west.business.controller",
        "com.west.business.service"})
@MapperScan(value = "com.west.domain.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BusinessApp{
	
    public static void main(String[] args){
        // test();
        //test1();
//        test2();
        // test3();
        // test4();
         test5();

        //SpringApplication.run(BusinessApp.class, args);
    }

    private static void test5() {
        boolean res = !"1".equals("2");
        System.out.println(res);
    }

    private static void test4() {
        CreateUserVO createUserVO = new CreateUserVO();
        String string = new String("[\"{\"IN_MODE_CODE\":\"6\",\"ACCESSTOKEN\":\"20210507191805c787f5ed456544e99630f492534319f4-0238-1617794285\",\"OPR_TIME\":\"20210421084122\",\"X_RSPTYPE\":\"2\",\"TMSISDN\":\"\",\"PROVINCE_CODE\":\"971\",\"TIMESTAMP\":\"20210421084122\",\"SIGN\":\"8DDC3A749EFECC131EF6397C70B4D48A\",\"SIGNMETHOD\":\"md5\",\"X_RESULTCODE\":\"\",\"BIZ_CODE\":\"698039020100000117\",\"CHARGE_TYPE\":\"3\",\"SPID\":\"698039\",\"BIPCODE\":\"synBizOrder\",\"EXTEND_INFO\":[{\"INFO_VALUE\":\"0030018\",\"INFO_CODE\":\"ChannelCode\"}],\"X_RSPDESC\":\"\",\"TRADE_STAFF_ID\":\"IBOSS000\",\"TESTFLAG\":\"1\",\"CutOffDay\":\"20210421\",\"BRAND\":\"\",\"BIPVER\":\"0100\",\"transIDO\":\"MIGU202104210841220000323422\",\"CHANNEL\":\"13\",\"OPER_NUMB\":\"MIGU202104210841220000323422\",\"TRADE_EPARCHY_CODE\":\"INTF\",\"ENVFLAG\":\"1\",\"TransIDO\":\"MIGU202104210841220000323422\",\"TRADE_CITY_CODE\":\"INTF\",\"TRADE_DEPART_ID\":\"00087\",\"cutOffDay\":\"20210421\",\"DOMAIN\":\"COP\",\"SESSIONID\":\"MIGU202104210841220000323422\",\"ROUTEVALUE\":\"13709741069\",\"ROUTETYPE\":\"01\",\"ACTIVITYCODE\":\"bizOrder\",\"X_TRANS_CODE\":\"SS.PlatRegSVC.tradeRegIntf\",\"OPR_SOURCE\":\"13\",\"X_RSPCODE\":\"\",\"ID_TYPE\":\"01\",\"SERIAL_NUMBER\":\"13709741069\",\"BUSI_TYPE\":\"MIGU\",\"BUSI_SIGN\":\"synBizOrder_bizOrder_1_0\",\"KIND_ID\":\"synBizOrder_bizOrder_1_0\",\"IMSI\":\"\",\"BIZ_TYPE_CODE\":\"81\",\"X_RESULTINFO\":\"\",\"KIND_ID_NEW\":\"synBizOrder_1.0.1_MIGU_1\",\"SERVICEPACKNAME\":\"\",\"ORIGDOMAIN\":\"MIGU\",\"OPER_CODE\":\"01\",\"EFFT_TIME\":\"20210421084122\",\"CUTOFFDAY\":\"20210421\",\"BUSITYPE\":\"MIGU\",\"USERPARTYID\":\"MIGU2500\",\"SVCCONTVER\":\"0100\",\"ACTIONCODE\":\"0\",\"TRANSIDO\":\"MIGU202104210841220000323422\",\"HOMEDOMAIN\":\"BOSS\",\"VERSION\":\"1.0.1\",\"APICODE\":\"\"}\"]");
        List<String> acceptDataset = CommonStrUtil.stringSubsection(string, 4000);


// 标记为普通接口
        for (int i = 0; i < acceptDataset.size(); i++) {
            switch (i) {
                case 0:
                    createUserVO.setUserAccount(acceptDataset.get(i));
                    break;
                default:
                    break;
            }
        }
        System.out.println(createUserVO);
    }


    private static void test1() {
        Integer a = 121;
        Integer b = 121;
        boolean res = (a==b);


        System.out.println(res);
        long c = 100L;
        long d = 100L;
        boolean res2 = (c==d);
        System.out.println(res2);

    }
    private static void test3() {
        Map map = (Map)null;
        System.out.println(map);
    }


    private static void test2() {
        String tradeEparchCode = humpToLine("tradeEparchCode", false);
        System.out.println(tradeEparchCode);
    }
    public static String humpToLine(String str, boolean optimization) {
        if (!optimization) {
            return str.replaceAll("[A-Z]", "_$0").toUpperCase();
        }
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }
    private static void test() {
        LocalDateTime now = LocalDateTime.now();
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalDateTime now1 = LocalDateTime.now().plusDays(11);
        System.out.println(now);
        System.out.println(now1);
        Duration between = Duration.between(now1, now);
        long toDays = between.toDays();
        System.out.println(toDays);
    }
}