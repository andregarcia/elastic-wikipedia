package br.curso.elastic.parser;

import br.curso.elastic.model.local.Page;
import br.curso.elastic.model.xwiki.PageType;
import br.curso.elastic.model.xwiki.RevisionType;
import br.curso.elastic.parser.fields.LinkParser;
import br.curso.elastic.parser.fields.SubtitlesParser;
import br.curso.elastic.parser.fields.TableInfoParser;
import br.curso.elastic.parser.fields.TextParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageElementParser {

    @Autowired
    private TextParser textParser;

    @Autowired
    private SubtitlesParser subtitlesParser;

    @Autowired
    private TableInfoParser tableInfoParser;

    @Autowired
    private LinkParser linkParser;

    public Page toPage(PageType pageType){
        Page page = new Page();
        String pageText = getText(pageType);
        page.setText(textParser.parse(pageType));
        page.setSubtitles(subtitlesParser.parse(pageText));
        page.setTableInfo(tableInfoParser.parse(pageText));
        page.setLinks(linkParser.parse(pageText));
        page.setId(pageType.getId());
        page.setDiscussionthreadinginfo(pageType.getDiscussionthreadinginfo());
        page.setNs(pageType.getNs());
        page.setRedirect(pageType.getRedirect());
        page.setRestrictions(pageType.getRestrictions());
        page.setTitle(pageType.getTitle());
        return page;
    }

    private String getText(PageType pageType){
        return ((RevisionType) pageType.getRevisionOrUpload().get(0)).getText().getValue();
    }


}