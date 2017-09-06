package br.curso.elastic.parser;

import br.curso.elastic.BaseTest;
import br.curso.elastic.model.local.WikipediaIdRowList;
import br.curso.elastic.processor.xml.WikipediaIdFileProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class WikipediaIdXmlProcessorTest extends BaseTest{

    @Autowired
    private WikipediaIdFileProcessor wikipediaIdXmlProcessor;

    @Test
    public void testGetPageIndexById(){
        Integer res = wikipediaIdXmlProcessor.getPageIndexById("1718");
        assertEquals(1, res.intValue());
    }

    @Test
    public void testGetPageIndexByIdInexistantId(){
        Integer res = wikipediaIdXmlProcessor.getPageIndexById("4123145123");
        assertNull(res);
    }

    @Test
    public void testGetPageIndexByTitle(){
        Integer res = wikipediaIdXmlProcessor.getPageIndexByTitle("Sergipe");
        assertEquals(1, res.intValue());
    }

    @Test
    public void testGetPageIndexByTitleInexistantTitle(){
        Integer res = wikipediaIdXmlProcessor.getPageIndexByTitle("AKkjhkasdkjhgakdjhsa");
        assertNull(res);
    }

    @Test
    public void testGetIndexRangeByInitial(){
        WikipediaIdRowList res = wikipediaIdXmlProcessor.getIndexRangeByInitial("S");
        assertEquals(1, res.getMinIndex().intValue());
        assertEquals(5, res.getMaxIndex().intValue());
    }

    @Test
    public void testGetIndexRangeByInitialInexistantInitial(){
        WikipediaIdRowList res = wikipediaIdXmlProcessor.getIndexRangeByInitial("Z");
        assertNull(res);
    }

}