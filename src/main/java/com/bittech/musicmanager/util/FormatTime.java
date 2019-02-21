package com.bittech.musicmanager.util;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @Author: Eve
 * @Date: 2019/2/19 10:30
 * @Version 1.0
 */


public class FormatTime {
    /**
     * 将秒转换为 时:分:秒 格式
     */
    public static String convertTime(Integer second){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return format.format(second*1000);
    }
}
