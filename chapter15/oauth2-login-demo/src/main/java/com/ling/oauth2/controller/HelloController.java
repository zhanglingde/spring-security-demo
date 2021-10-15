package com.ling.oauth2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangling
 * @date 2021/10/15 10:54 上午
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public DefaultOAuth2User hello() {
        DefaultOAuth2User principal = (DefaultOAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
