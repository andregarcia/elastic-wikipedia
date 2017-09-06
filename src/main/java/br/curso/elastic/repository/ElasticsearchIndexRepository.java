package br.curso.elastic.repository;


import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static br.curso.elastic.configuration.IndexConstants.PAGES_INDEX;


@Repository
public class ElasticsearchIndexRepository {


    @Autowired
    private Client elasticsearchClient;


    public DeleteIndexResponse deleteIndex(String indexName){
        return elasticsearchClient.admin().
                indices().
                prepareDelete(PAGES_INDEX).
                execute().
                actionGet();
    }
}
