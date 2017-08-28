package br.curso.elastic.response;


import java.util.List;

public class MultiItemResponse<T> {

    private List<T> items;

    private Integer page;

    private Integer totalResults;

    private Integer totalPages;

    private Integer pageSize;

    public MultiItemResponse(List<T> items, Integer page, Integer totalResults, Integer totalPages, Integer pageSize) {
        this.items = items;
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
