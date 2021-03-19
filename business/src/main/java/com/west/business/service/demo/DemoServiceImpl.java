package com.west.business.service.demo;

import com.west.business.util.HttpUtil;
import com.west.domain.dao.DemandInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: []
 * title:
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    private DemandInfoDao demandInfoDao;

    @Override
    public Object req(String str) {
        return HttpUtil.sendPostRequest("http://localhost:8071/domain/", str);
    }

    @Override
    public String demoRequest(String reqString) {



        return null;
    }

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                // Class.getResourceAsStream(String path) ： path 不以’/‘开头时默认是从此类所在的包下取资源，以’/‘开头则是从ClassPath根下获取。
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    // 初始化一个长度为文件字节总数的数组
                    byte[] byteArr = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(byteArr);
                    return defineClass(name, byteArr, 0, byteArr.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = classLoader.loadClass("jvm.DifferentClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DemoServiceImpl);

    }

}
