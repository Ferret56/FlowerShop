package com.Ferret56.FlowerShopEE.be.dao.UserDao;

import com.Ferret56.FlowerShopEE.be.entity.User.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User findUserById(Long id);
    User findUserByName(String username);
    List<User> getAll();
    void updateUser(User user);
}
