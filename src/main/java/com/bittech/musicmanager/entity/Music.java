package com.bittech.musicmanager.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author: Eve
 * @Date: 2019/2/19 11:08
 * @Version 1.0
 */
@Data
@Alias("Music")
public class Music {
    private Integer id;
    private String name;
    private String singer;
    private String time;
    private String path;
}
