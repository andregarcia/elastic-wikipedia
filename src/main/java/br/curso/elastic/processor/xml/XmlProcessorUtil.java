package br.curso.elastic.processor.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class XmlProcessorUtil {


    public static XMLEventReader getXmlEventReader(String filePath){
        try {
            XMLInputFactory f = XMLInputFactory.newInstance();
            return f.createXMLEventReader(new FileInputStream(filePath));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Unmarshaller getUnmarshaller(){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance("br.curso.elastic.model.xwiki");
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


}
