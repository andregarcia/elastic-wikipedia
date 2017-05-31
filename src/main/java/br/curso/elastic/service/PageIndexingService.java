package br.curso.elastic.service;


import br.curso.elastic.parser.PageElementProcessor;
import br.curso.elastic.parser.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageIndexingService {

    @Autowired
    private XmlParser xmlParser;

    @Autowired
    private PageElementProcessor pageElementProcessor;

    public int indexAll(){
        return xmlParser.parseAndProcessPages(pageElementProcessor);
    }

}
