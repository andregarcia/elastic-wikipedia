package br.curso.elastic.parser.fields;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


public class SubtitlesParserTest {

    private static String pageText;
    private static SubtitlesParser subtitlesParser;

    @BeforeClass
    public static void beforeClass() throws IOException {
        pageText = Resources.toString(Resources.getResource("sample_text.txt"), Charsets.UTF_8);
        subtitlesParser = new SubtitlesParser();
    }

    @Test
    public void testParse(){
        List<String> result = subtitlesParser.parse(pageText);
        System.out.println(result);
        assertEquals(26, result.size());
    }

}