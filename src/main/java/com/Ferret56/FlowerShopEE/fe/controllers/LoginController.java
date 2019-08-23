package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.user.UserBusinessService;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.entity.user.User;
import com.Ferret56.FlowerShopEE.be.entity.user.UserRoleEnum;
import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);


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
            if(currentUser.getRole().equals(UserRoleEnum.ADMIN))
                return "redirect:/admin";

            return "redirect:/userPage";
        }
        catch (UserLoginException e) {
           LOG.error("Login error!");
           redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
           return "redirect:/login";
        }
    }
 }

