package com.bittech.musicmanager.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author: Eve
 * @Date: 2019/2/19 11:27
 * @Version 1.0
 */

/**
 * 用户信息类
 */
@Data
@Alias("User")
public class User {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;
}
