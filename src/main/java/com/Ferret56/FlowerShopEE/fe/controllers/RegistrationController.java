package com.Ferret56.FlowerShopEE.fe.controllers;


import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "Registration";
    }
}
