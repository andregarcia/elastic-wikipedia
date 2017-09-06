package br.curso.elastic.service;

import br.curso.elastic.configuration.IndexConstants;
import br.curso.elastic.model.local.Page;
import br.curso.elastic.model.xwiki.PageType;
import br.curso.elastic.response.PageableResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PageSearchService {

    @Autowired
    private Client elasticsearchClient;

    @Autowired
    private ObjectMapper objectMapper;

    public Page getPageById(String id){
        GetResponse response = elasticsearchClient.prepareGet(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE, id)
                .execute().actionGet();
        Page page = null;
        try {
            page = objectMapper.readValue(response.getSourceAsBytes(), Page.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public PageableResult<Page> getPagesByIds(List<String> ids, int page, int size){
        ids = PagingService.applyPagination(ids, page, size);
        MultiGetResponse multiGetItemResponses = elasticsearchClient.prepareMultiGet()
                .add(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE, ids)
                .execute()
                .actionGet();
        List<Page> pages = Lists.newArrayList();
        multiGetItemResponses.forEach((item) -> {
            try {
                pages.add(objectMapper.readValue(item.getResponse().getSourceAsBytes(), Page.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new PageableResult<>(pages, PagingService.totalPages(ids, size));
    }


}
