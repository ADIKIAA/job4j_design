package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class SerializationJAXB {

    public static void main(String[] args) throws JAXBException {

        Characteristic character = new Characteristic(
                true,
                345,
                new char[] {'a', 'b', 'c'},
                new Contact(47775, "+4954797"),
                "this is line");

        JAXBContext context = JAXBContext.newInstance(Characteristic.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(character, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Characteristic result = (Characteristic) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
