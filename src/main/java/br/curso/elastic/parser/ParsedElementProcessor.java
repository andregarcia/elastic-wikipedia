package br.curso.elastic.parser;

import org.elasticsearch.action.index.IndexResponse;

/**
 * Created by andregarcia on 27/05/17.
 */
public interface ParsedElementProcessor<T> {

    public IndexResponse processElement(T t);

}
