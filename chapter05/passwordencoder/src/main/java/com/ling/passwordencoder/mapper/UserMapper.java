package com.ling.passwordencoder.mapper;

import com.ling.passwordencoder.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    Integer updatePassword(@Param("username") String username,
                           @Param("newPassword") String newPassword);
}
