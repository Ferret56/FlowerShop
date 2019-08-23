package com.Ferret56.FlowerShopEE.be.business.user;

import com.Ferret56.FlowerShopEE.be.business.user.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserRegisterException;
import com.Ferret56.FlowerShopEE.be.entity.user.User;

public interface UserBusinessService {
    User login(User user) throws UserLoginException;
    User register(User user, String confirmPassword) throws UserRegisterException;
}
