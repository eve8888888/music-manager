package com.bittech.musicmanager.service;

import com.bittech.musicmanager.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 用户注册
     * @param user 用户对象
     */
    boolean userRegister(User user);

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return boolean
     */
    boolean userLogin(String email, String password);

    List<User> findAllUser();

    void updateUser(User user);

    void deleteUser(String  ids);

    void addUser(User user);

    User findUserByEmail(String email);

    boolean checkUser(User user);
}
