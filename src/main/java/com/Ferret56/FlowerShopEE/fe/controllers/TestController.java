package com.Ferret56.FlowerShopEE.fe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/login")
    public String getLoginPage(){
        return "Login";
    }
}
