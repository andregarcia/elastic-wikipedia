package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.LinksCounter;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class LinkParserTest {

    private static String pageText;
    private static LinkParser linkParser;

    @BeforeClass
    public static void beforeClass() throws IOException {
        pageText = Resources.toString(Resources.getResource("sample_text.txt"), Charsets.UTF_8);
        linkParser = new LinkParser();
    }

    @Test
    public void testParse(){
        LinksCounter result = linkParser.parse(pageText);
        assertEquals(320, result.size());
        assertEquals("Grande Nuvem de Magalhães", result.getLinks().get(0).getText());
    }


}