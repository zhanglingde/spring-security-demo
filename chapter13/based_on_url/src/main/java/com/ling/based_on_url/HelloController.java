package com.ling.based_on_url;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangling
 * @date 2021/10/2 10:10 上午
 */
@RestController
public class HelloController {

    @GetMapping("/hello/{userId}")
    public String hello(@PathVariable Long userId) {
        return "hello" + userId;
    }

    @GetMapping("/hi")
    public String hello2User(String username) {
        return "hello" + username;
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user";
    }

    @GetMapping("/getinfo")
    public String getInfo() {
        return "getinfo";
    }


}
