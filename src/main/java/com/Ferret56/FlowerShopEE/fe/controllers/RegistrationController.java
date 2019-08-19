package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.Mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.UserBusinessService;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserRegisterException;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserBusinessService userBusinessService;

    private Mapper mapper = new Mapper();

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "Registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDTO")UserDTO formUserDTO,Model model){

        User user = mapper.map(formUserDTO, User.class);
        try {
            userBusinessService.register(user, formUserDTO.getConfirm_password());
        } catch (UserRegisterException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "Registration";
        }
        return "redirect:/login";
    }
}
