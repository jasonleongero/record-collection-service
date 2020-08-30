package org.jasongero.recordcollectionservice.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jasongero.recordcollectionservice.dto.ArtistDto;
import org.jasongero.recordcollectionservice.entity.Artist;
import org.jasongero.recordcollectionservice.jsonPostRequestProcessor.JsonPostRequestProcessorForNewArtist;
import org.jasongero.recordcollectionservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArtistController extends BaseController {    
    @Autowired
    DozerBeanMapper dozerBeanMapper;
        
    @RequestMapping(value="/artists", method=GET)
    @ResponseBody
    public ResponseEntity<String> getArtists() {
        try {            
            List<ArtistDto> artistDtoList = new ArrayList<>();
            
            for (Artist artist : ArtistRepository.getArtists()) {
                artistDtoList.add(dozerBeanMapper.map(artist, ArtistDto.class));
            }
            
            return getStandardSuccessResponse(artistDtoList);
        } catch (Exception e) {
            return this.getErrorResponse();
        }
    }
    
    @RequestMapping(value="/artists/{artistId}", method=GET)
    @ResponseBody
    public ResponseEntity<String> getArtistDetails(@PathVariable("artistId") long artistId) {
        try {
            Artist artist;
            
            try {
                artist = ArtistRepository.getArtistById(artistId);
            } catch (EmptyResultDataAccessException e) {
                return getNotFoundResponse();
            }
            
            return getStandardSuccessResponse(dozerBeanMapper.map(artist, ArtistDto.class));
        } catch (Exception e) {
            return this.getErrorResponse();
        }
    }
    
    @RequestMapping(value="/artists", method=POST, consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createArtist(HttpEntity<String> httpEntity) {        
        try {
            JsonPostRequestProcessorForNewArtist.processJson(httpEntity.getBody());
            
            return getResourceCreatedResponse();
        } catch (Exception e) {
            return getErrorResponse();
        }
    }
}
