package com.ling.formlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyLoginController {

    @RequestMapping("/mylogin.html")
    public String mylogin(){
        return "mylogin";
    }
}
