package br.curso.elastic.model.local;

import br.curso.elastic.model.xwiki.DiscussionThreadingInfo;
import br.curso.elastic.model.xwiki.RedirectType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;


public class Page {

    protected String title;
    protected BigInteger ns;
    protected BigInteger id;
    protected RedirectType redirect;
    protected String restrictions;
    protected String text;
    protected String rawText;
    protected DiscussionThreadingInfo discussionthreadinginfo;
    protected List<String> subtitles;
    protected List<TableInfo> tableInfo;

    @JsonIgnore
    protected LinksCounter linksCounter;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getNs() {
        return ns;
    }

    public void setNs(BigInteger ns) {
        this.ns = ns;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public RedirectType getRedirect() {
        return redirect;
    }

    public void setRedirect(RedirectType redirect) {
        this.redirect = redirect;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DiscussionThreadingInfo getDiscussionthreadinginfo() {
        return discussionthreadinginfo;
    }

    public void setDiscussionthreadinginfo(DiscussionThreadingInfo discussionthreadinginfo) {
        this.discussionthreadinginfo = discussionthreadinginfo;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public List<TableInfo> getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(List<TableInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }

    public LinksCounter getLinksCounter() {
        return linksCounter;
    }

    public void setLinksCounter(LinksCounter linksCounter) {
        this.linksCounter = linksCounter;
    }

    @JsonInclude
    public List<Link> getLinksTo(){
        return linksCounter.getLinks();
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
}
