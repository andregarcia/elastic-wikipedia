package br.curso.elastic.parser;


import br.curso.elastic.model.xwiki.PageType;
import org.springframework.beans.factory.annotation.Value;
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
public class WikipediaXmlProcessor {

    @Value("${wikipedia.sample-xml-file}")
    private String sampleWikipediaXmlFile;

    @Value("${wikipedia.xml-file}")
    private String wikipediaXmlFile;

    @Value("${wikipedia.use-sample}")
    private Boolean useSample;

    private final QName qName = new QName("http://www.mediawiki.org/xml/export-0.10/", "page");

    public int parseAndProcessPages(ParsedElementProcessor<PageType> pageElementProcessor){
        int numPagesIndexed = 0;
        try {
            Unmarshaller um = getUnmarshaller();
            XMLEventReader reader = getXmlEventReader();
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
        } catch (JAXBException | XMLStreamException e) {
            e.printStackTrace();
        }

        return numPagesIndexed;
    }

    private XMLEventReader getXmlEventReader(){
        try {
            XMLInputFactory f = XMLInputFactory.newInstance();
            return f.createXMLEventReader(new FileInputStream(getWikipediaFilePath()));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Unmarshaller getUnmarshaller(){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance("br.curso.elastic.model.xwiki");
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getWikipediaFilePath(){
        if(useSample==Boolean.TRUE){
            return sampleWikipediaXmlFile;
        }
        else {
            return wikipediaXmlFile;
        }

    }

}
