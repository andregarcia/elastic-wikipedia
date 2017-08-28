package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.TableInfo;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


public class TableInfoParserTest {

    private static TableInfoParser tableInfoParser;
    private static String infoTable;

    @BeforeClass
    public static void initClass() throws IOException {
        tableInfoParser = new TableInfoParser();
        infoTable = Resources.toString(Resources.getResource("sample_wiki_table.txt"), Charsets.UTF_8);
    }

    @Test
    public void testParse(){
        List<TableInfo> tableInfos = tableInfoParser.parse(infoTable);
        assertEquals(2, tableInfos.size());

        assertEquals(36, tableInfos.get(0).getRows().size());
        assertEquals("Info/Estado do Brasil", tableInfos.get(0).getName());

        assertEquals(26, tableInfos.get(1).getRows().size());
        assertEquals("Info2/Sergipe", tableInfos.get(1).getName());

        assertEquals("nome", tableInfos.get(0).getRows().get(0).getKey());
        assertEquals("Sergipe", tableInfos.get(0).getRows().get(0).getValue());
        assertEquals("indicador_ano", tableInfos.get(0).getRows().get(35).getKey());
        assertEquals("2010/2015", tableInfos.get(0).getRows().get(35).getValue());

        assertEquals("esp_vida_ano", tableInfos.get(1).getRows().get(0).getKey());
        assertEquals("2015", tableInfos.get(1).getRows().get(0).getValue());
        assertEquals("Maior altitude", tableInfos.get(1).getRows().get(25).getKey());
        assertEquals("742m", tableInfos.get(1).getRows().get(25).getValue());
    }

}