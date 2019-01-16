package com.angel.user.utils;

import org.apache.tomcat.util.buf.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author JingXiang Bi
 * @date 2019/1/16
 */
public class MD5Util {
    public static String getMd5(String value) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(value.getBytes("utf-8"));

            return HexUtils.toHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
