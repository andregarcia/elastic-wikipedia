package br.curso.elastic.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@ConfigurationProperties(prefix="elasticsearch")
public class ElasticsearchConfig {

    private String clusterName;

    private String host;

    private Integer port;

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private Settings clientSettings() {
        return Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .build();
    }

    @Bean
    public Client client() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(clientSettings())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        return client;
    }

}
