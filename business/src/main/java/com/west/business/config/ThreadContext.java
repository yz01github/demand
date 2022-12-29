package com.west.business.config;

import com.west.business.pojo.dto.context.URP;
import com.west.business.pojo.dto.context.Visit;
import com.west.domain.entity.PermissionEntity;
import com.west.domain.entity.RoleEntity;
import com.west.domain.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * description: [权限、用户、角色变量]
 * title: ThreadContext
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/9/15
 */
public class ThreadContext {

    private static ThreadLocal<URP> currURP = ThreadLocal.withInitial(() -> URP.builder().build());

    public static ThreadLocal<Visit> visit = ThreadLocal.withInitial(() -> Visit.builder().build());

    public static URP getURP(){
        return currURP.get();
    }

    public static URP setURP(URP urp){
        return currURP.get();
    }

    public static Visit getVisit(){
        return visit.get();
    }


    public static void setVisit(Visit visitParam){
        visit.set(visitParam);
    }

    public static void setVisitRequest(HttpServletRequest request){
        visit.get().setRequest(request);
    }

    public static void setVisitResponse(HttpServletResponse response){
        visit.get().setResponse(response);
    }

    public static HttpSession getSession(){
        return visit.get().getRequest().getSession();
    }

    public static Cookie[] getCookies(){
        return visit.get().getRequest().getCookies();
    }

    public static void remove(){
        currURP.remove();
        visit.remove();
    }
}
