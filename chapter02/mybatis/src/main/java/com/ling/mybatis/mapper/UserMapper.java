package com.ling.mybatis.mapper;

import com.ling.mybatis.model.Role;
import com.ling.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Role> getRoleByUserId(Integer userId);

    User loadUserByUsername(String username);

}
