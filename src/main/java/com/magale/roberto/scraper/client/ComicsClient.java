package com.magale.roberto.scraper.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magale.roberto.scraper.domain.comic.ComicData;
import com.magale.roberto.scraper.domain.comic.ComicResult;
import com.magale.roberto.scraper.domain.comic.ComicsResponse;
import jersey.repackaged.com.google.common.base.Throwables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static jersey.repackaged.com.google.common.base.Throwables.propagateIfPossible;

@Component
public class ComicsClient {

    private final MarvelClient client;
    private final ObjectMapper objectMapper;

    @Autowired
    public ComicsClient(MarvelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Map<String, ComicResult> getAll() {
        Map<String, ComicResult> allComics = new HashMap<>();
        int offset = 0;
        int total;
        try (FileWriter fw = new FileWriter("comics.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            do {
                long startTime = System.currentTimeMillis();
                MarvelRequest request = MarvelRequest.builder()
                        .withPagination(offset, 100)
                        .withPaths("comics").build();
                ComicsResponse comicsResponse = this.client.get(request, ComicsResponse.class);
                ComicData characterData = comicsResponse.getComicData();
                total = characterData.getTotal();
                offset += characterData.getCount();
                for (ComicResult result : characterData.getResults()) {
                    allComics.put(result.getTitle(), result);
                    out.println(objectMapper.writeValueAsString(result));
                }
                String fetchingMessage = new StringBuilder("Fetched ")
                        .append(offset).append(" of ").append(total)
                        .append(" comics in ").append(System.currentTimeMillis() - startTime)
                        .append(" milliseconds").toString();
                System.out.println(fetchingMessage);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    propagateIfPossible(e);
                }
            } while (offset < total);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }


        return allComics;
    }
}
