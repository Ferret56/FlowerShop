package com.Ferret56.FlowerShopEE.be.business.user;

import com.Ferret56.FlowerShopEE.be.jms.JmsProvider;
import com.Ferret56.FlowerShopEE.be.marshalling.XmlConverter;
import com.Ferret56.FlowerShopEE.be.access.user.UserDaoService;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserLoginException;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserRegisterException;
import com.Ferret56.FlowerShopEE.be.entity.user.User;
import com.Ferret56.FlowerShopEE.be.entity.user.UserRoleEnum;
import com.Ferret56.FlowerShopEE.be.validation.UserValidator;
import com.Ferret56.FlowerShopEE.be.validation.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    private final BigDecimal INIT_MONEY = BigDecimal.valueOf(2000);
    private final String FILE_DESTINATION = "C:\\Ferret56\\IdeaProjects\\FlowerShopEE\\xml\\";

    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JmsProvider jmsProvider;


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
            user.setRole(UserRoleEnum.USER);
            userDaoService.addUser(user);
            try {
                xmlConverter.convertUser(user,FILE_DESTINATION + "user" + user.getId() + ".xml");
               // jmsProducer.sendFileAsBytesMessage(new File((FILE_DESTINATION + "user" + user.getId() + ".xml")));
                jmsProvider.sendByteMessage(new File((FILE_DESTINATION + "user" + user.getId() + ".xml")));

            } catch (JAXBException | IOException | JMSException e) {
                LOG.error("Jms runtime error :   "  + e.getMessage());
            }
            return user;
        } catch (ValidatorException e) {
            throw new UserRegisterException(e.getMessage());
        }

    }

}
