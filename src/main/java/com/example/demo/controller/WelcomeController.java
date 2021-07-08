package com.example.demo.controller;

import com.example.demo.advice.GlobalResponseAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController is @Controller and @ResponseBody
@RestController
public class WelcomeController {

    @GlobalResponseAdvice
    @RequestMapping("/welcome")
    public String index() {
        return "welcome to docker";
    }
}
