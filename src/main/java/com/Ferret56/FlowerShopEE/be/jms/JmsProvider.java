package com.Ferret56.FlowerShopEE.be.jms;

import com.Ferret56.FlowerShopEE.be.access.user.UserDaoService;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.marshalling.XmlConverter;
import com.Ferret56.FlowerShopEE.fe.dto.DiscountDTO;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

@Component
public class JmsProvider {
    private static final Logger LOG = LoggerFactory.getLogger(JmsProvider.class);

    private final String IN_QUEUE = "IN_QUEUE";
    private final String OUT_QUEUE = "OUT_QUEUE";

    private final String FILE_PATH = "C:\\Ferret56\\IdeaProjects\\FlowerShopEE\\xml\\Discount.xml";

    @Autowired
    private UserDaoService userDaoService;

    private FileManager fileManager = new FileManager();
    private ConnectionFactory connFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
    private Connection connection = connFactory.createConnection();
    private ActiveMQSession session = (ActiveMQSession)connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


    public void sendByteMessage(File file) throws JMSException, IOException {
        connection.start();
        Queue queue = session.createQueue(IN_QUEUE);
        javax.jms.MessageProducer producer = session.createProducer(queue);
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.setStringProperty("fileName", file.getName());
        bytesMessage.writeBytes(fileManager.readFileAsBytes(file));
        producer.send(bytesMessage);
    }

    public JmsProvider() throws JMSException {
        connection.start();
        Destination destination = session.createQueue(OUT_QUEUE);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage) {
                        System.out.println("IT WORKS!");
                    try {
                        fileManager.writeStringToXml(((TextMessage) message).getText(),FILE_PATH );
                        DiscountDTO discountDTO = new XmlConverter().getDiscountDTO(FILE_PATH);
                        userDaoService.updateUserDiscount(discountDTO.getUserId(),discountDTO.getDiscount());

                    } catch (IOException | JMSException | JAXBException e) {
                       LOG.error("Error " + e.getMessage());
                    } catch (UserNotFoundException e) {
                        LOG.error("User found error: " + e.getMessage());
                    }

                }
            }
        });
    }
}
