package com.Ferret56.FlowerShopEE.be.business.UserBusinessService;

import com.Ferret56.FlowerShopEE.be.Jms.JmsProducer;
import com.Ferret56.FlowerShopEE.be.Marshalling.XmlConverter;
import com.Ferret56.FlowerShopEE.be.access.UserDaoService.UserDaoService;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.business.UserBusinessService.exp.UserRegisterException;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import com.Ferret56.FlowerShopEE.be.entity.User.UserRoles;
import com.Ferret56.FlowerShopEE.be.validation.UserValidator;
import com.Ferret56.FlowerShopEE.be.validation.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.jms.JMSException;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private final BigDecimal INIT_MONEY = BigDecimal.valueOf(2000);
    private final String FILE_DESTINATION = "C:\\Ferret56\\IdeaProjects\\FlowerShopEE\\xml\\";

    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private UserValidator userValidator;
    private JmsProducer jmsProducer = new JmsProducer();

    private XmlConverter xmlConverter = new XmlConverter();

    @Override
    public User login(User user) throws UserLoginException {
        try {
            User currentUser = userDaoService.findUserByName(user.getUsername());
            if(!currentUser.getPassword().equals(user.getPassword()))
                throw new UserLoginException("Wrong username or password!");
            return currentUser;
        } catch (UserNotFoundException e) {
            throw new UserLoginException("Wrong username or password!");
        }
    }

    @Override
    @Transactional
    public User register(User user, String confirmPassword) throws UserRegisterException {
        try {
            userValidator.validate(user, confirmPassword);
            user.setMoney(INIT_MONEY);
            user.setDiscount(0);
            user.setRole(UserRoles.USER);
            userDaoService.addUser(user);
            try {
                xmlConverter.convertUser(user,FILE_DESTINATION + "User" + user.getId() + ".xml");
                jmsProducer.sendFileAsBytesMessage(new File((FILE_DESTINATION + "User" + user.getId() + ".xml")));

            } catch (JAXBException | IOException | JMSException e) {
                e.printStackTrace();
            }
            return user;
        } catch (ValidatorException e) {
            throw new UserRegisterException(e.getMessage());
        }

    }

}
