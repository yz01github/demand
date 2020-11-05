package com.west.business.controller.demo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMultimap;
import com.west.business.util.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * description: []
 * title: AreaNationBuildSQL
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/24
 */
@Slf4j
public class AreaNationBuildSQL {
    private static String areaNames = "亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,亚洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,欧洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,南美洲,航空漫游,海事漫游,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,非洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,大洋洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲,北美洲";
    private static String parentNames = "韩国,新加坡,泰国,马来西亚,以色列,约旦河西岸,日本,印度尼西亚,阿联酋,土耳其,菲律宾,巴基斯坦,越南,印度,柬埔寨,蒙古,巴林,沙特,马尔代夫,亚美尼亚,塔吉克斯坦,也门,老挝,乌兹别克斯坦,斯里兰卡,阿塞拜疆,格鲁吉亚,阿富汗,阿曼,哈萨克斯坦,卡塔尔,科威特,孟加拉国,尼泊尔,文莱,约旦,伊拉克,叙利亚,黎巴嫩,巴勒斯坦,东帝汶,吉尔吉斯斯坦,缅甸,土库曼斯坦,伊朗,不丹,中国香港,中国台湾,中国澳门,英国,德国,法国,西班牙,英国,英国,英国,法国,摩纳哥,英国,西班牙,西班牙,西班牙,西班牙,西班牙,西班牙,西班牙,西班牙,西班牙,捷克,马耳他,希腊,希腊,希腊,希腊,希腊,希腊,波兰,瑞士,匈牙利,列支敦士登,意大利,意大利,意大利,梵蒂冈,圣马力诺,奥地利,荷兰,荷兰,荷兰,罗马尼亚,斯洛伐克,比利时,爱尔兰,葡萄牙,葡萄牙,葡萄牙,葡萄牙,保加利亚,白俄罗斯,丹麦,丹麦,丹麦,丹麦,瑞典,瑞典,瑞典,卢森堡,挪威,挪威,挪威,挪威,拉脱维亚,芬兰,芬兰,泽西岛,乌克兰,俄罗斯,立陶宛,摩尔多瓦,塞浦路斯,直布罗陀,阿尔巴尼亚,爱沙尼亚,法罗群岛,海峡群岛,黑山,曼岛,马其顿,塞尔维亚,冰岛,波黑,克罗地亚,斯洛文尼亚,科索沃,安道尔,格陵兰,巴西,苏里南,阿根廷,智利,巴拉圭,乌拉圭,厄瓜多尔,法属圭亚那,马提尼岛,圭亚那,贝弗敖群岛,哥伦比亚,秘鲁,委内瑞拉,玻利维亚,航空漫游,海事漫游,埃及,南非,加纳,尼日利亚,马达加斯加,纳米比亚,乌干达,莫桑比克,毛里求斯,留尼汪岛,坦桑尼亚,喀麦隆,肯尼亚,阿尔及利亚,刚果,刚果民主共和国,科特迪瓦（象牙海岸）,留尼汪岛,摩洛哥,赞比亚,布隆迪,中非共和国,莱索托,斯威士兰,贝宁,博茨瓦纳,利比里亚,卢旺达,多哥,佛得角,加蓬,利比亚,乍得,埃塞俄比亚,安哥拉,布基纳法索,赤道几内亚,冈比亚,津巴布韦,马拉维,马里,尼日尔,塞拉利昂,塞内加尔,塞舌尔,苏丹,突尼斯,几内亚比绍,吉布提,南苏丹,几内亚,毛里塔尼亚,科摩罗,圣多美和普林西比,美国(本土),新西兰,新西兰,澳大利亚,巴布亚新几内亚,所罗门群岛,瓦努阿图,斐济,关岛,天宁岛,塞班岛,帛琉岛（帕劳）,塔西提岛,密克罗尼西亚,汤加,萨摩亚,新喀里多尼亚,瑙鲁,基里巴斯,美国(本土),美国(本土),美国(本土),加拿大,巴拿马,墨西哥,洪都拉斯,危地马拉,瓜德罗普岛,牙买加,圣基茨和尼维斯,安圭拉,巴巴多斯,开曼群岛,格林纳达,圣卢西亚,圣文森特,安提瓜岛,巴布达,格林纳丁斯群岛,特克斯和凯克斯群岛,海地,库拉索岛和博奈尔岛,特立尼达和多巴哥,萨尔瓦多,巴哈马,百慕大,哥斯达黎加,阿鲁巴,库拉索岛和博奈尔岛,多米尼加共和国,多米尼克,古巴,英属维尔京群岛,尼加拉瓜,伯利兹,蒙特塞拉特岛";
    private static String nationNames = "韩国,新加坡,泰国,马来西亚,以色列,约旦河西岸,日本,印度尼西亚,阿联酋,土耳其,菲律宾,巴基斯坦,越南,印度,柬埔寨,蒙古,巴林,沙特,马尔代夫,亚美尼亚,塔吉克斯坦,也门,老挝,乌兹别克斯坦,斯里兰卡,阿塞拜疆,格鲁吉亚,阿富汗,阿曼,哈萨克斯坦,卡塔尔,科威特,孟加拉国,尼泊尔,文莱,约旦,伊拉克,叙利亚,黎巴嫩,巴勒斯坦,东帝汶,吉尔吉斯斯坦,缅甸,土库曼斯坦,伊朗,不丹,中国香港,中国台湾,中国澳门,英国,德国,法国,西班牙,奥克尼群岛,北爱尔兰,郝布里底群岛,科西嘉岛,摩纳哥,设得兰群岛,西班牙福门特拉,西班牙加那利群岛,西班牙卡夫雷拉岛,西班牙卡那利群岛,西班牙马略卡岛,西班牙梅诺卡岛,西班牙美利利亚,西班牙切乌塔,西班牙伊比沙岛,捷克,马耳他,希腊,克里特岛,基克拉泽,罗德岛,爱奥尼亚,伯罗奔尼撒,波兰,瑞士,匈牙利,列支敦士登,意大利,意大利西西里岛,意大利撒丁岛,梵蒂冈,圣马力诺,奥地利,荷兰,西佛里西亚群岛,南贝佛兰岛,罗马尼亚,斯洛伐克,比利时,爱尔兰,葡萄牙,亚述尔群岛,马德拉群岛,圣港岛,保加利亚,白俄罗斯,丹麦,丹麦朗厄兰岛,波恩荷尔摩岛,丹麦措辛厄岛,瑞典,哥特兰岛,厄兰岛,卢森堡,挪威,斯雅尔巴群岛,罗弗敦群岛,西奥仑群岛,拉脱维亚,芬兰,艾伦岛（奥兰府）,泽西岛,乌克兰,俄罗斯,立陶宛,摩尔多瓦,塞浦路斯,直布罗陀,阿尔巴尼亚,爱沙尼亚,法罗群岛,海峡群岛,黑山,曼岛,马其顿,塞尔维亚,冰岛,波黑,克罗地亚,斯洛文尼亚,科索沃,安道尔,格陵兰,巴西,苏里南,阿根廷,智利,巴拉圭,乌拉圭,厄瓜多尔,法属圭亚那,马提尼岛,圭亚那,贝弗敖群岛,哥伦比亚,秘鲁,委内瑞拉,玻利维亚,航空漫游,海事漫游,埃及,南非,加纳,尼日利亚,马达加斯加,纳米比亚,乌干达,莫桑比克,毛里求斯,留尼汪岛,坦桑尼亚,喀麦隆,肯尼亚,阿尔及利亚,刚果,刚果民主共和国,科特迪瓦（象牙海岸）,马约特岛,摩洛哥,赞比亚,布隆迪,中非共和国,莱索托,斯威士兰,贝宁,博茨瓦纳,利比里亚,卢旺达,多哥,佛得角,加蓬,利比亚,乍得,埃塞俄比亚,安哥拉,布基纳法索,赤道几内亚,冈比亚,津巴布韦,马拉维,马里,尼日尔,塞拉利昂,塞内加尔,塞舌尔,苏丹,突尼斯,几内亚比绍,吉布提,南苏丹,几内亚,毛里塔尼亚,科摩罗,圣多美和普林西比,夏威夷,斯图尔特岛,新西兰,澳大利亚,巴布亚新几内亚,所罗门群岛,瓦努阿图,斐济,关岛,天宁岛,塞班岛,帛琉岛（帕劳）,塔西提岛,密克罗尼西亚,汤加,萨摩亚,新喀里多尼亚,瑙鲁,基里巴斯,美国(本土),波多黎各,美属维尔京群岛,加拿大,巴拿马,墨西哥,洪都拉斯,危地马拉,瓜德罗普岛,牙买加,圣基茨和尼维斯,安圭拉,巴巴多斯,开曼群岛,格林纳达,圣卢西亚,圣文森特,安提瓜岛,巴布达,格林纳丁斯群岛,特克斯和凯克斯群岛,海地,库拉索岛和博奈尔岛,特立尼达和多巴哥,萨尔瓦多,巴哈马,百慕大,哥斯达黎加,阿鲁巴,安的列斯群岛,多米尼加共和国,多米尼克,古巴,英属维尔京群岛,尼加拉瓜,伯利兹,蒙特塞拉特岛";
    private static String englishNationNames = "Korea,Singapore,Thailand,Malaysia,Israel,West Bank,Japan,Indonesia,U.A.E,Turkey,Philippines,Pakistan,Vietnam,India,Cambodia,Mongolia,Bahrain,Saudi Arabia,Maldives,Armenia,Tajikistan,Yemen,Laos,Uzbeksitan,Sri Lanka,Azerbaidjan,Georgia,Afghanistan,Oman,Kazakhstan,Qatar,Kuwait,Bangladesh,Nepal,Brunei,Jordan,Iraq,Syrial,Lebanon,Palestine,East Timor,Kyrgyzstan,Myanmar,Turkmenistan,Iran,Bhutan,Hong Kong,Taiwan,Macau,United Kingdom,Germany,France,Spain,Orkney Island,North Ireland,Hebrides,Corse,Monaco,Shetland,Formentera,Canarian Islands,Cabrera,Canary Islands,Mallora,Menora,Melilla,Ceuta,Ibiza,Czech Republic,Malta,Greece,Crete Island,Cyclades,Rhodes,Ionian,Peloponnese,Poland,Switzerland,Hungary,Liechtenstein,Italy,Ldi Sicilia,Sardinia,Vatican,San Marino,Austria,Netherlands,West Friese Eilanden,Zeeland,Romania,Slovak Republic,Belgium,Ireland,Portugal,Acores Island,Madeira Island,Porto Santo Island,Bulgaria,Belarus,Denmark,Langeland,Bornholm,Tasinge,Sweden,Gotland,Oland,Luxembourg,Norway,Svalbard,Lofoten,Vesteralen,Latvia,Finland,Island Aland,Jersey Island,Ukraine,Russia,Lithuania,Moldova,Cyprus,Gibraltar,Albania,Estonia,Faroe,Gurensey Island,Montenegro,Manx,Macedonia,Serbian,Iceland,Bosnia and Herzegovina,Croatia,Slovenia,Kosovo,Andorra,Greenland,Brazil,Suriname,Argentina,Chile,Paraguay,Uruguay,Ecuador,French West Indies (French Guiana),Martinique,Guyana,The Beverly Islands,Columbia,Peru,Venezuela,Bolivia,Inflight,Maritime,Egypt,South Africa,Ghana,Nigeria,Madagascar,Namibia,Uganda,Mozambique,Mauritius,La Reunion,Tanzania,Cameroon,Kenya,Algeria,Congo,Republic Congo,Cote D'ivoire,Mayotte,Morocco,Zambia,Burundi,Central African Rep,Lesotho,Swaziland,Benin,Botswana,Liberia,Rwanda,Togo,The Republic of Cape Verde,Gabon,Libya,The Republic of Chad,Ethiopia,Angola,Burkina Faso,Equatorial Guinea,Gambia,Zimbabwe,Malavi,Mali,Niger,Sierra Leone,Senegal,Seychelles,Sudan,Tunise,Guinea Bissau,Djibouti,South Sudan,Guinea,Mauritania,Comores,The Democratic Republic of Sao Tome and Principe,Hawaii,Stewart Island,New Zealand,Australia,Papua New Guinea,Solomon Islands,Vanuatu,Fiji,Guam,Tinian Island,Saipan,Palau,Tahiti,Federated States of Micronesia,Tonga,Samoa,New Caledonia,Nauru,Kiribati,U.S.A,Puerto Rico,US Virgin Islands,Canada,Panama,Mexico,Honduras,Guatemala,Guadeloupe,Jamaica,St.Kitts and Nevis,Anguilla,Barbados,Cayman Islands,Grenada,St.Lucia,St.vincent,Antigua,Barbuda,The Grenadines,Turks & Caicos,Haiti,Curacao & Bonaire,Trinidad,El Savador,Bahamas,Bermuda,Costa Rica,Aruba,Netherlands Antilles,Dominican Republic,Dominica,Cuba,Virgin Islands (British),Nicaragua,Belize,Montserrat";
    private static String nationCodes = "101001,101002,101003,101004,101005,101006,101007,101008,101009,101010,101011,101012,101013,101014,101015,101016,101017,101018,101019,101020,101021,101022,101023,101024,101025,101026,101027,101028,101029,101030,101031,101032,101033,101034,101035,101036,101037,101038,101039,101040,101041,101042,101043,101044,101045,101046,101047,101048,101049,201001,201002,201003,201004,201005,201006,201007,201008,201009,201010,201011,201012,201013,201014,201015,201016,201017,201018,201019,201020,201021,201022,201023,201024,201025,201026,201027,201028,201029,201030,201031,201032,201033,201034,201035,201036,201037,201038,201039,201040,201041,201042,201043,201044,201045,201046,201047,201048,201049,201050,201051,201052,201053,201054,201055,201056,201057,201058,201059,201060,201061,201062,201063,201064,201065,201066,201067,201068,201069,201070,201071,201072,201073,201074,201075,201076,201077,201078,201079,201080,201081,201082,201083,201084,201085,201086,201087,301001,301002,301003,301004,301005,301006,301007,301008,301009,301010,301011,301012,301013,301014,301015,401001,501001,601001,601002,601003,601004,601005,601006,601007,601008,601009,601010,601011,601012,601013,601014,601015,601016,601017,601018,601019,601020,601021,601022,601023,601024,601025,601026,601027,601028,601029,601030,601031,601032,601033,601034,601035,601036,601037,601038,601039,601040,601041,601042,601043,601044,601045,601046,601047,601048,601049,601050,601051,601052,601053,601054,701001,701002,701003,701004,701005,701006,701007,701008,701009,701010,701011,701012,701013,701014,701015,701016,701017,701018,701019,801001,801002,801003,801004,801005,801006,801007,801008,801009,801010,801011,801012,801013,801014,801015,801016,801017,801018,801019,801020,801021,801022,801023,801024,801025,801026,801027,801028,801029,801030,801031,801032,801033,801034,801035,801036,801037";

    private static String sql = "insert into uop_cen1.td_s_commpara (SUBSYS_CODE, PARAM_ATTR, PARAM_CODE, PARAM_NAME, PARA_CODE1, PARA_CODE2, PARA_CODE3, PARA_CODE4, PARA_CODE5, PARA_CODE6, PARA_CODE7, PARA_CODE8, PARA_CODE9, PARA_CODE10, PARA_CODE11, PARA_CODE12, PARA_CODE13, PARA_CODE14, PARA_CODE15, PARA_CODE16, PARA_CODE17, PARA_CODE18, PARA_CODE19, PARA_CODE20, PARA_CODE21, PARA_CODE22, PARA_CODE23, PARA_CODE24, PARA_CODE25, PARA_CODE26, PARA_CODE27, PARA_CODE28, PARA_CODE29, PARA_CODE30, START_DATE, END_DATE, EPARCHY_CODE, UPDATE_STAFF_ID, UPDATE_DEPART_ID, UPDATE_TIME, REMARK)\n" +
            "values ('CSM', '1025', 'AREA_NATION_CONFIG', '自选国漫优惠国向数据配置', 'nationCode', 'nationName', 'areaCode', 'areaName', 'parentCode', 'parentName', 'nationEnglishName', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, null, null, null, sysdate, to_date('31-12-2050', 'dd-mm-yyyy'), 'ZZZZ', '', '', sysdate, '国家编码{1},国家名{2},所属州编码{3},所属州名{4}');";

    public static void main(String[] args) {
        List<String> areaNameList = Arrays.asList(areaNames.split(","));

        if(!CollectionUtils.isEmpty(areaNameList)){
            HashSet<String> set = new HashSet<>();
            HashSet<String> set2 = new HashSet<>();
            set.add("aaa");
            set.add("bbb");
            System.out.println(set.toString().substring(1, set.toString().length()-1).replaceAll(" ", ""));
            System.out.println(set2.toString());
            return;
        }

        List<String> parentNameList = Arrays.asList(parentNames.split(","));
        List<String> nationNameList = Arrays.asList(nationNames.split(","));
        List<String> englishNationNameList = Arrays.asList(englishNationNames.split(","));
        List<String> nationCodeList = Arrays.asList(nationCodes.split(","));

        ArrayList<BuildModel> models = new ArrayList<>();
        for (int i = 0; i < areaNameList.size(); i++) {
            String areaName = areaNameList.get(i);
            String parentName = parentNameList.get(i);
            String nationName = nationNameList.get(i);
            String englishNationName = englishNationNameList.get(i);
            String nationCode = nationCodeList.get(i);
            BuildModel buildModel = new BuildModel();
            buildModel.setAreaName(areaName);
            buildModel.setEnglishNationName(englishNationName);
            buildModel.setNationCode(nationCode);
            buildModel.setNationName(nationName);
            buildModel.setParentName(parentName);
            models.add(buildModel);
        }
        log.debug("size:{}",models.size());
        Map<String, String> nationNameMapCode = models.stream().collect(Collectors.toMap(BuildModel::getNationName, BuildModel::getNationCode));
        // "ASIA", "EUROPE", "NORTH", "SOUTH", "AFRICA", "OCEANIA", "ANTARCTICA"
        HashMap<String, String> map = new HashMap<>();
        map.put("亚洲", "ASIA");
        map.put("欧洲", "EUROPE");
        map.put("非洲", "AFRICA");
        map.put("大洋州", "OCEANIA");
        map.put("南美洲", "SOUTH");
        map.put("北美州", "NORTH");
        map.put("海事漫游", "OTHER");
        map.put("航空漫游", "OTHER");

        models.forEach(o -> {
            String parentName = o.getParentName();
            o.setParentCode(nationNameMapCode.get(parentName));
            String areaName = o.getAreaName();
            String areaCode;
            if("亚洲".equals(areaName)){
                areaCode = "ASIA";
            }else if("欧洲".equals(areaName)){
                areaCode = "EUROPE";
            }else if("非洲".equals(areaName)){
                areaCode = "AFRICA";
            }else if("大洋洲".equals(areaName)){
                areaCode = "OCEANIA";
            }else if("南美洲".equals(areaName)){
                areaCode = "SOUTH";
            }else if("北美洲".equals(areaName)){
                areaCode = "NORTH";
            }else if("海事漫游".equals(areaName)){
                areaCode = "OTHER";
            }else if("航空漫游".equals(areaName)){
                areaCode = "OTHER";
            }else {
                areaCode = "";
            }
            o.setAreaCode(areaCode);
        });

        models.forEach(o -> {
            System.out.println(buildSQL(o));
        });
    }

    private static String buildSQL(BuildModel buildModel){
        try {
            String sqlCopy = new String(sql);
            String result1 = sqlCopy.replace("areaCode", buildModel.getAreaCode());
            String result2 = result1.replace("areaName", buildModel.getAreaName());
            String result3 = result2.replace("nationCode", buildModel.getNationCode());
            String result4 = result3.replace("nationName", buildModel.getNationName());
            String result5 = result4.replace("parentCode", buildModel.getParentCode());
            String result6 = result5.replace("parentName", buildModel.getParentName());
            String result7 = result6.replace("nationEnglishName", buildModel.getEnglishNationName());
            return result7;
        }catch (Exception e){
            log.debug(buildModel.toString());
            throw e;
        }
    }
}
