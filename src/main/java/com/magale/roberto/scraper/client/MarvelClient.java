package com.magale.roberto.scraper.client;

import com.magale.roberto.scraper.util.MarvelApiHashGenerator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static java.lang.System.currentTimeMillis;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static jersey.repackaged.com.google.common.base.Throwables.propagateIfPossible;

public class MarvelClient {

    private static final int RETRIES = 3;
    private final String endpoint;
    private final String privateKey;
    private final String publicKey;
    private final Client client;
    private final MarvelApiHashGenerator hashGenerator;


    private MarvelClient(Builder builder) {
        this.privateKey = builder.privateKey;
        this.publicKey = builder.publicKey;
        this.client = builder.client;
        this.hashGenerator = builder.hashGenerator;
        this.endpoint = builder.endpoint;
    }

    public <T> T get(MarvelRequest request, Class<T> returnClass) {
        String timestamp = String.valueOf(currentTimeMillis());
        WebTarget webTarget = client
                .target(endpoint)
                .path(request.getPath())
                .queryParam("apikey", publicKey)
                .queryParam("ts", timestamp)
                .queryParam("hash", hashGenerator.generateHash(timestamp, privateKey, publicKey));

        if (request.getLimit() > 0) {
            webTarget = webTarget.queryParam("limit", request.getLimit());
        }

        if (request.getOffset() > 0) {
            webTarget = webTarget.queryParam("offset", request.getOffset());
        }

        Response clientResponse = null;
        int retryCount = 0;
        do {
            try {
                if(retryCount > 0) {
                    try {
                        Thread.sleep(100 * retryCount);
                    } catch (InterruptedException e) {
                        propagateIfPossible(e);
                    }
                }
                clientResponse = webTarget.request(APPLICATION_JSON_TYPE).get();
            } catch (Exception e) {
                System.err.println("Failed call");
            } finally {
                retryCount++;
            }
        } while ((clientResponse == null || clientResponse.getStatus() != 200) && retryCount < RETRIES);

        if (clientResponse == null) {
            System.err.println("Reached max number of retries. Aborting...");
            System.exit(-2);
        }
        return clientResponse.readEntity(returnClass);
    }

    public static Builder build() {
        return new Builder();
    }

    public static final class Builder {
        private String privateKey;
        private String publicKey;
        private Client client;
        private String endpoint;
        private MarvelApiHashGenerator hashGenerator;

        private Builder() {
        }

        public Builder withPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder withPublicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        public Builder withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder withHashGenerator(MarvelApiHashGenerator hashGenerator) {
            this.hashGenerator = hashGenerator;
            return this;
        }

        public Builder withDefaultHashGenerator() {
            this.hashGenerator = new MarvelApiHashGenerator();
            ;
            return this;
        }

        public MarvelClient build() {
            return new MarvelClient(this);
        }
    }
}
