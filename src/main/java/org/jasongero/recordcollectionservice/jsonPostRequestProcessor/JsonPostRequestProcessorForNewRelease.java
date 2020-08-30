package org.jasongero.recordcollectionservice.jsonPostRequestProcessor;

import org.jasongero.recordcollectionservice.repository.FormatRepository;
import org.jasongero.recordcollectionservice.repository.ReleaseRepository;
import org.jasongero.recordcollectionservice.repository.TrackRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPostRequestProcessorForNewRelease {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static void processJson(String requestBody, long masterReleaseId) throws Exception {
        JsonNode mainJsonNode = objectMapper.readTree(requestBody);
        long trackIndex = 1;
        long newReleaseId;
        
        String title = mainJsonNode.get("title").asText();
        long releaseMonth = mainJsonNode.get("releaseMonth").asLong();
        long releaseDay = mainJsonNode.get("releaseDay").asLong();
        long releaseYear = mainJsonNode.get("releaseYear").asLong();
        String catalogNumber = mainJsonNode.get("catalogNumber").asText();
        long recordLabelId = mainJsonNode.get("recordLabelId").asLong();
        String countryIsoCode = mainJsonNode.get("countryIsoCode").asText();
        String imageUrl = mainJsonNode.get("imageUrl").asText();
        boolean isKey = mainJsonNode.get("isKey").asBoolean();
        
        newReleaseId = ReleaseRepository.createRelease(
             masterReleaseId,
             title,
             releaseMonth,
             releaseDay,
             releaseYear,
             catalogNumber,
             recordLabelId,
             countryIsoCode,
             imageUrl,
             isKey
        );
        
        JsonNode tracksNode = mainJsonNode.get("tracks");
        
        for (JsonNode track : tracksNode) {
            String trackTitle = track.get("title").asText();
            String trackNumber = track.get("trackNumber").asText();
            long duration = track.get("duration").asLong();
            
            TrackRepository.createTrack(newReleaseId, trackIndex, trackNumber, duration, trackTitle);
            
            trackIndex++;
        }
        
        JsonNode formatsNode = mainJsonNode.get("formats");
        
        for (JsonNode format : formatsNode) {
            String formatName = format.get("formatName").asText();
            long quantity = format.get("quantity").asLong();
            String notes = format.get("notes").asText();
            
            FormatRepository.createFormat(newReleaseId, formatName, quantity, notes);
        }
    }
}
