package br.curso.elastic.model.local;

import br.curso.elastic.model.xwiki.DiscussionThreadingInfo;
import br.curso.elastic.model.xwiki.RedirectType;
import br.curso.elastic.model.xwiki.RevisionType;
import br.curso.elastic.model.xwiki.UploadType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by andregarcia on 26/08/17.
 */
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
    protected List<Link> links;

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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
}
