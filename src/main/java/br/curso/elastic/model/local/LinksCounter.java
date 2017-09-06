package br.curso.elastic.model.local;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LinksCounter {

    @JsonIgnore
    private Map<String, Link> linksMap;

    public LinksCounter() {
        this.linksMap = Maps.newHashMap();
    }

    public void addLink(Link link){
        if(!linksMap.containsKey(link.getText())){
            linksMap.put(link.getText(), link);
        }
        linksMap.get(link.getText()).incrementCount();
    }

    public void addAll(Collection<Link> links){
        links.forEach(l -> addLink(l));
    }

    @JsonInclude
    public List<Link> getLinks(){
        List<Link> linksAsList = Lists.newArrayList(linksMap.values());
        linksAsList.sort((o1, o2) -> o1.getText().toLowerCase().compareTo(o2.getText().toLowerCase()));
        return linksAsList;
    }

    @JsonIgnore
    public List<Link> getLinksUnsorted(){
        return Lists.newArrayList(linksMap.values());
    }

    public int size(){
        return linksMap.size();
    }
}
