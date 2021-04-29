package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * description: []
 * title: CloneUtils
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/29
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CloneUtils {

    /**
     * description: [深copy一个对象返回]
     * @param   from    需要copy的对象
     * @return  Object  copy结果
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/4/29
     */
    public static Object deepCopy(Object from) {
        Object result = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // wirte to stream
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(from);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read to stream
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
            result = ois.readObject();
            bos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
