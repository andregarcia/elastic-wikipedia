package br.curso.elastic.parser;


import br.curso.elastic.model.PageType;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Component
public class XmlParser {

    private static final String wikipediaXmlFile =
            "/home/andregarcia/Downloads/datasets/ptwiki/ptwiki-20170501-pages-articles-multistream/ptwiki-20170501-pages-articles-multistream.sample.xml";

    private final QName qName = new QName("http://www.mediawiki.org/xml/export-0.10/", "page");

    public int parseAndProcessPages(ParsedElementProcessor<PageType> pageElementProcessor){
        int numPagesIndexed = 0;
        try {
            XMLInputFactory f = XMLInputFactory.newInstance();
            JAXBContext jaxbContext = JAXBContext.newInstance("br.curso.elastic.model");
            Unmarshaller um = jaxbContext.createUnmarshaller();
            XMLEventReader reader = f.createXMLEventReader(new FileInputStream(wikipediaXmlFile));

            XMLEvent e = null;
            while ((e = reader.peek()) != null) {
                if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                    PageType page = um.unmarshal(reader, PageType.class).getValue();
                    pageElementProcessor.processElement(page);
                    numPagesIndexed += 1;
                }
                else{
                    reader.next();
                }
            }
        } catch (JAXBException | XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return numPagesIndexed;
    }

}
