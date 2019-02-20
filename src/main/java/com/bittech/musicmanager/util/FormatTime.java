package com.bittech.musicmanager.util;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

/*
 * @Author: Eve
 * @Date: 2019/2/19 10:30
 * @Version 1.0
 */

/**
 * 将秒转换为 时:分:秒 格式
 */
public class FormatTime {
    public static String convertTime(Integer second){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return format.format(second*1000);
    }
}
