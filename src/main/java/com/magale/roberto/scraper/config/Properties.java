package com.magale.roberto.scraper.config;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class Properties {
    private java.util.Properties prop;

    public Properties() {
        prop = new java.util.Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            System.err.println("Failed to load property file");
            System.exit(-1);
        }
    }


    public String getPrivateKey() {
        //return privateKey;
        return prop.getProperty("marvel.private.api.key");
    }

    public String getPublicKey() {
        return prop.getProperty("marvel.public.api.key");
        //return publicKey;
    }

    public String getMarvelApiEndpoint() {
        return prop.getProperty("marvel.api.endpoint");
//        return marvelApiEndpoint;
    }

    public boolean shouldDownloadComics() {
        String property = prop.getProperty("download.comics", "false");
        return Boolean.valueOf(property);
    }
}
