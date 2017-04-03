package com.magale.roberto.scraper.data;

import com.magale.roberto.scraper.domain.character.CharacterResult;
import com.magale.roberto.scraper.domain.comic.ComicResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MarvelCache {
    private List<CharacterResult> mostPopular;
    private Map<CharacterResult, Double> mostInfluential;
    private List<String> toInfect;
    private Map<String, ComicResult> comics;
    private Map<Integer, CharacterResult> characters;

    public MarvelCache() {
    }

    public void setComics(Map<String, ComicResult> comics) {
        this.comics = comics;
    }

    public Map<String, ComicResult> getComics() {
        return comics;
    }

    public void setCharacters(Map<Integer, CharacterResult> characters) {
        this.characters = characters;
    }


    public Map<Integer, CharacterResult> getCharacters() {
        return characters;
    }

    public List<CharacterResult> getMostPopular() {
        return mostPopular;
    }

    public Map<CharacterResult, Double> getMostInfluential() {
        return mostInfluential;
    }

    public List<String> getToInfect() {
        return toInfect;
    }

    public void setMostPopular(List<CharacterResult> mostPopular) {
        this.mostPopular = mostPopular;
    }

    public void setMostInfluential(Map<CharacterResult, Double> mostInfluential) {
        this.mostInfluential = mostInfluential;
    }

    public void setToInfect(List<String> toInfect) {
        this.toInfect = toInfect;
    }
}
