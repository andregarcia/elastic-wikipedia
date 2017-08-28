package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.TableInfo;
import br.curso.elastic.model.local.TableRow;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TableInfoParser {

    private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\n(\\s*\\|\\s*\\n?.*=.*\\n?)+\\}\\}(\\n|$)");
    private Pattern pattern2 = Pattern.compile("\\|\\s*([^=]+)=([^\\n]+)(\\n|\\}\\})");

    public List<TableInfo> parse(String pageTypeText){
        Matcher m = pattern1.matcher(pageTypeText);
        List<TableInfo> tableInfos = Lists.newArrayList();
        while(m.find()){
            TableInfo tinfo = new TableInfo(m.group(2).trim());
            String tableStr = m.group().trim();
            Matcher m2 = pattern2.matcher(tableStr);
            while(m2.find()){
                tinfo.addRow(new TableRow(m2.group(1).trim(), m2.group(2).trim().replaceAll("\\}\\}$", "")));
            }
            tableInfos.add(tinfo);
        }
        return tableInfos;
    }

}
