package br.curso.elastic.controller;

import br.curso.elastic.response.IndexResponse;
import br.curso.elastic.service.PageIndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PageIndexingController {

    @Autowired
    private PageIndexingService pageIndexingService;


    @RequestMapping(path="/pages/index/{id}", method=POST)
    public IndexResponse indexPageById(@PathVariable Integer id){
        boolean indexed = pageIndexingService.indexById(id.toString());
        return indexed ? new IndexResponse(true, 1) : new IndexResponse(false, 0);
    }

    @RequestMapping(path="/pages/index", method=POST)
    public IndexResponse indexPages(@RequestParam(name = "initial", required = false) Character initial){
        return handleIndexPages(initial);
    }

    private IndexResponse handleIndexPages(Character initial){
        if(initial == null){
            int docCount = pageIndexingService.indexAll();
            return docCount > 0 ? new IndexResponse(true, docCount) : new IndexResponse(false, 0);
        }
        else{
            int docCount = pageIndexingService.indexByInitial(initial);
            return docCount > 0 ? new IndexResponse(true, docCount) : new IndexResponse(false, 0);
        }
    }
}
