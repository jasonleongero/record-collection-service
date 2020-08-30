package org.jasongero.recordcollectionservice.jsonPostRequestProcessor;

import org.jasongero.recordcollectionservice.repository.ArtistRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPostRequestProcessorForNewArtist {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static void processJson(String requestBody) throws Exception {
        JsonNode mainJsonNode;
        JsonNode artistNameNode;
        
        mainJsonNode = objectMapper.readTree(requestBody);
        artistNameNode = mainJsonNode.get("artistName");
        
        ArtistRepository.createArtist(artistNameNode.textValue());
    }
}
