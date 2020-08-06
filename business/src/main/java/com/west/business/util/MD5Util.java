package com.west.business.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * description: []
 * title: MD5Util
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/8/5
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MD5Util {

    /**
     * description: [MD5加密]
     * @param   text    明文
     * @param   key     密钥
     * @return  String  密文
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/8/5
     */
    public static String md5(String text, String key) {
        String encodeStr = DigestUtils.md5Hex(text + key);
        log.debug("秘钥&明文加密结果:{}", encodeStr);
        return encodeStr;
    }

    /**
     * @see #md5(String)
     */
    public static String md5(String text) {
        String encodeStr= DigestUtils.md5Hex(text);
        log.debug("明文加密结果:{}", encodeStr);
        return encodeStr;
    }

    /**
     * description: [MD5验证方法]
     * @param   text    明文
     * @param   key     密钥
     * @param   md5     密文
     * @return  boolean true/false
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2020/8/5
     */
    public static boolean verify(String text, String key, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        boolean isEq = md5Text.equalsIgnoreCase(md5);
        log.debug("秘钥&明文加密后验证结果:{}", isEq);
        return isEq;
    }
}
