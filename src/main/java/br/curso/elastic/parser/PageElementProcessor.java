package br.curso.elastic.parser;

import br.curso.elastic.configuration.IndexConstants;
import br.curso.elastic.model.local.Page;
import br.curso.elastic.model.xwiki.PageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageElementProcessor implements ParsedElementProcessor<PageType> {

    @Autowired
    private Client elasticsearchClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PageElementParser pageElementParser;

    @Override
    public IndexResponse processElement(PageType pageType) {
    try {
        Page page = pageElementParser.toPage(pageType);
        return elasticsearchClient.prepareIndex(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE)
                .setId(pageType.getId().toString())
                .setSource(objectMapper.writeValueAsString(page), XContentType.JSON)
                .execute()
                .actionGet();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
