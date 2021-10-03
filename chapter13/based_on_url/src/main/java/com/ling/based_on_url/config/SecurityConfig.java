package com.ling.based_on_url.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;

/**
 * @author zhangling
 * @date 2021/10/2 9:31 上午
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        // admin 继承 user
        roleHierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return roleHierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ling")
                .password("{noop}123")
                .roles("admin")
                .and()
                .withUser("zhang")
                .password("{noop}123")
                .roles("user")
                .and()
                .withUser("bai")
                .password("{noop}123")
                .authorities("READ_INFO");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(applicationContext))
                .getRegistry()
                .mvcMatchers("/admin/**").access("ROLE_admin")
                .mvcMatchers("/user/**").access("ROLE_user");
        http.formLogin()
                .and()
                .csrf().disable();

    }
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").access("hasAnyRole('user')")
//                .antMatchers("/hello/{userId}")
//                .access("@permissionExpression.checkId(authentication,#userId)")
//                .antMatchers("/hi")
//                .access("isAuthenticated() and @permissionExpression.check(request)")
//                // 用户必须具备 READ_INFO 权限才可以访问 /getinfo
//                .antMatchers("/getinfo").hasAuthority("READ_INFO")
//                // 剩余请求只要认证后的用户就可以访问,可以通过 access 方法设置权限表达式
//                .anyRequest().access("isAuthenticated()")
//                .and()
//                .formLogin()
//                .and()
//                .csrf().disable();
//
//    }
}
