package com.Ferret56.FlowerShopEE.be.Jms;

import com.Ferret56.FlowerShopEE.be.Marshalling.XmlConverter;
import com.Ferret56.FlowerShopEE.be.access.UserDaoService.UserDaoService;
import com.Ferret56.FlowerShopEE.be.access.UserDaoService.UserDaoServiceImpl;
import com.Ferret56.FlowerShopEE.be.dao.UserDao.UserDaoImpl;
import com.Ferret56.FlowerShopEE.fe.dto.DiscountDTO;
import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JmsReceiver implements ServletContextListener {
    private Thread listenerThread = null;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;


    private FileManager fileManager = new FileManager();
    private UserDaoService userService = new UserDaoServiceImpl(new UserDaoImpl());

    public void contextInitialized(final ServletContextEvent sce){
        listenerThread = new Thread(() -> {
            try {
                connectionFactory = new ActiveMQConnectionFactory();
                connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                destination = session.createQueue("Out_Queue");
                consumer = session.createConsumer(destination);
            }catch (JMSException e){
                e.printStackTrace();
            }
            try {
                while (!Thread.interrupted()) {
                    Message message = consumer.receive(1000);
                    if(message instanceof TextMessage){
                        fileManager.writeStringToXml(((TextMessage) message).getText(),
                                "C:\\Ferret56\\IdeaProjects\\FlowerShopEE\\xml\\discount.xml");
                        DiscountDTO discount = new XmlConverter().getDiscountDTO("C:\\Ferret56\\IdeaProjects\\FlowerShopEE\\xml\\discount.xml");
                        try {
                            userService.updateUserDiscount(discount.getUserId(), discount.getDiscount());
                        } catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        System.out.println("Success");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listenerThread.start();
    }

    public void contextDestroyed(ServletContextEvent sce){
        if(connection != null){
            try {
                connection.close();
                System.out.println("GoodBye");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
