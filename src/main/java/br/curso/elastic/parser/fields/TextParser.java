package br.curso.elastic.parser.fields;


import br.curso.elastic.model.xwiki.PageType;
import br.curso.elastic.model.xwiki.RevisionType;
import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TextParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextParser.class);

    @Autowired
    private WikiModel wikiModel;

    public String parse(PageType pageType){
        String plainStr = null;
        try {
            plainStr = wikiModel.render(new PlainTextConverter(), getText(pageType));
        } catch (IOException e) {
            LOGGER.error("Error converting PageType to String", e);
        }
        return plainStr;
    }

    private String getText(PageType pageType){
        return ((RevisionType) pageType.getRevisionOrUpload().get(0)).getText().getValue();
    }

}
