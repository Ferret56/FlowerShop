package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.access.user.UserDaoService;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestfulController {
    @Autowired
    private UserDaoService userDaoService;

    @RequestMapping("/find/{username}")
    public String isUserExist(@PathVariable("username")String username){
        try {
            userDaoService.findUserByName(username);
        } catch (UserNotFoundException e) {
            return Boolean.FALSE.toString();
        }
       return Boolean.TRUE.toString();
    }

}
