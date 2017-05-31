package br.curso.elastic.service;


import java.util.List;

public class PagingService {

    public static <T> List<T> applyPagination(List<T> list, int page, int pageSize){
        int start = page*pageSize;
        int end = (page+1)*pageSize;
        return list.subList(start, end<=list.size() ? end : list.size());
    }

    public static <T> int totalPages(List<T> list, int pageSize){
        return (int) Math.ceil(((double)list.size())/pageSize);
    }

}
