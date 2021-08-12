package com.west.business.util;

import com.west.business.controller.DemoController;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyleConstants;
import java.lang.reflect.Method;

/**
 * description: [热加载交换class文件相关工具类]
 * title: ClassUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/24
 */
public class ClassUtils {

    public static boolean reloadClass() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.west.business.controller.DemoController");

        CtMethod personFly = cc.getDeclaredMethod("lookNowSys");
        personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
        personFly.insertAfter("System.out.println(\"成功落地。。。。\");");

        DemoController bean = (DemoController)Class.forName("com.west.business.controller.DemoController").newInstance();
        bean.lookNowSys();
        cc.writeFile();
        bean.lookNowSys();
        //新增一个方法
        /*CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        cc.addMethod(ctMethod);

        Object person = cc.toClass().newInstance();
        // 调用 personFly 方法
        Method personFlyMethod = person.getClass().getMethod("personFly");
        personFlyMethod.invoke(person);
        //调用 joinFriend 方法
        Method execute = person.getClass().getMethod("joinFriend");
        execute.invoke(person);*/
        return false;
    }
}
