package com.magale.roberto.scraper.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.magale.roberto.scraper.client.MarvelClient;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
@ComponentScan(basePackages = {"com.magale.roberto.scraper"})
public class AppConfig {

    @Autowired
    private Properties properties;

    @Bean
    public Client restClient() {
        ClientConfig cc = new ClientConfig().register(JacksonJsonProvider.class);
        return ClientBuilder.newClient(cc);
    }

    @Bean
    public MarvelClient marvelClient(Client restClient) {
        return MarvelClient.build()
                .withClient(restClient)
                .withEndpoint(properties.getMarvelApiEndpoint())
                .withDefaultHashGenerator()
                .withPrivateKey(properties.getPrivateKey())
                .withPublicKey(properties.getPublicKey())
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
