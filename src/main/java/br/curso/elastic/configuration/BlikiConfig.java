package br.curso.elastic.configuration;

import info.bliki.wiki.model.WikiModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlikiConfig {


    @Bean
    public WikiModel wikiModel(){
        return new WikiModel("https://www.mywiki.com/wiki/${image}", "https://www.mywiki.com/wiki/${title}");
    }



}
