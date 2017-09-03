package br.curso.elastic.service;


import br.curso.elastic.processor.WikipediaXmlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageIndexingService {

    @Autowired
    private WikipediaXmlProcessor wikipediaXmlProcessor;

    public int indexAll(){
        return wikipediaXmlProcessor.parseAndProcessAllPages();
    }

    public boolean indexById(String id){
        return wikipediaXmlProcessor.parseAndProcessPageById(id);
    }

    public int indexByInitial(Character initial){
        return wikipediaXmlProcessor.parseAndProcessPageByInitial(initial);
    }

}
