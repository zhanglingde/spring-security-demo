package com.ling.formlogin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    // 开启权限配置
                // 所有请求都需要认证后才可访问
                .anyRequest().authenticated()
                .and()
                // 表单登录配置
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureUrl("/login.html")
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                // 禁用 csrf 防御
                .csrf().disable();
    }

    SavedRequestAwareAuthenticationSuccessHandler successHandler(){
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/index");
        handler.setTargetUrlParameter("target");
        return handler;
    }
}
