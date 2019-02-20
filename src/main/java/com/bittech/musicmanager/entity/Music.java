package com.bittech.musicmanager.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author: Eve
 * @Date: 2019/2/19 11:08
 * @Version 1.0
 */

/**
 * 音乐信息类
 */
@Data
@Alias("Music")
public class Music {

    /**
     * 音乐id
     */
    private Integer id;

    /**
     * 音乐名称
     */
    private String name;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 音乐时长
     */
    private String time;

    /**
     * 音乐路径
     * 实际所存放的是一个"<a>"标签
     */
    private String path;
}
