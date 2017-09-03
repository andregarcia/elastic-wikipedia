package br.curso.elastic.processor;


import br.curso.elastic.model.local.WikipediaIdRowList;
import br.curso.elastic.model.xwiki.PageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


@Component
public class WikipediaXmlProcessor {

    @Value("${wikipedia.sample-xml-file}")
    private String sampleWikipediaXmlFile;

    @Value("${wikipedia.xml-file}")
    private String wikipediaXmlFile;

    @Value("${wikipedia.use-sample}")
    private Boolean useSample;

    @Autowired
    private PageElementProcessor pageElementProcessor;

    @Autowired
    private WikipediaIdFileProcessor wikipediaIdFileProcessor;

    private final QName qName = new QName("http://www.mediawiki.org/xml/export-0.10/", "page");

    //TODO: test
    public int parseAndProcessAllPages(){
        int numPagesIndexed = 0;
        try {
            Unmarshaller um = XmlProcessorUtil.getUnmarshaller();
            XMLEventReader reader = XmlProcessorUtil.getXmlEventReader(getWikipediaFilePath());
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
            reader.close();
        } catch (JAXBException | XMLStreamException e) {
            e.printStackTrace();
        }
        return numPagesIndexed;
    }


    //TODO: test
    public boolean parseAndProcessPageById(String id){
        Integer index = wikipediaIdFileProcessor.getPageIndexById(id);
        if(index == null) return false;
        boolean indexed = false;
        int count = 0;
        try {
            Unmarshaller um = XmlProcessorUtil.getUnmarshaller();
            XMLEventReader reader = XmlProcessorUtil.getXmlEventReader(getWikipediaFilePath());
            XMLEvent e = null;
            while ((e = reader.peek()) != null) {
                if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                    if(count == index) {
                        PageType page = um.unmarshal(reader, PageType.class).getValue();
                        pageElementProcessor.processElement(page);
                        indexed = true;
                        break;
                    }
                    count += 1;
                }
                else{
                    reader.next();
                }
            }
            reader.close();
        } catch (JAXBException | XMLStreamException e) {
            e.printStackTrace();
        }
        return indexed;
    }

    //TODO: test
    public int parseAndProcessPageByInitial(Character initial){
        WikipediaIdRowList rowsToIndex = wikipediaIdFileProcessor.getIndexRangeByInitial(initial.toString());
        if(rowsToIndex == null) return 0;
        int indexedCount = 0;
        try {
            Unmarshaller um = XmlProcessorUtil.getUnmarshaller();
            XMLEventReader reader = XmlProcessorUtil.getXmlEventReader(getWikipediaFilePath());
            XMLEvent e = null;
            while ((e = reader.peek()) != null) {
                if (e.isStartElement() && ((StartElement) e).getName().equals(qName)) {
                    PageType page = um.unmarshal(reader, PageType.class).getValue();
                    if(rowsToIndex.contains(page.getId().toString())) {
                        pageElementProcessor.processElement(page);
                        indexedCount += 1;
                    }
                }
                else{
                    reader.next();
                }
            }
            reader.close();
        } catch (JAXBException | XMLStreamException e) {
            e.printStackTrace();
        }
        return indexedCount;
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
