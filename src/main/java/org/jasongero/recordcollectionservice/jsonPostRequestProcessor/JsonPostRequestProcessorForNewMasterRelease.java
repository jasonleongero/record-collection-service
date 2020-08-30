package org.jasongero.recordcollectionservice.jsonPostRequestProcessor;

import org.jasongero.recordcollectionservice.repository.MasterReleaseRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPostRequestProcessorForNewMasterRelease {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static void processJson(String requestBody, long artistId) throws Exception {
        JsonNode mainJsonNode;
        JsonNode masterReleaseTitleNode;
        
        mainJsonNode = objectMapper.readTree(requestBody);
        masterReleaseTitleNode = mainJsonNode.get("masterReleaseTitle");
        
        MasterReleaseRepository.createMasterRelease(masterReleaseTitleNode.textValue(), artistId);
    }
}
