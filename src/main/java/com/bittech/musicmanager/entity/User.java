package com.bittech.musicmanager.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author: Eve
 * @Date: 2019/2/19 11:27
 * @Version 1.0
 */
@Data
@Alias("User")
public class User {
    private Integer user_id;
    private String user_name;
    private String user_password;
}
