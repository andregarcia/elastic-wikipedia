package br.curso.elastic.repository;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElasticsearchScrollRepository {

    @Autowired
    private Client elasticsearchClient;


    public SearchResponse scroll(String index, QueryBuilder query){
        return elasticsearchClient.prepareSearch(index)
                .addSort(SortBuilders.fieldSort("id"))
                .setScroll(new TimeValue(60000))
                .setQuery(query)
                .setSize(100)
                .get();
    }


    public SearchResponse scroll(String scrollId){
        return elasticsearchClient.prepareSearchScroll(scrollId)
                .setScroll(new TimeValue(60000))
                .get();
    }

}
