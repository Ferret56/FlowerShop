package com.Ferret56.FlowerShopEE.be.business.UserBusinessService;

import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserRegisterException;
import com.Ferret56.FlowerShopEE.be.entity.User.User;

public interface UserBusinessService {
    User login(User user) throws UserLoginException;
    User register(User user, String confirm_password) throws UserRegisterException;
}
