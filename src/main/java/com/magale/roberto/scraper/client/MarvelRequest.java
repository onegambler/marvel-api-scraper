package com.magale.roberto.scraper.client;

import java.util.HashMap;
import java.util.Map;

import static jersey.repackaged.com.google.common.base.Preconditions.checkArgument;

public class MarvelRequest {

    private final Map<String, String> params;
    private final String path;
    private final Class<?> returnClass;
    private final int limit;
    private final int offset;

    public MarvelRequest(Builder marvelRequestBuilder) {
        this.params = marvelRequestBuilder.params;
        this.path = marvelRequestBuilder.path;
        this.returnClass = marvelRequestBuilder.returnClass;
        this.limit = marvelRequestBuilder.limit;
        this.offset = marvelRequestBuilder.offset;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getPath() {
        return path;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public static final class Builder {
        private Map<String, String> params = new HashMap<>();
        private String path;
        public Class<?> returnClass;
        private int limit;
        private int offset;

        private Builder() {
        }

        public Builder withParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder withPaths(String path) {
            this.path = path;
            return this;
        }

        public Builder withReturnClass(Class<?> clazz) {
            this.returnClass = clazz;
            return this;
        }

        public Builder withPagination(int offset, int limit) {
            checkArgument(offset >= 0, "Offset cannot be a negative number");
            checkArgument(limit >= 0 && limit <= 100, "Limit can be only a number between 0 and 100");
            this.limit = limit;
            this.offset = offset;
            return this;
        }

        public MarvelRequest build() {
            return new MarvelRequest(this);
        }
    }
}
