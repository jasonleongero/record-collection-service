package org.jasongero.recordcollectionservice.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jasongero.recordcollectionservice.dto.MasterReleaseDto;
import org.jasongero.recordcollectionservice.entity.MasterRelease;
import org.jasongero.recordcollectionservice.jsonPostRequestProcessor.JsonPostRequestProcessorForNewMasterRelease;
import org.jasongero.recordcollectionservice.repository.MasterReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MasterReleaseController extends BaseController {
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    @RequestMapping(value="/artists/{artistId}/master-releases", method=GET)
    @ResponseBody
    public ResponseEntity<String> getMasterReleasesByArtist(
        @PathVariable("artistId") long artistId
    ) {
        try {
            List<MasterReleaseDto> masterReleaseDtoList = new ArrayList<>();
            
            for (MasterRelease masterRelease : MasterReleaseRepository.getMasterReleasesByArtist(artistId)) {
                masterReleaseDtoList.add(dozerBeanMapper.map(masterRelease, MasterReleaseDto.class));
            }
            
            return getStandardSuccessResponse(masterReleaseDtoList);
        } catch (Exception e) {
            return getErrorResponse();
        }
    }
    
    @RequestMapping(value="/master-releases/{masterReleaseId}", method=GET)
    @ResponseBody
    public ResponseEntity<String> getMasterReleaseDetails(
        @PathVariable("masterReleaseId") long masterReleaseId
    ) {
        try {
            MasterRelease masterRelease;
            
            try {
                masterRelease = MasterReleaseRepository.getMasterRelease(masterReleaseId);
            } catch (EmptyResultDataAccessException e) {
                return getNotFoundResponse();
            }
            
            return getStandardSuccessResponse(dozerBeanMapper.map(masterRelease, MasterReleaseDto.class));
        } catch (Exception e) {
            return this.getErrorResponse();
        }
    }
    
    @RequestMapping(value="/artists/{artistId}/master-releases", method=POST, consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createMasterRelease(
        HttpEntity<String> httpEntity,
        @PathVariable("artistId") long artistId
    ) {
        try {            
            JsonPostRequestProcessorForNewMasterRelease.processJson(httpEntity.getBody(), artistId);
            
            return getResourceCreatedResponse();
        } catch (Exception e) {
            return getErrorResponse();
        }
    }
}
