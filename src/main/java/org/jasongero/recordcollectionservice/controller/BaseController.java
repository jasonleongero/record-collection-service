package org.jasongero.recordcollectionservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class BaseController {
    private static ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    
    protected ResponseEntity<String> getStandardSuccessResponse(Object responseObject) throws JsonProcessingException {
        return ResponseEntity.ok().headers(getCommonHeaders()).body(objectWriter.writeValueAsString(responseObject));
    }
    
    protected ResponseEntity<String> getResourceCreatedResponse() {
        return ResponseEntity.status(HttpStatus.CREATED).headers(getCommonHeaders()).body("");
    }
    
    protected ResponseEntity<String> getNotFoundResponse() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(getCommonHeaders()).body("");
    }
    
    protected ResponseEntity<String> getErrorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getCommonHeaders()).body("");
    }
    
    private static HttpHeaders getCommonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        
        return httpHeaders;
    }
}
