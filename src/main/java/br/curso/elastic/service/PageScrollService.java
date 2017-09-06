package br.curso.elastic.service;

import br.curso.elastic.repository.ElasticsearchScrollRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.curso.elastic.configuration.IndexConstants.PAGES_INDEX;

@Service
public class PageScrollService {

    @Autowired
    private ElasticsearchScrollRepository elasticsearchScrollRepository;

    public SearchResponse scrollAllPages(){
        return elasticsearchScrollRepository.scroll(PAGES_INDEX, QueryBuilders.matchAllQuery());
    }

    public SearchResponse scrollAllPages(String scrollId){
        return elasticsearchScrollRepository.scroll(scrollId);
    }

}
