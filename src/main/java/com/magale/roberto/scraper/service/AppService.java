package com.magale.roberto.scraper.service;

import com.magale.roberto.scraper.data.MarvelCache;
import com.magale.roberto.scraper.domain.character.CharacterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppService {

    private final MarvelScraper scraper;
    private final MarvelCache marvelCache;

    @Autowired
    public AppService(MarvelScraper scraper, MarvelCache marvelCache) {
        this.scraper = scraper;
        this.marvelCache = marvelCache;
    }

    public void initialise() {
        this.scraper.buildCache();
    }

    public void printCharacters() {
        marvelCache.getCharacters()
                .values()
                .stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .forEach(character -> System.out.println(character.getName()));
    }

    public void printMostPopular() {
        List<CharacterResult> all = marvelCache.getMostPopular();
        for (CharacterResult character : all) {
            System.out.println(character.getName() + ": " + character.getComics().getAvailable());
        }
    }

    public void printHighestInfluence() {
        Map<CharacterResult, Double> all = marvelCache.getMostInfluential();
        all.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey().getName() + " - Score: " + entry.getValue()));
    }

    public void printToInfectList() {
        marvelCache.getToInfect().forEach(System.out::println);
    }
}
