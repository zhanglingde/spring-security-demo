package com.ling.config;

import com.ling.entry.Menu;
import com.ling.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.logging.Filter;
import java.util.stream.Stream;

/**
 * @author zhangling
 * @date 2021/10/3 10:20 上午
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        List<Menu> menus = menuService.selectAllMenu();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestURI)) {
                String[] roles = menu.getRoles().stream()
                        .map(r -> r.getName()).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    // 是否支持数据库权限配置，返回 true 支持
    @Override
    public boolean supports(Class<?> clazz) {
        // 数据库中未定义的接口都没有权限访问
        return FilterInvocation.class.isAssignableFrom(clazz);
//        return true;
    }
}
