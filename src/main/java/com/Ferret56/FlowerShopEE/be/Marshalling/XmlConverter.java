package com.Ferret56.FlowerShopEE.be.Marshalling;

import com.Ferret56.FlowerShopEE.be.entity.User.User;
import com.Ferret56.FlowerShopEE.fe.dto.DiscountDTO;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class XmlConverter {

    private JAXBContext jaxbContext;
    private Marshaller marshaller;

    public void convertUser(User user, String path) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(User.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                                                   Boolean.TRUE);
        marshaller.marshal(user, new File(path));
    }

    public User getUser(String path) throws JAXBException, FileNotFoundException {
        jaxbContext = JAXBContext.newInstance(User.class);
        return (User) jaxbContext.createUnmarshaller().unmarshal(new FileReader(path));

    }

    public void convertStringToXml(String line) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(line)));
        }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }


    public void convertDiscount(DiscountDTO discount, String path) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(DiscountDTO.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);
        marshaller.marshal(discount, new File(path));
    }

    public DiscountDTO getDiscountDTO(final String path) throws JAXBException,
                                                                FileNotFoundException {
        jaxbContext = JAXBContext.newInstance(DiscountDTO.class);
        return (DiscountDTO) jaxbContext
                .createUnmarshaller()
                .unmarshal(new FileReader(path));
    }
}
