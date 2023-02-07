package com.wisdomleaf.speakingclock.controller;

import com.wisdomleaf.speakingclock.common.Constants;
import com.wisdomleaf.speakingclock.common.WisdomLeafResponseBody;
import com.wisdomleaf.speakingclock.dto.SpeakingClockRequestDTO;
import com.wisdomleaf.speakingclock.dto.SpeakingClockResponseDTO;
import com.wisdomleaf.speakingclock.service.ISpeakingClockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.BASE_URL)
public class SpeakingClockController extends WisdomLeafBaseController{

    private final Logger logger = LoggerFactory.getLogger(SpeakingClockController.class);

    @Autowired
    private ISpeakingClockService iSpeakingClockService;

    @RequestMapping(value    = Constants.SPEAKING_CLOCK,
                    method   = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WisdomLeafResponseBody> getTimeinWords(@RequestBody SpeakingClockRequestDTO speakingClockRequestDTO) {
        try {
            SpeakingClockResponseDTO speakingClockResponseDTO = iSpeakingClockService.convertTimeInWords(speakingClockRequestDTO);
            return super.prepareSuccessResponse(speakingClockResponseDTO,
                                                HttpStatus.OK,
                                                Constants.SUCCESS);
        }catch(Exception e) {
            return prepareErrorResponse(Constants.GENERIC_ERROR_MSG,
                                        e,
                                        HttpStatus.BAD_REQUEST,
                                        Constants.FAILURE,
                                        Constants.GENERIC_ERROR_CODE);
        }
    }
}
