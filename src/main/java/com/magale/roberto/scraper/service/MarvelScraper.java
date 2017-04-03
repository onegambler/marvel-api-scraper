package com.magale.roberto.scraper.service;

import com.magale.roberto.scraper.client.CharactersClient;
import com.magale.roberto.scraper.client.ComicsClient;
import com.magale.roberto.scraper.config.Properties;
import com.magale.roberto.scraper.data.MarvelCache;
import com.magale.roberto.scraper.domain.Item;
import com.magale.roberto.scraper.domain.character.CharacterResult;
import com.magale.roberto.scraper.domain.comic.Characters;
import com.magale.roberto.scraper.domain.comic.ComicResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.compare;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
public class MarvelScraper {

    private MarvelCache marvelCache;
    private final CharactersClient charactersClient;
    private final ComicsClient comicsClient;
    private final Properties properties;

    @Autowired
    public MarvelScraper(MarvelCache cache, CharactersClient charactersClient,
                         ComicsClient comicsClient, Properties properties) {
        this.marvelCache = cache;
        this.charactersClient = charactersClient;
        this.comicsClient = comicsClient;
        this.properties = properties;
    }

    public void buildCache() {
        System.out.println("\nFetching CHARACTERS");
        Map<Integer, CharacterResult> allCharacters = this.charactersClient.getAll();
        marvelCache.setCharacters(allCharacters);

        /**
         * Downloading comics is a bit flaky and takes a lot of time. A solution is to build the comics
         * response from the Characters. It will contain only a subset of the actual data. But better than nothing.
         */
        Map<String, ComicResult> allComics;
        if(properties.shouldDownloadComics()) {
            System.out.println("\nFetching COMICS");
            allComics = comicsClient.getAll();
        } else {
            allComics = new HashMap<>();
            for (CharacterResult characterResult : allCharacters.values()) {
                for (Item item : characterResult.getComics().getItems()) {
                    ComicResult comicResult = allComics.computeIfAbsent(item.getName(), s -> createComicResult(item));
                    Characters characters = comicResult.getCharacters();
                    characters.getItems().add(createCharacterItem(characterResult));
                    characters.setAvailable(characters.getItems().size());
                }
            }
        }
        marvelCache.setComics(allComics);

        List<CharacterResult> mostPopular = calculateMostPopular(allCharacters);
        marvelCache.setMostPopular(mostPopular);

        Map<CharacterResult, Double> mostInfluential = calculateMostInfluential(allComics, allCharacters);
        marvelCache.setMostInfluential(mostInfluential);

        List<String> toInfect = calculateToInfect(allComics, allCharacters);
        marvelCache.setToInfect(toInfect);
    }

    private List<String> calculateToInfect(Map<String, ComicResult> allComics,
                                           Map<Integer, CharacterResult> allCharacters) {
        Map<Integer, Set<Item>> costarringMap = new HashMap<>();

        for (CharacterResult character : allCharacters.values()) {
            costarringMap.computeIfAbsent(character.getId(), s -> new HashSet<>());
            for (Item item : character.getComics().getItems()) {
                List<Item> costars = allComics.get(item.getName()).getCharacters().getItems();
                costarringMap.get(character.getId()).addAll(costars);
            }
        }

        return costarringMap
                .entrySet()
                .stream()
                .sorted((o1, o2) -> compare(o2.getValue().size(), o1.getValue().size()))
                .limit(10)
                .map(entry -> allCharacters.get(entry.getKey()).getName())
                .collect(toList());
    }

    private Map<CharacterResult, Double> calculateMostInfluential(Map<String, ComicResult> allComics,
                                                                  Map<Integer, CharacterResult> allCharacters) {


        Map<Integer, Double> mostInfluentialScoreMap = new HashMap<>();
        for (CharacterResult character : allCharacters.values()) {
            double score = 0;
            for (Item comic : character.getComics().getItems()) {
                int comicCharactersCount = allComics.get(comic.getName()).getCharacters().getAvailable();
                if (comicCharactersCount > 1) {
                    score += comicCharactersCount / (double) allComics.size();
                }
            }
            mostInfluentialScoreMap.put(character.getId(), score);
        }

        return mostInfluentialScoreMap
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(10)
                .collect(toMap(o -> allCharacters.get(o.getKey()), Map.Entry::getValue));
    }

    private List<CharacterResult> calculateMostPopular(Map<Integer, CharacterResult> allCharacters) {
        return allCharacters
                .values()
                .stream()
                .sorted((o1, o2) -> compare(getComicSize(o2), getComicSize(o1)))
                .limit(10)
                .collect(toList());
    }

    private ComicResult createComicResult(Item item) {
        ComicResult comicResult = new ComicResult();
        Characters characters = new Characters();
        characters.setAvailable(0);
        characters.setItems(new ArrayList<>());
        comicResult.setCharacters(characters);
        comicResult.setTitle(item.getName());
        return comicResult;
    }

    private Item createCharacterItem(CharacterResult characterResult) {
        Item character = new Item();
        character.setName(characterResult.getName());
        return character;
    }

    private int getComicSize(CharacterResult result) {
        return result.getComics().getAvailable();
    }
}
