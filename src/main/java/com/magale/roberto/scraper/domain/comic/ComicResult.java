
package com.magale.roberto.scraper.domain.comic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.magale.roberto.scraper.domain.Thumbnail;
import com.magale.roberto.scraper.domain.Url;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicResult {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("digitalId")
    private Integer digitalId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("issueNumber")
    private Integer issueNumber;
    @JsonProperty("variantDescription")
    private String variantDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("upc")
    private String upc;
    @JsonProperty("diamondCode")
    private String diamondCode;
    @JsonProperty("ean")
    private String ean;
    @JsonProperty("issn")
    private String issn;
    @JsonProperty("format")
    private String format;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("resourceURI")
    private String resourceURI;
    @JsonProperty("urls")
    private List<Url> urls = null;
    @JsonProperty("series")
    private Series series;
    @JsonProperty("variants")
    private List<Object> variants = null;
    @JsonProperty("collections")
    private List<Object> collections = null;
    @JsonProperty("collectedIssues")
    private List<Object> collectedIssues = null;
    @JsonProperty("dates")
    private List<Date> dates = null;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("creators")
    private Creators creators;
    @JsonProperty("characters")
    private Characters characters;
    @JsonProperty("stories")
    private Stories stories;
    @JsonProperty("events")
    private Events events;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("digitalId")
    public Integer getDigitalId() {
        return digitalId;
    }

    @JsonProperty("digitalId")
    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("issueNumber")
    public Integer getIssueNumber() {
        return issueNumber;
    }

    @JsonProperty("issueNumber")
    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    @JsonProperty("variantDescription")
    public String getVariantDescription() {
        return variantDescription;
    }

    @JsonProperty("variantDescription")
    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
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

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("upc")
    public String getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @JsonProperty("diamondCode")
    public String getDiamondCode() {
        return diamondCode;
    }

    @JsonProperty("diamondCode")
    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    @JsonProperty("ean")
    public String getEan() {
        return ean;
    }

    @JsonProperty("ean")
    public void setEan(String ean) {
        this.ean = ean;
    }

    @JsonProperty("issn")
    public String getIssn() {
        return issn;
    }

    @JsonProperty("issn")
    public void setIssn(String issn) {
        this.issn = issn;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("pageCount")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("resourceURI")
    public String getResourceURI() {
        return resourceURI;
    }

    @JsonProperty("resourceURI")
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    @JsonProperty("urls")
    public List<Url> getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(Series series) {
        this.series = series;
    }

    @JsonProperty("variants")
    public List<Object> getVariants() {
        return variants;
    }

    @JsonProperty("variants")
    public void setVariants(List<Object> variants) {
        this.variants = variants;
    }

    @JsonProperty("collections")
    public List<Object> getCollections() {
        return collections;
    }

    @JsonProperty("collections")
    public void setCollections(List<Object> collections) {
        this.collections = collections;
    }

    @JsonProperty("collectedIssues")
    public List<Object> getCollectedIssues() {
        return collectedIssues;
    }

    @JsonProperty("collectedIssues")
    public void setCollectedIssues(List<Object> collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    @JsonProperty("dates")
    public List<Date> getDates() {
        return dates;
    }

    @JsonProperty("dates")
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonProperty("creators")
    public Creators getCreators() {
        return creators;
    }

    @JsonProperty("creators")
    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    @JsonProperty("characters")
    public Characters getCharacters() {
        return characters;
    }

    @JsonProperty("characters")
    public void setCharacters(Characters characters) {
        this.characters = characters;
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

}
