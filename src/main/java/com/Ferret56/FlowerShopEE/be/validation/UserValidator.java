package com.Ferret56.FlowerShopEE.be.validation;

import com.Ferret56.FlowerShopEE.be.access.UserDaoService.UserDaoService;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class UserValidator {
    @Autowired
    private UserDaoService userDaoService;
    public void validate(User user, String confirm_password) throws ValidatorException {
        if(user.getUsername().equals("")
        || user.getPhone().equals("")
        || user.getEmail().equals("")
        || user.getPassword().equals("")){
            throw new ValidatorException("All fields must be filled!");
        }
        ArrayList<User> users = (ArrayList<User>) userDaoService.getAll();
        for(User u: users)
            if(u.getUsername().equals(user.getUsername()))
                throw new ValidatorException("This username is already exist!");
       if(!user.getPhone().matches("(^(\\+7|7|8)+([0-9]){10})$"))
           throw new ValidatorException("Phone incorrect!");
       if(!user.getEmail().matches("[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
           throw new ValidatorException("Email incorrect!");
       if(!user.getPassword().equals(confirm_password))
           throw new ValidatorException("Password and confirm password do not match!");
    }

}
