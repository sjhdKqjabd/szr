package com.pandahis.dashboard.util.string;

import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类
 *
 * @author 源码乐园
 */
public class StrUtils {

    public static String maxLength(CharSequence str, int maxLength) {
        return StrUtil.maxLength(str, maxLength - 3); // -3 的原因，是该方法会补充 ... 恰好
    }

}
