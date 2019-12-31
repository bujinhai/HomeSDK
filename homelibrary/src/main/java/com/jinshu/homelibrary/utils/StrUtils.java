package com.jinshu.homelibrary.utils;

/**
 * Create on 2019/9/10 17:33 by bll
 */


public class StrUtils {

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input) || "null".equals(input) || "Null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String target) {
        if (target != null && target.length() > 0 && !target.equalsIgnoreCase("null") && !target.equals("")) {
            return true;
        }

        return false;
    }
}
