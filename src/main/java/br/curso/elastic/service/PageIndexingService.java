package br.curso.elastic.service;


import br.curso.elastic.processor.xml.WikipediaXmlProcessor;
import br.curso.elastic.repository.ElasticsearchIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.curso.elastic.configuration.IndexConstants.PAGES_INDEX;

@Service
public class PageIndexingService {

    @Autowired
    private WikipediaXmlProcessor wikipediaXmlProcessor;

    @Autowired
    private ElasticsearchIndexRepository pageIndexingRepository;

    public int indexAll(){
        return wikipediaXmlProcessor.parseAndProcessAllPages();
    }

    public boolean indexById(String id){
        return wikipediaXmlProcessor.parseAndProcessPageById(id);
    }

    public int indexByInitial(Character initial){
        return wikipediaXmlProcessor.parseAndProcessPageByInitial(initial);
    }

    public boolean deletePagesIndex(){
        return pageIndexingRepository
                .deleteIndex(PAGES_INDEX)
                .isAcknowledged();
    }

}
