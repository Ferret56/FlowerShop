package com.Ferret56.FlowerShopEE.be.access.UserDaoService;

import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.entity.User.User;

import java.util.List;

public interface UserDaoService {
    void addUser(User user);
    User findUserById(Long id) throws UserNotFoundException;
    User findUserByName(String username) throws UserNotFoundException;
    List<User> getAll();
    void updateUser(User user);
    void updateUserDiscount(Long id, int discount) throws UserNotFoundException;
}
