package com.pandahis.dashboard.common.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtils {
    public static String getRandomNumString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomNumStringLength10() { // length表示生成字符串的长度
        String base = "0123456789";
        int length=10;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static Integer getRandomNumIntetger(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return  Integer.parseInt(sb.toString());
    }
    public static String get32UUID(){
        UUID uuid=UUID.randomUUID();
        String s = uuid.toString();
        String replace = s.replace("-", "");
        return replace;
    }

    public static String getRandomNumStringLength5() {
        String base = "0123456789";
        int length=5;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
