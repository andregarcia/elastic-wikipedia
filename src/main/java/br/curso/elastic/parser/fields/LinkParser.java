package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.Link;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class LinkParser {

    private static final Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");

    public List<Link> parse(String pageTypeText){
        List<Link> links = Lists.newArrayList();
        Matcher m = pattern.matcher(pageTypeText);
        while(m.find()){
            String ref = m.group(1).trim();
            if(isImageReference(ref)){
                continue;
            }
            links.addAll(
                    Arrays.stream(splitMultiReference(ref)).
                            map((Link::new)).
                            collect(Collectors.toList()));
        }
        return links;
    }



    public boolean isImageReference(String s){
        return s.contains("Imagem:");
    }

    public String[] splitMultiReference(String s){
        return s.split("[\\|#]");
    }

}
