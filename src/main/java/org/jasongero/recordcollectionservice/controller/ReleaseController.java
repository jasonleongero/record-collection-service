package org.jasongero.recordcollectionservice.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jasongero.recordcollectionservice.dto.ReleaseDetailDto;
import org.jasongero.recordcollectionservice.dto.ReleaseDto;
import org.jasongero.recordcollectionservice.dto.ReleaseWithArtistDto;
import org.jasongero.recordcollectionservice.entity.Release;
import org.jasongero.recordcollectionservice.entity.ReleaseWithArtist;
import org.jasongero.recordcollectionservice.jsonPostRequestProcessor.JsonPostRequestProcessorForNewRelease;
import org.jasongero.recordcollectionservice.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReleaseController extends BaseController {    
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    @RequestMapping(value="/releases", method=GET)
    @ResponseBody
    public ResponseEntity<String> getReleases() {
        try {
            List<ReleaseWithArtistDto> releaseWithArtistDtoList = new ArrayList<>();
            
            for (ReleaseWithArtist releaseWithArtist : ReleaseRepository.getReleases()) {
                releaseWithArtistDtoList.add(dozerBeanMapper.map(releaseWithArtist, ReleaseWithArtistDto.class));
            }
            
            return getStandardSuccessResponse(releaseWithArtistDtoList);
        } catch (Exception e) {
            return this.getErrorResponse();
        }
    }
    
    @RequestMapping(value="/master-releases/{masterReleaseId}/releases", method=GET)
    @ResponseBody
    public ResponseEntity<String> getReleasesByMasterRelease(
        @PathVariable("masterReleaseId") long masterReleaseId
    ) {
        try {
            List<ReleaseDto> releaseDtoList = new ArrayList<>();
            
            for (Release release: ReleaseRepository.getReleasesByMasterRelease(masterReleaseId)) {
                releaseDtoList.add(dozerBeanMapper.map(release, ReleaseDto.class));
            }
            
            return getStandardSuccessResponse(releaseDtoList);
        } catch (Exception e) {
            return getErrorResponse();
        }
    }
    
    @RequestMapping(value="/releases/{releaseId}", method=GET)
    @ResponseBody
    public ResponseEntity<String> getRelease(
            @PathVariable("releaseId") long releaseId
    ) {
        try {
            Release release;
            
            try {
                release = ReleaseRepository.getRelease(releaseId);
            } catch (EmptyResultDataAccessException e) {
                return getNotFoundResponse();
            }

            return getStandardSuccessResponse(dozerBeanMapper.map(release, ReleaseDetailDto.class));
        } catch (Exception e) {
            return this.getErrorResponse();
        }
    }
    
    @RequestMapping(value="/master-releases/{masterReleaseId}/releases", method=POST, consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createRelease(
        HttpEntity<String> httpEntity,
        @PathVariable("masterReleaseId") long masterReleaseId
    ) {        
        try {            
            JsonPostRequestProcessorForNewRelease.processJson(httpEntity.getBody(), masterReleaseId);
            
            return getResourceCreatedResponse();
        } catch (Exception e) {
            return getErrorResponse();
        }
    }
}
