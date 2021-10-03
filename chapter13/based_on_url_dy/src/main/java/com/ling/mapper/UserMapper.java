package com.ling.mapper;

import com.ling.entry.Role;
import com.ling.entry.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangling
 * @date 2021/10/3 9:44 上午
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> selectUserRoleByUserId(Integer userId);
}
