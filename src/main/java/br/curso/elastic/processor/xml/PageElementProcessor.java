package br.curso.elastic.processor.xml;

import br.curso.elastic.configuration.IndexConstants;
import br.curso.elastic.model.local.Page;
import br.curso.elastic.mapper.PageMapper;
import br.curso.elastic.model.xwiki.PageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageElementProcessor implements ParsedElementProcessor<PageType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageElementProcessor.class);

    @Autowired
    private Client elasticsearchClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PageMapper pageElementParser;

    @Override
    public IndexResponse processElement(PageType pageType) {
    try {
        LOGGER.info("Processing page id={} title={}", pageType.getId(), pageType.getTitle());
        Page page = pageElementParser.toPage(pageType);
        return elasticsearchClient.prepareIndex(IndexConstants.PAGES_INDEX, IndexConstants.PAGES_PAGE_TYPE)
                .setId(pageType.getId().toString())
                .setSource(objectMapper.writeValueAsString(page), XContentType.JSON)
                .execute()
                .actionGet();
        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing page id=" + pageType.getId(), e);
        }
        return null;
    }

}
