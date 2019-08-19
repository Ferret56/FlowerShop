package com.Ferret56.FlowerShopEE.be.access.UserDaoService;

import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.dao.UserDao.UserDao;
import com.Ferret56.FlowerShopEE.be.dao.UserDao.UserDaoImpl;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoServiceImpl implements UserDaoService {
    @Autowired
    private UserDao dao;

    public UserDaoServiceImpl(UserDaoImpl userDao) {
        this.dao = userDao;
    }


    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException {
        User user = dao.findUserById(id);
        if(user == null)
            throw new UserNotFoundException("User with id{" + id + "} is not found");
        return user;
    }

    @Override
    public User findUserByName(String username) throws UserNotFoundException {
        User user = dao.findUserByName(username);
        if(user == null)
            throw  new UserNotFoundException("User with username" + "{" + username + "} is not found");
        return user;
    }

    @Override
    public List<User> getAll() {
       return dao.getAll();
    }

    @Override
    public void updateUser(User user) {
            dao.updateUser(user);
    }

    @Override
    public void updateUserDiscount(Long id, int discount) throws UserNotFoundException {
          User user = dao.findUserById(id);
          if(user == null)
              throw new UserNotFoundException("User with id{" + id + "} is not found");
          user.setDiscount(discount);
          dao.updateUser(user);
    }

}
