package com.bittech.musicmanager.service.impl;

import com.bittech.musicmanager.entity.User;
import com.bittech.musicmanager.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    IUserService ius;
    @Test
    public void userRegister() {
        User user = new User();
        user.setName("张三");
        user.setEmail("123456789");
        user.setPassword("qweasdzxc");
        ius.userRegister(user);
    }

    @Test
    public void userLogin() {
        System.out.println("**************************");
        System.out.println(ius.userLogin("123456789","qweasdzxc"));
        System.out.println("**************************");
    }
}