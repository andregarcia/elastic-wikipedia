package br.curso.elastic.model.local;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


public class WikipediaIdRowList extends ArrayList<WikipediaIdRow> {

    private Set<String> ids;

    public WikipediaIdRowList(Collection<WikipediaIdRow> values){
        super(values);
        ids = createIdsSet(values);
    }

    public Integer getMinIndex(){
        Integer min = null;
        for(WikipediaIdRow idRow : this){
            if(min==null || idRow.getIndex()<min) min = idRow.getIndex();
        }
        return min;
    }

    public Integer getMaxIndex(){
        Integer max = null;
        for(WikipediaIdRow idRow : this){
            if(max==null || idRow.getIndex()>max) max = idRow.getIndex();
        }
        return max;
    }

    public boolean contains(String id){
        return ids.contains(id);
    }

    private Set<String> createIdsSet(Collection<WikipediaIdRow> wikipediaIdRows){
        Set<String> ids = Sets.newHashSet();
        for(WikipediaIdRow row : wikipediaIdRows){
            ids.add(row.getId());
        }
        return ids;
    }

}
