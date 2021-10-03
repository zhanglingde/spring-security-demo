package com.ling.based_on_url.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangling
 * @date 2021/10/2 10:36 上午
 */
// 自定义权限表达式
@Component
public class PermissionExpression {

    // 检查参数 userId，同时传入 authentication 对象，通过该对象判断当前用户是否登录。返回 true，表示校验通过
    public boolean checkId(Authentication authentication, Integer userId) {
        if (authentication.isAuthenticated()) {
            return userId % 2 == 0;
        }
        return false;
    }

    // 校验请求参数
    public boolean check(HttpServletRequest request) {
        return "ling".equals(request.getParameter("username"));
    }
}
