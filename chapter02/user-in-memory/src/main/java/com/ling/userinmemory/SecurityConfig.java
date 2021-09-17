package com.ling.userinmemory;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 自定义基于内存的用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 创建内存用户管理对象
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 密码 {noop} 前缀表示密码不加密
        manager.createUser(User.withUsername("ling")
                .password("{noop}123").roles("admin").build());
        manager.createUser(User.withUsername("zhang")
                .password("{noop}123").roles("user").build());
        auth.userDetailsService(manager);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
