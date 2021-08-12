package com.west.business.util;

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

    /**
     * description: [生成缓存key:角色下所有权限列表缓存key]
     * @param   roleId  角色id
     * @return  String  缓存key
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static String generaRolePermCacheId(String roleId) {
        return "CACHE^ROLE_PERMS^" + roleId;
    }

    /**
     * description: [生成缓存key:所有权限信息的缓存key]
     * @return  String  缓存key
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/9/14
     */
    public static String generaAllPermCacheId() {
        return "CACHE^PERM_INFO^ALL_MAP";
    }
}
