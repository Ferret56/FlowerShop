package com.Ferret56.FlowerShopEE.be.Jms;



import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import javax.jms.*;
import java.io.File;
import java.io.IOException;

public class JmsProducer {
    private FileManager fileManager = new FileManager();
    public void sendFileAsBytesMessage(File file) throws JMSException, IOException {
        ConnectionFactory connFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = connFactory.createConnection();
        ActiveMQSession session = (ActiveMQSession)connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("IN_QUEUE");
        javax.jms.MessageProducer producer = session.createProducer(queue);
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.setStringProperty("fileName", file.getName());
        bytesMessage.writeBytes(fileManager.readFileAsBytes(file));
        producer.send(bytesMessage);
    }
    public JmsProducer(){}


}
