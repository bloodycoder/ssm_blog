package com.shiyanlou.mappers;

import com.shiyanlou.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    // 添加用户
    @Insert("INSERT INTO `user` VALUES (NULL, #{username}, #{password}, #{registerTime})")
    void addUser(@Param("username") String username, @Param("password") String password,
                 @Param("registerTime") Long registerTime);

    // 通过用户名寻找用户，用于判断该用户名是否已经被注册
    @Select("SELECT * FROM `user` WHERE `username`=#{username}")
    UserEntity findUserByUsername(@Param("username") String username);

    // 通过用户名和密码寻找用户，用于用户的登入。
    @Select("SELECT * FROM `user` WHERE `username`=#{username} AND `password`=#{password}")
    UserEntity findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}