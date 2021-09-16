package com.ling.formlogin.controller;

import com.ling.formlogin.config.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

// 登录用户信息获取
@RestController
public class UserController {

    @GetMapping("/user")
    public void userInfo() {
        // 获取身份认证后的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        System.out.println("name = " + name);
        // 没有给用户配置角色，默认用户角色为空数组
        System.out.println("authorities = " + authorities);
    }

    // 子线程获取登录用户信息
    @GetMapping("/user2")
    public void userInfo2() {
        // 获取身份认证后的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        System.out.println("name = " + name);
        // 没有给用户配置角色，默认用户角色为空数组
        System.out.println("authorities = " + authorities);

        new Thread(() -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                System.out.println("获取用户信息失败...");
                return;
            }
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "auth.getName() = " + auth.getName());
            System.out.println(threadName + "auth.getauthorities() = " + auth.getAuthorities());
        }).start();
    }

    // 请求对象获取用户数据
    @GetMapping("/authentication")
    public void authentication(Authentication authentication) {
        System.out.println("authentication = " + authentication);
    }

    @GetMapping("/principal")
    public void principal(Principal principal) {
        System.out.println("principal = " + principal);
    }
}
