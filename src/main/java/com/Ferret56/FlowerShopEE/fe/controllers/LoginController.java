package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.Mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.UserBusinessService;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import com.Ferret56.FlowerShopEE.be.entity.User.UserRoles;
import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserBusinessService userBusinessService;
    private Mapper mapper = new Mapper();


    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "Login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userDTO") UserDTO formUserDTO,
                                                   HttpSession session,
                                 RedirectAttributes redirectAttributes){

         User user = mapper.map(formUserDTO, User.class);
        try {
            User currentUser = userBusinessService.login(user);
            session.setAttribute("currentUser", mapper.map(currentUser, UserDTO.class));
            if(currentUser.getRole().equals(UserRoles.ADMIN))
                return "redirect:/admin";

            return "redirect:/userPage";
        }
        catch (UserLoginException e) {
           redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
           return "redirect:/login";
        }
    }
 }

