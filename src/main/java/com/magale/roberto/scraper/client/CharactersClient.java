package com.magale.roberto.scraper.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magale.roberto.scraper.domain.character.CharactersResponse;
import com.magale.roberto.scraper.domain.character.CharacterData;
import com.magale.roberto.scraper.domain.character.CharacterResult;
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
public class CharactersClient {

    private final MarvelClient client;
    private final ObjectMapper objectMapper;

    @Autowired
    public CharactersClient(MarvelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Map<Integer, CharacterResult> getAll() {
        Map<Integer, CharacterResult> allCharacters = new HashMap<>();
        int offset = 0;
        int total;
//        try (FileWriter fw = new FileWriter("marvel.txt", true);
//             BufferedWriter bw = new BufferedWriter(fw);
//             PrintWriter out = new PrintWriter(bw)) {
            do {
                long startTime = System.currentTimeMillis();
                MarvelRequest request = MarvelRequest.builder()
                        .withPagination(offset, 100)
                        .withPaths("characters").build();
                CharactersResponse charactersResponse = this.client.get(request, CharactersResponse.class);
                CharacterData characterData = charactersResponse.getCharacterData();
                total = characterData.getTotal();
                offset += characterData.getCount();
                for (CharacterResult result : characterData.getResults()) {
                    allCharacters.put(result.getId(), result);
//                    out.println(objectMapper.writeValueAsString(result));
                }
                String fetchingMessage = new StringBuilder("Fetched ")
                        .append(offset).append(" of ").append(total)
                        .append(" characters in ").append(System.currentTimeMillis() - startTime)
                        .append(" milliseconds").toString();
                System.out.println(fetchingMessage);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    propagateIfPossible(e);
                }
            } while (offset < total);
//        } catch (IOException e) {
//            throw Throwables.propagate(e);
//        }


        return allCharacters;
    }
}
