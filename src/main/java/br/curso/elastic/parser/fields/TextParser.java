package br.curso.elastic.parser.fields;


import br.curso.elastic.model.xwiki.PageType;
import br.curso.elastic.model.xwiki.RevisionType;
import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TextParser {

    @Autowired
    private WikiModel wikiModel;

    public String parse(PageType pageType){
        String plainStr = null;
        try {
            plainStr = wikiModel.render(new PlainTextConverter(), getText(pageType));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plainStr;
    }

    private String getText(PageType pageType){
        return ((RevisionType) pageType.getRevisionOrUpload().get(0)).getText().getValue();
    }

}
