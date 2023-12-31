package com.demo.movie.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 获取当前时间的字符串
     * @return 当前时间的字符串
     */
    public static String getCurrentDateStr() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
