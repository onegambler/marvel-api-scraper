
package com.magale.roberto.scraper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail {

    @JsonProperty("path")
    private String path;
    @JsonProperty("extension")
    private String extension;

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("extension")
    public String getExtension() {
        return extension;
    }

    @JsonProperty("extension")
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Thumbnail{");
        sb.append("path='").append(path).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, extension);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Thumbnail other = (Thumbnail) obj;
        return Objects.equals(this.path, other.path)
                && Objects.equals(this.extension, other.extension);
    }
}
