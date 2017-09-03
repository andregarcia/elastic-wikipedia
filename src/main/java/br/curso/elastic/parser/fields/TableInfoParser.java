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

    //private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\n(\\s*\\|\\s*\\n?.*=.*\\n?)+\\}\\}(\\n|$)");
    //private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\\n(\\s?\\|\\s?\\n?[^\\n]+=[^\\n]*\\n)+\\}\\}(\\n|$)");
    //private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\\n(\\s*\\|\\s*\\n?[^\\n]+=[^\\n]*\\n)+\\}\\}(\\n|$)");
    //private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\\n(\\s*\\|\\s*\\n?[^\\n]+=[^\\n]*\\n?)+\\}\\}(\\n|$)");
    //private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\\n(\\s*\\|\\s*\\n?[^\\n]+=[^\\n]*\\n?)+\\s*\\}\\}(\\n|$)");
    private Pattern pattern1 = Pattern.compile("(^|\\n)\\{\\{([^\\n\\}]+)\\n(\\s*\\|\\s*\\n?[^\\n]+=[^\\n]*\\n?)+\\s*\\}\\}");
    private Pattern pattern2 = Pattern.compile("\\|\\s*([^=]+)=([^\\n]+)(\\n|\\}\\})");

    public List<TableInfo> parse(String pageTypeText){
        pageTypeText = preprocess(pageTypeText);
        Matcher m = pattern1.matcher(pageTypeText);
        List<TableInfo> tableInfos = Lists.newArrayList();
        //int start=0;
        while(m.find()){
            //start = m.end();
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

    private String preprocess(String pageTypeText){
        String res = pageTypeText.replaceAll("<!--(.*?)-->", "");       //remove html comments
        res = res.replaceAll("<ref.*?>\\{\\{.*?\\}\\}<\\/ref>", "");    //remove ref tags and inside content
        res = res.replaceAll("<ref.*?\\/>", "");                        //remove ref tags
        res = res.replaceAll("(\\|.*?=\\s+)\\n", "$1");                 //fix new line on tableinfo
        int MAX_REPLACE = 5;
        for(int i=0; i<MAX_REPLACE; i++){
            String replaced = res.replaceAll("(\\|.{0,50}=[^\\n]{1,200});\\n+", "$1");  //remove newline in tableinfos
            if(replaced.equals(res)){
                break;
            }
            res = replaced;
        }
        return res;
    }

}
