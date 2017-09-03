package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.TableInfo;
import br.curso.elastic.model.local.TableRow;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class TableInfoParser2 {

    private Pattern pattern1 = Pattern.compile("\\n\\s*\\|\\s*([^=]+)=([^\\n]+)");

    public List<TableInfo> parse(String pageTypeText){
        pageTypeText = preprocess(pageTypeText);
        Matcher m = pattern1.matcher(pageTypeText);
        List<TableInfo> tableInfos = Lists.newArrayList();
        TableInfo tinfo = new TableInfo();
        while(m.find()){
            tinfo.addRow(new TableRow(m.group(1), m.group(2)));
        }
        tableInfos.add(tinfo);
        return tableInfos;
    }

    private String preprocess(String pageTypeText){
        String res = pageTypeText.replaceAll("<!--(.*?)-->", "");       //remove html comments
        res = res.replaceAll("<ref.*?>\\{\\{.*?\\}\\}<\\/ref>", "");    //remove ref tags and inside content
        res = res.replaceAll("<ref.*?\\/>", "");                        //remove ref tags
        res = res.replaceAll("(\\|.*?=\\s+)\\n", "$1");                 //fix new line on tableinfo
        int MAX_REPLACE = 5;
        for(int i=0; i<MAX_REPLACE; i++){
            String replaced = res.replaceAll("(\\|[^\\n\\[]+?)(\\|.*?=)", "$1\n$2");  //remove newline in tableinfos
            if(replaced.equals(res)){
                break;
            }
            res = replaced;
        }
        return res;
    }
}
