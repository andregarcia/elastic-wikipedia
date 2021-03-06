package br.curso.elastic.controller;

import br.curso.elastic.configuration.SearchConstants;
import br.curso.elastic.model.xwiki.PageType;
import br.curso.elastic.response.MultiItemResponse;
import br.curso.elastic.service.PageSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PageSearchController {

    @Autowired
    private PageSearchService pageSearchService;

    @RequestMapping(path="/pages/page/{id}", method=GET)
    public PageType getPageById(@PathVariable(name="id") String id){
        return pageSearchService.getPageById(id);
    }

    @RequestMapping(path="/pages/{ids}", method=GET)
    public MultiItemResponse<PageType> getPagesByIds(@PathVariable(name="ids") String ids,
                                        @RequestParam(defaultValue=SearchConstants.PAGE_SIZE) Integer size,
                                        @RequestParam(defaultValue="0") Integer page){
        List<String> idsList = Arrays.asList(ids.split(","));
        PageSearchService.PageableResult<PageType> results = pageSearchService.getPagesByIds(idsList, page, size);
        return new MultiItemResponse<PageType>(
                results.getResults(),
                page,
                idsList.size(),
                results.getTotalPages(),
                size
        );
    }

}
