package com.bittech.musicmanager.service;

import com.bittech.musicmanager.entity.User;

public interface IUserService {

    /**
     * 用户注册业务
     * @param user 用户对象
     */
    boolean userRegister(User user);

    /**
     * 用户登录业务
     * @param email 邮箱
     * @param password 密码
     * @return boolean
     */
    boolean userLogin(String email, String password);
}
