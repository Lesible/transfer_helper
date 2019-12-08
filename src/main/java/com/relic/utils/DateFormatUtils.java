package com.relic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Relic
 * @desc 时间格式化工具
 * @date 2019-11-19 19:27
 */
public class DateFormatUtils {

    private static final ThreadLocal<Map<String, SimpleDateFormat>> LOCAL = new ThreadLocal<Map<String, SimpleDateFormat>>();

    public static String format(Date date, String pattern) {
        Map<String, SimpleDateFormat> sdfMap = LOCAL.get();
        if (sdfMap == null) {
            sdfMap = new HashMap<String, SimpleDateFormat>(4);
            LOCAL.set(sdfMap);
        }
        SimpleDateFormat simpleDateFormat = sdfMap.get(pattern);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern);
            sdfMap.put(pattern, simpleDateFormat);
        }
        return simpleDateFormat.format(date);
    }

}
