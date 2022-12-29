package com.west.business;

import com.west.business.service.seq.ISequenceService;
import com.west.business.service.seq.SequenceServiceImpl;
import com.west.business.util.SeqUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: [junit测试样例]
 * title: JunitCase
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/9/28
 */
@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BusinessApp.class)
public class JunitCase {

    @Autowired
    private ISequenceService seqService;

    @Test
    public void testJunit10() throws InterruptedException {
        String str = "1300456789";
        System.out.println(str.substring(4));
    }

    @Test
    public void testJunit3() throws InterruptedException {
        Date date = new Date();
        Thread.sleep(190);
        Date date1 = new Date();
        int compare = date.compareTo(date1);
        int compare1 = "2021-10-15 10:00:00".compareTo("2021-10-18 20:00:00");
        System.out.println(compare);

    }
    @Test
    public void testJunit4() throws InterruptedException {
        List<String> list = Arrays.asList("1. 9-3-502 2", "2. 9-2-0403 1", "3. 9-1-2202  2", "4. 9-2-2403  2", "5. 9-2-1704  2", "6. 9-1-2902 2", "7. 9-1–1001女 1", "8. 9-2-2502  2", "9. 9-2-1903 2", "10. 9-2-0902 2", "11. 9-3-0603 2", "12. 9-3-1201 2", "13. 9-1-2702  2", "14. 9-1-1302 2", "15. 9-2-2803  2", "16. 9-3-2401", "17. 9-2-2001父 1", "18. 9-2-2504  2", "19. 9-22703   2", "20. 9-3-2004男 2", "21. 9-3-2602 2", "22. 9-3-1801 2", "23. 9-2-2603 2", "24. 9-20904男1", "25. 9-3-2601 2", "26. 9-3-0703 2", "27. 9-1-503 2", "28. 9-2-1402  2", "29. 9-1-2604女 1", "30. 9-3-1002 1", "31. 9-3-2902", "32. 9-3-1303   2", "33. 9-2-1303  2", "34. 9-1-0702 1", "35. 9-1-2103 1", "36. 9-2-1504  2", "37. 9-3-0901-王  1", "38. 9-3-2101-姚   2", "39. 9-2-2404   1", "40. 9-2-1601", "41. 9-2-0801 2", "42. 9-3-2201   1", "43. 9-3-1704 1", "44. 9-2-2804   2", "45. 9-32804 1", "46. 9-2-2501 1", "47. 9-1-0701  2", "48. 9-1-1903  2", "49. 9-2-1203 2", "50. 9-3-2204  2", "51. 9-1-1902新新家装饰温刚 1", "52. 9-1-1701 2", "53. 9-2-603   1", "54. 9-1-1002 Ethan  1", "55. 9-2-0802    1", "56. 9-2-2303  1", "57. 9-1-2502   2", "58. 9-1-1004 2", "59. 9-3-803  2", "60. 9-1-1003   1", "61. 9-1-1703    1", "62. 9-1-2402  2", "63. 9-1-2603    2", "64. 9-3-2202     2", "65. 9-2-2202 1", "66. 9-22503    1", "67. 9-3-1304    2", "68. 9-2—2501 2", "69. 9-2-1601 2", "70. 9-3-2802", "71. 9-3-2601  2", "72. 9-2-1803   2", "73. 9-2-1301  2", "74. 9-3-1203男  1", "75. 9-3-0402  2", "76. 9-1-2403   2", "77. 9-2-1703男 2", "78. 9-3-0404    2", "79. 9-3-804女 2", "80. 9-3-1702男 2", "81. 9-2-1403    2", "82. 9-3-2604   2", "83. 9-21802 2", "84. 9-21801   2", "85. 9-2-2103     2", "86. 9-2-0604 2", "87. 9-3-1402   2", "88. 9-2-2302  1", "89. 9-3-1403   2", "90. 9-3-1204 2");
        HashMap<Integer, String> countMap1 = new HashMap<>();
        HashMap<Integer, String> countMap2 = new HashMap<>();
        HashMap<Integer, String> countMap3 = new HashMap<>();
        HashMap<Integer, String> countMapAll = new HashMap<>();
        int unitCount1 = 0;
        int unitCount2 = 0;
        int unitCount3 = 0;
        for (String obj : list) {
            int num = obj.indexOf("-");
            if(num == -1){
                System.out.println(obj+"   有问题");
                continue;
            }
            String unitNum = obj.substring(num+1,num+2);

            Integer floorNum = Integer.valueOf(obj.substring(num + 3, num + 5));
            String houseNum = "";
            if(floorNum > 29 ){
                floorNum = Integer.valueOf(obj.substring(num + 3, num + 4));
                houseNum = obj.substring(num + 4, num + 6);
            }else{
                houseNum = obj.substring(num + 5, num + 7);
            }
            if("1".equals(unitNum)){
                unitCount1++;
                String str = countMap1.get(floorNum);
                countMap1.put(floorNum, Objects.isNull(str) ? floorNum+houseNum: str+","+floorNum+houseNum);
            }else if("2".equals(unitNum)){
                unitCount2++;
                String str = countMap1.get(floorNum);
                countMap2.put(floorNum, Objects.isNull(str) ? floorNum+houseNum: str+","+floorNum+houseNum);
            }else if("3".equals(unitNum)){
                unitCount3++;
                String str = countMap1.get(floorNum);
                countMap3.put(floorNum, Objects.isNull(str) ? floorNum+houseNum: str+","+floorNum+houseNum);
            }
            String str = countMapAll.get(floorNum);
            countMapAll.put(floorNum, Objects.isNull(str) ? unitNum+"-"+floorNum+houseNum: str+","+unitNum+"-"+floorNum+houseNum);


        }
        System.out.println("1单元总人数:"+unitCount1+";2单元总人数:"+unitCount2+";3单元总人数:"+unitCount3);
        for (Map.Entry<Integer, String> entry : countMapAll.entrySet()) {
            System.out.println(+entry.getKey()+"楼:"+(entry.getKey()>9?"\t":"\t\t")+entry.getValue());
        }
        /*for (Map.Entry<Integer, String> entry : countMap2.entrySet()) {
            System.out.println("2单元-"+entry.getKey()+"楼:\t"+entry.getValue());
        }
        for (Map.Entry<Integer, String> entry : countMap3.entrySet()) {
            System.out.println("3单元-"+entry.getKey()+"楼:\t"+entry.getValue());
        }*/
    }

    @Test
    public void testJunit(){
        List<String> strings = Arrays.asList("subOrderList", "orderSubId", "numberOprType", "orderHandleType", "number", "legalName", "certificateType", "certificateNo", "goodsInfo", "goodsId", "goodsTitle", "planCycle", "maxRate", "price", "firstBandTag", "formFlag");
        strings.forEach(key -> {
            System.out.println("subOrderData.put(\""+humpToLine(key,false)+"\",data.getString(\""+key+"\"));");

        });
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

    // 并发情况下序列结果缓存测试
    @Test
    public void testJunit2() throws InterruptedException {
        Map<String, Queue<Long>> map = SequenceServiceImpl.CACHE_SEQ_MAP;
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    log.debug("线程-1,第{}次获取序列:{}; {}",i,SeqUtils.genSeq(), Thread.currentThread().getName());
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    log.debug("线程-2,第{}次获取序列:{}; {}",i,SeqUtils.genSeq(), Thread.currentThread().getName());
                }
            }
        };
        // TODO 这里存在一个问题, 每次序列更新后, 其中一个线程取值时,会跳过一个值, 比如本应时xxx02, 直接取到了xxx03
        new Thread(runnable1).start();
        new Thread(runnable2).start();

        Thread.sleep(1000*30);
        log.debug("测试结束!");
    }
}
