package com.happycommunity.framework.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author Danny
 * @Title: StringUtil
 * @Description:
 * @Created on 2018-11-23 10:42:21
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean hasOneEmpty(String... strs) {
        if (strs.length < 1) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉"-"符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 按当前时间随机生成数字字符串+3位随机数
     *
     * @return
     */
    public static String getRandomTimeStr() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); //精确到毫秒
        String suffix = fmt.format(new Date());
        return suffix + getRandomNum(3);
    }

    /**
     * 生成随机数字和字母
     *
     * @param length 字符串长度
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 随机不重复的6-8位
     *
     * @return
     */
    public static int getRundomDiffNum() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++) {
            result = result * 10 + array[i];
        }
        return result;
    }

    /**
     * 生成随机数字字符串
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String code = "";
        Random rand = new Random();//生成随机数
        for (int a = 0; a < length; a++) {
            code += rand.nextInt(10);//生成6位验证码
        }
        return code;
    }

}
