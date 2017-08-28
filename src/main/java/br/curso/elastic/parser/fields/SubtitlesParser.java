package br.curso.elastic.parser.fields;


import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SubtitlesParser {

    private Pattern pattern = Pattern.compile("={1,}\\s([^=\\|\\n]+?)\\s={1,}");

    public List<String> parse(String pageTypeText){
        List<String> subtitles = Lists.newArrayList();
        Matcher m = pattern.matcher(pageTypeText);
        while(m.find()){
            String subtitle = m.group(1);
            subtitles.add(subtitle);
        }
        return subtitles;
    }



}

