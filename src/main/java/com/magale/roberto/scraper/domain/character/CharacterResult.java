
package com.magale.roberto.scraper.domain.character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.magale.roberto.scraper.domain.Thumbnail;
import com.magale.roberto.scraper.domain.Url;

import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterResult implements Comparable<CharacterResult> {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("resourceURI")
    private String resourceURI;
    @JsonProperty("comics")
    private Comics comics;
    @JsonProperty("series")
    private Series series;
    @JsonProperty("stories")
    private Stories stories;
    @JsonProperty("events")
    private Events events;
    @JsonProperty("urls")
    private List<Url> urls = null;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("resourceURI")
    public String getResourceURI() {
        return resourceURI;
    }

    @JsonProperty("resourceURI")
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    @JsonProperty("comics")
    public Comics getComics() {
        return comics;
    }

    @JsonProperty("comics")
    public void setComics(Comics comics) {
        this.comics = comics;
    }

    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(Series series) {
        this.series = series;
    }

    @JsonProperty("stories")
    public Stories getStories() {
        return stories;
    }

    @JsonProperty("stories")
    public void setStories(Stories stories) {
        this.stories = stories;
    }

    @JsonProperty("events")
    public Events getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(Events events) {
        this.events = events;
    }

    @JsonProperty("urls")
    public List<Url> getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    @Override
    public int compareTo(CharacterResult o) {
        if (this == o) return 0;
        if (this.name == null) return -1;

        return this.name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, modified, thumbnail,
                resourceURI, comics, series, stories, events, urls);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CharacterResult other = (CharacterResult) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.description, other.description)
                && Objects.equals(this.modified, other.modified)
                && Objects.equals(this.thumbnail, other.thumbnail)
                && Objects.equals(this.resourceURI, other.resourceURI)
                && Objects.equals(this.comics, other.comics)
                && Objects.equals(this.series, other.series)
                && Objects.equals(this.stories, other.stories)
                && Objects.equals(this.events, other.events)
                && Objects.equals(this.urls, other.urls);
    }
}
