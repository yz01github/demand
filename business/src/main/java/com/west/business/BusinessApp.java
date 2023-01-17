package com.west.business;

import com.west.business.consts.FileSuffixConsts;
import com.west.business.pojo.vo.user.CreateUserVO;
import com.west.business.util.CommonStrUtil;
import com.west.business.util.ZXingUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @EnableEurekaClient		// 将当前项目标记为客户端
// @EnableFeignClients
@EnableCaching  // 开启基于注解的缓存
@ComponentScan(basePackages = {"com.west.business.config",
        "com.west.business.controller",
        "com.west.business.util",// util 要扫描的原因见RedisUtil
        "com.west.business.service"})
@MapperScan(value = "com.west.domain.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BusinessApp{

    /**
     * description: []
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2023/1/4
     */
    // java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(BusinessApp.class, args);
        com.west.business.util.SpringContextUtils.setContext(context);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                int i = Thread.activeCount();
                System.out.println("aaaaaaa:"+i);
                while (Thread.activeCount()<=0){
                    Thread.sleep(1000);
                }
            }
        }
        );
    }

    private static void test9() throws Exception {
        // String aaa = SysDateMgr.getSysDate("YYYYMMDDHH24MISS");
    }

    private static boolean test8(String orderType, String productOfferingId) {
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> stringList26 = Arrays.asList("2","6");

        if (!stringList.contains(orderType)
                ||(stringList26.contains(orderType) && StringUtils.isBlank(productOfferingId))) {
            return true;
        }
        return false;
    }

    private static void test7() throws Exception {
        /**
         * 生成二维码
         * 加密：将文字其他东西放在图片里面
         * 解密：反之
         */
        String projPath = System.getProperty("user.dir");
        String imagePath = projPath+"/business/src/main/resources/img/webapp.jpg";
        String imgPath = projPath+"\\business\\src\\main\\resources\\img\\logo.jpg";
        String content = "https://www.baidu.com/";
        // ZXingUtil.encodeimage(imagePath, "JPEG", content, 430, 430 , imgPath);
        ZXingUtil.genQRCode(imagePath, FileSuffixConsts.JPEG, content, 430, 430 , "");
        /**
         * 解密 -->将二维码内部的文字显示出来
         */
        String s = ZXingUtil.parseQRCode(new File(imagePath));
        System.out.println();
    }

    private static void test6() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            StringBuilder builder = new StringBuilder();
            String s1 = UUID.randomUUID().toString();
            String s2 = UUID.randomUUID().toString();
            String s6 = builder.append(s1).append(s2).toString();
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }

    // 1560 2281 1906 1603 1665 1736
    private static void test5() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String s1 = UUID.randomUUID().toString();
            String s2 = UUID.randomUUID().toString();
            String s6 = s1+s2;
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }

    private static void test4() throws UnsupportedEncodingException {
        CreateUserVO createUserVO = new CreateUserVO();
        byte[] strGBK = "UTF8编码".getBytes("UTF-8");
        byte[] strGB2312 = "UTF8编码".getBytes("GB2312");
        String str = new String(strGBK, "UTF-8");
        String str2 = new String(strGBK, "GB2312");
        System.out.println(str2.equals(str));

        String string = new String("[\"{\"IN_MODE_CODE\":\"6\",\"ACCESSTOKEN\":\"20210507191805c787f5ed456544e99630f492534319f4-0238-1617794285\",\"OPR_TIME\":\"20210421084122\",\"X_RSPTYPE\":\"2\",\"TMSISDN\":\"\",\"PROVINCE_CODE\":\"971\",\"TIMESTAMP\":\"20210421084122\",\"SIGN\":\"8DDC3A749EFECC131EF6397C70B4D48A\",\"SIGNMETHOD\":\"md5\",\"X_RESULTCODE\":\"\",\"BIZ_CODE\":\"698039020100000117\",\"CHARGE_TYPE\":\"3\",\"SPID\":\"698039\",\"BIPCODE\":\"synBizOrder\",\"EXTEND_INFO\":[{\"INFO_VALUE\":\"这玩意儿用来测试\",\"INFO_CODE\":\"ChannelCode\"}],\"X_RSPDESC\":\"\",\"TRADE_STAFF_ID\":\"IBOSS000\",\"TESTFLAG\":\"1\",\"CutOffDay\":\"20210421\",\"BRAND\":\"\",\"BIPVER\":\"0100\",\"transIDO\":\"MIGU202104210841220000323422\",\"CHANNEL\":\"13\",\"OPER_NUMB\":\"MIGU202104210841220000323422\",\"TRADE_EPARCHY_CODE\":\"INTF\",\"ENVFLAG\":\"1\",\"TransIDO\":\"MIGU202104210841220000323422\",\"TRADE_CITY_CODE\":\"INTF\",\"TRADE_DEPART_ID\":\"00087\",\"cutOffDay\":\"20210421\",\"DOMAIN\":\"COP\",\"SESSIONID\":\"MIGU202104210841220000323422\",\"ROUTEVALUE\":\"13709741069\",\"ROUTETYPE\":\"01\",\"ACTIVITYCODE\":\"bizOrder\",\"X_TRANS_CODE\":\"SS.PlatRegSVC.tradeRegIntf\",\"OPR_SOURCE\":\"13\",\"X_RSPCODE\":\"\",\"ID_TYPE\":\"01\",\"SERIAL_NUMBER\":\"13709741069\",\"BUSI_TYPE\":\"MIGU\",\"BUSI_SIGN\":\"synBizOrder_bizOrder_1_0\",\"KIND_ID\":\"synBizOrder_bizOrder_1_0\",\"IMSI\":\"\",\"BIZ_TYPE_CODE\":\"81\",\"X_RESULTINFO\":\"\",\"KIND_ID_NEW\":\"synBizOrder_1.0.1_MIGU_1\",\"SERVICEPACKNAME\":\"\",\"ORIGDOMAIN\":\"MIGU\",\"OPER_CODE\":\"01\",\"EFFT_TIME\":\"20210421084122\",\"CUTOFFDAY\":\"20210421\",\"BUSITYPE\":\"MIGU\",\"USERPARTYID\":\"MIGU2500\",\"SVCCONTVER\":\"0100\",\"ACTIONCODE\":\"0\",\"TRANSIDO\":\"MIGU202104210841220000323422\",\"HOMEDOMAIN\":\"BOSS\",\"VERSION\":\"1.0.1\",\"APICODE\":\"\"}\"]");
        List<String> acceptDataset = CommonStrUtil.stringSubsection(string, 300);

        System.out.println(acceptDataset);
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