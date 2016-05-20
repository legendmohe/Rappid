package com.legendmohe.rappid.util;

import java.security.MessageDigest;

/**
 * Created by legendmohe on 16/5/20.
 */
public class StringUtil {

    public static boolean isEmpty(String target) {
        return target == null || target.length() == 0;
    }

    public static boolean isNotEmpty(String target) {
        return target != null && target.length() != 0;
    }

    public static boolean isAllNotEmpty(String... targets) {
        for (String target :
                targets) {
            if (isEmpty(target)) {
                return false;
            }
        }
        return true;
    }

    public static void appendIfNotEmpty(StringBuilder sb, String target, String sep) {
        if (sb == null)
            return;
        if (isNotEmpty(target)) {
            sb.append(target);
            if (isNotEmpty(sep)) {
                sb.append(sep);
            }
        }
    }

    public static void appendIfNotEmpty(StringBuilder sb, String target) {
        appendIfNotEmpty(sb, target, null);
    }

    public static String joinStrings(String sep, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String item :
                strings) {
            if (item != null && item.length() != 0) {
                sb.append(item);
                sb.append(sep);
            }
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - sep.length(), sb.length() - 1);
        }
        return sb.toString();
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
