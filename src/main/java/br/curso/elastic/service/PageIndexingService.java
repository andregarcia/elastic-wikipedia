package br.curso.elastic.service;


import br.curso.elastic.parser.PageElementProcessor;
import br.curso.elastic.parser.WikipediaXmlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageIndexingService {

    @Autowired
    private WikipediaXmlProcessor xmlParser;

    @Autowired
    private PageElementProcessor pageElementProcessor;

    public int indexAll(){
        return xmlParser.parseAndProcessPages(pageElementProcessor);
    }

}
