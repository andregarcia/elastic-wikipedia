package br.curso.elastic.service;

import br.curso.elastic.configuration.IndexConstants;
import br.curso.elastic.model.xwiki.PageType;
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

    public PageType getPageById(String id){
        GetResponse response = elasticsearchClient.prepareGet(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE, id)
                .execute().actionGet();
        PageType page = null;
        try {
            page = objectMapper.readValue(response.getSourceAsBytes(), PageType.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public PageableResult<PageType> getPagesByIds(List<String> ids, int page, int size){
        ids = PagingService.applyPagination(ids, page, size);
        MultiGetResponse multiGetItemResponses = elasticsearchClient.prepareMultiGet()
                .add(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE, ids)
                .execute()
                .actionGet();
        List<PageType> pages = Lists.newArrayList();
        multiGetItemResponses.forEach((item) -> {
            try {
                pages.add(objectMapper.readValue(item.getResponse().getSourceAsBytes(), PageType.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new PageableResult<>(pages, PagingService.totalPages(ids, size));
    }



    public static class PageableResult<T> {

        private List<T> results;

        private int totalPages;

        public PageableResult(List<T> results, int totalPages) {
            this.results = results;
            this.totalPages = totalPages;
        }

        public List<T> getResults() {
            return results;
        }

        public int getTotalPages() {
            return totalPages;
        }

    }

}
