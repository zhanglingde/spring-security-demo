package com.ling.mybatis.service;

import com.ling.mybatis.mapper.UserMapper;
import com.ling.mybatis.model.Role;
import com.ling.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// 提供用户的服务
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    // 根据用户名查询数据库中用户是否存在，存在返回用户（同时返回用户所拥有的权限）
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<Role> roles = userMapper.getRoleByUserId(user.getUserId());
        user.setRoles(roles);
        return user;
    }
}
