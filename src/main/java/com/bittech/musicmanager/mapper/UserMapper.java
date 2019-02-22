package com.bittech.musicmanager.mapper;

import com.bittech.musicmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user 用户对象
     */
    void addUser(User user);

    /**
     * 查找用户
     */
    List<User> findAllUser();

    /**
     * 通过邮箱进行查找对象
     * @param email 邮箱
     * @return user
     */
    User findUserByEmail(String email);

    /**
     * 删除用户
     * @param id 用户id
     */
    void deleteUser(Integer id);

    /**
     * 更新用户
     * @param user 用户对象
     */
    void updateUser(User user);
    
}
