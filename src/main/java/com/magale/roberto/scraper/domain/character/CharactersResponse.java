
package com.magale.roberto.scraper.domain.character;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharactersResponse {

    @JsonProperty("code")
    private String code;
    @JsonProperty("status")
    private String status;
    @JsonProperty("copyright")
    private String copyright;
    @JsonProperty("attributionText")
    private String attributionText;
    @JsonProperty("attributionHTML")
    private String attributionHTML;
    @JsonProperty("etag")
    private String etag;
    @JsonProperty("data")
    private CharacterData characterData;

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("attributionText")
    public String getAttributionText() {
        return attributionText;
    }

    @JsonProperty("attributionText")
    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    @JsonProperty("attributionHTML")
    public String getAttributionHTML() {
        return attributionHTML;
    }

    @JsonProperty("attributionHTML")
    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    @JsonProperty("etag")
    public String getEtag() {
        return etag;
    }

    @JsonProperty("etag")
    public void setEtag(String etag) {
        this.etag = etag;
    }

    @JsonProperty("data")
    public CharacterData getCharacterData() {
        return characterData;
    }

    @JsonProperty("data")
    public void setCharacterData(CharacterData characterData) {
        this.characterData = characterData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharactersResponse{");
        sb.append("code='").append(code).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", copyright='").append(copyright).append('\'');
        sb.append(", attributionText='").append(attributionText).append('\'');
        sb.append(", attributionHTML='").append(attributionHTML).append('\'');
        sb.append(", etag='").append(etag).append('\'');
        sb.append(", data=").append(characterData);
        sb.append('}');
        return sb.toString();
    }
}
