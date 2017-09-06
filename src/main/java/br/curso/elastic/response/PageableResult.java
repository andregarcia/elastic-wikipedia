package br.curso.elastic.response;

import java.util.List;


public class PageableResult<T> {

    private List<T> results;

    private int totalPages;

    public PageableResult(List<T> results, int totalPages) {
        this.results = results;
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
