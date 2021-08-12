package com.west.business.config;

import com.west.business.pojo.dto.context.URP;
import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.User;

/**
 * description: []
 * title: ThreadContext
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/15
 */
public class ThreadContext {

    public static ThreadLocal<URP> currURP = new ThreadLocal<>();
}
