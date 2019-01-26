package com.angel.user.utils;

import java.util.Random;

/**
 * Token工具类
 * @author JingXiang Bi
 * @date 2019/1/16
 */
public class TokenUtil {

    public static String getToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    public static String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for(int i=0;i<size;i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }
}
