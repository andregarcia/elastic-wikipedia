package br.curso.elastic.processor.elasticsearch;


import br.curso.elastic.model.local.Link;
import br.curso.elastic.model.local.Page;
import br.curso.elastic.service.PageScrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class LinkedFromProcessor {

    @Autowired
    public PageScrollService pageScrollService;

    @Autowired
    private ObjectMapper objectMapper;


    public void createLinkedFrom() throws IOException {
        SearchResponse resp = pageScrollService.scrollAllPages();
        LinkedFromResults linkedFromResults = new LinkedFromResults();
        do {
            for(SearchHit sh : resp.getHits().getHits()){
                processSearchHit(linkedFromResults, sh);
            }
            resp = pageScrollService.scrollAllPages(resp.getScrollId());
        } while(resp.getHits().getHits().length>0);
    }


    private void processSearchHit(LinkedFromResults res, SearchHit sh) throws IOException {
        Page page = objectMapper.readValue(sh.getSourceAsString(), Page.class);
        for(Link l : page.getLinksCounter().getLinksUnsorted()){
            res.addCount(l.getText(), page.getText(), l.getCount());
        }
    }


    public static class LinkedFromResults {

        private Map<String, Map<String, Integer>> linkedFromMap;   // docId -> linkedFromId -> counter

        public LinkedFromResults(){
            linkedFromMap = Maps.newHashMap();
        }

        public void addCount(String docId, String linkedFrom, int count){
            if(!linkedFromMap.containsKey(docId)){
                linkedFromMap.put(docId, Maps.newHashMap());
            }
            if(!linkedFromMap.get(docId).containsKey(linkedFrom)){
                linkedFromMap.get(docId).put(linkedFrom, 0);
            }
            int currentCount = linkedFromMap.get(docId).get(linkedFrom);
            linkedFromMap.get(docId).put(linkedFrom, currentCount + 1);
        }

    }

}
