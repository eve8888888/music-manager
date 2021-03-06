package com.bittech.musicmanager.service.impl;

import com.bittech.musicmanager.entity.User;
import com.bittech.musicmanager.mapper.UserMapper;
import com.bittech.musicmanager.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Eve
 * @Date: 2019/2/20 9:36
 * @Version 1.0
 */
@Service("usi")
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    /**
     * 用户注册
     * @param user 用户对象
     */
    @Override
    public boolean userRegister(User user) {
        if(checkUser(user)){
            userMapper.addUser(user);
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     * 通过邮箱和密码判断用户是否存在
     * @param email 邮箱
     * @param password 密码
     * @return boolean
     */
    @Override
    public boolean userLogin(String email,String password) {

        User user = userMapper.findUserByEmail(email);
        if(user == null){
            return false;
        }else {
            return user.getPassword().equals(password);
        }
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String ids) {
        String[] str = ids.split(",");
        for (String s:
                str) {
            Integer is = Integer.parseInt(s);
            userMapper.deleteUser(is);
        }
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public User findUserByEmail(String email){
        return userMapper.findUserByEmail(email);
    }


    public boolean checkUser(User user){
        String email = user.getEmail();
        return userMapper.findUserByEmail(email) == null;
    }
}
