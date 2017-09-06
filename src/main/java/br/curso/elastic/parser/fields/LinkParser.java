package br.curso.elastic.parser.fields;

import br.curso.elastic.model.local.Link;
import br.curso.elastic.model.local.LinksCounter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class LinkParser {

    private static final Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");

    public LinksCounter parse(String pageTypeText){
        LinksCounter linksCounter = new LinksCounter();
        Matcher m = pattern.matcher(pageTypeText);
        while(m.find()){
            String ref = m.group(1).trim();
            if(isImageReference(ref)){
                continue;
            }
            linksCounter.addAll(
                    Arrays.stream(splitMultiReference(ref)).
                            map((Link::new)).
                            collect(Collectors.toList()));
        }
        return linksCounter;
    }

    public boolean isImageReference(String s){
        return s.contains("Imagem:");
    }

    public String[] splitMultiReference(String s){
        return s.split("[\\|#]");
    }

}
