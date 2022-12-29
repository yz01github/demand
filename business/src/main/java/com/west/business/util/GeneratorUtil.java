package com.west.business.util;

import com.west.business.util.date.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import java.util.UUID;

/**
 * description: []
 * title: GeneratorUtil
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/30
 */
public class GeneratorUtil {

    /**
     * description: [生成唯一标识UUID]
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static final String getUUID(){
        return UUID.randomUUID().toString().replace("-", "a");
    }


    public static String generateUserId() {
        String seqId = SeqUtils.genSeq(8, SeqUtils.USER_SEQ_ID);
        // 极端高并发情况，可能存在重复id，可尝试以主机编号替换 "290"
        return "290"+ DateUtils.getSysTimeyyyyMMddHHmmssSSS()+seqId;
    }

    /**
     * description: [生成缓存key:角色下所有权限列表缓存key]
     * @param   roleId  角色id
     * @return  String  缓存key
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static String generateRolePermCacheId(String roleId) {
        return "CACHE^ROLE_PERMS^" + roleId;
    }

    /**
     * description: [生成缓存key:所有权限信息的缓存key]
     * @return  String  缓存key
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static String generateAllPermCacheId() {
        return "CACHE^PERM_INFO^ALL_MAP";
    }
}
