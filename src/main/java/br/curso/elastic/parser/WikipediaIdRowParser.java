package br.curso.elastic.parser;

import br.curso.elastic.model.local.WikipediaIdRow;

public class WikipediaIdRowParser {

    public static WikipediaIdRow parse(int index, String row){
        String[] spl = row.split(":");
        return new WikipediaIdRow(index, spl[1], spl[2]);
    }

}
