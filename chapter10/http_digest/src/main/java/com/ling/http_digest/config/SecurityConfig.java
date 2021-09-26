package com.ling.http_digest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("ling")
                // 使用编码后的密码
                .password("09274beb93d9ce7ad272539209f78039")
                .roles("admin")
                .build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(digestAuthenticationEntryPoint())
                .and()
                // 将 DigestAuthenticationFilter 添加到 Security 过滤器链中
                .addFilter(digestAuthenticationFilter());
    }

    // DigestAuthenticationEntryPoint 实例相当与 HTTP 基本认证中的 BasicAuthenticationEntryPoint
    // 当用户发起一个没有认证的请求时，由该实例进行处理
    DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        // 随机数的有效期
        entryPoint.setNonceValiditySeconds(3600);
        entryPoint.setRealmName("myrealm");
        entryPoint.setKey("ling");
        return entryPoint;
    }

    // DigestAuthenticationFilter 实例添加到过滤器链中，作用类似于 HTTP 基本认证中 BasicAuthenticationFilter 的作用
    DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
        filter.setUserDetailsService(userDetailsServiceBean());
        // 对密码进行编码
        filter.setPasswordAlreadyEncoded(true);
        return filter;
    }
}
