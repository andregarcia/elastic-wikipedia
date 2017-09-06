package br.curso.elastic.processor.xml;

import br.curso.elastic.model.local.WikipediaIdRow;
import br.curso.elastic.model.local.WikipediaIdRowList;
import br.curso.elastic.parser.WikipediaIdRowParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WikipediaIdFileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikipediaIdFileProcessor.class);

    @Value("${wikipedia.sample-ids-xml-file}")
    private String sampleIdsWikipediaXmlFile;

    @Value("${wikipedia.ids-xml-file}")
    private String idsWikipediaXmlFile;

    @Value("${wikipedia.use-sample}")
    private Boolean useSample;

    public Integer getPageIndexById(String id) {
        Optional<WikipediaIdRow> result;
        try {
            result = Files.lines(Paths.get(getWikipediaIdsFile()))
                    .map(new WikipediaIdRowParserFunction())
                    .filter(rowObj -> rowObj.getId().equals(id))
                    .findFirst();
            if(result.isPresent()){
                return result.get().getIndex();
            }
        } catch (IOException e) {
            LOGGER.error("Error getting page index by id, id=" + id, e);
        }
        return null;
    }

    public Integer getPageIndexByTitle(String title){
        Optional<WikipediaIdRow> result;
        try {
            WikipediaIdRowParserFunction mapFunction = new WikipediaIdRowParserFunction();
            result = Files.lines(Paths.get(getWikipediaIdsFile()))
                    .map(mapFunction)
                    .filter(rowObj -> rowObj.getTitle().equals(title))
                    .findFirst();
            if(result.isPresent()){
                return result.get().getIndex();
            }
        } catch (IOException e) {
            LOGGER.error("Error getting page index by title, title=" + title, e);
        }
        return null;
    }

    public WikipediaIdRowList getIndexRangeByInitial(String initial){
        Optional<WikipediaIdRow> result;
        try {
            WikipediaIdRowParserFunction mapFunction = new WikipediaIdRowParserFunction();
            List<WikipediaIdRow> parsedRows = Files.lines(Paths.get(getWikipediaIdsFile()))
                    .map(mapFunction)
                    .filter(rowObj -> rowObj.getTitle().toLowerCase().startsWith(initial.toLowerCase()))
                    .collect(Collectors.toList());
            return new WikipediaIdRowList(parsedRows);
        } catch (IOException e) {
            LOGGER.error("Error getting page indexes by initial, initial=" + initial, e);
        }
        return null;
    }


    private String getWikipediaIdsFile(){
        if(useSample){
            return sampleIdsWikipediaXmlFile;
        }
        return idsWikipediaXmlFile;
    }


    private static class WikipediaIdRowParserFunction implements Function<String, WikipediaIdRow>{
        private int count = 0;
        @Override
        public WikipediaIdRow apply(String s) {
            WikipediaIdRow parsed = WikipediaIdRowParser.parse(count, s);
            count += 1;
            return parsed;
        }
    }

}
