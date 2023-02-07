package com.wisdomleaf.speakingclock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.speakingclock.common.WisdomLeafResponseBody;
import com.wisdomleaf.speakingclock.exception.WisdomLeafServiceException;

@RestController
public class WisdomLeafBaseController {

    private final Logger logger = LoggerFactory.getLogger(WisdomLeafBaseController.class);

    public ResponseEntity<WisdomLeafResponseBody> prepareSuccessResponse(Object responseObject, HttpStatus httpStatus, String message) {
        WisdomLeafResponseBody wisdomLeafResponseBody = new WisdomLeafResponseBody();
        wisdomLeafResponseBody.setData(responseObject);
        wisdomLeafResponseBody.setSuccess(true);
        wisdomLeafResponseBody.setStatusMsg(message);
        return new ResponseEntity<>(wisdomLeafResponseBody, httpStatus);
    }

    public ResponseEntity<WisdomLeafResponseBody> prepareErrorResponse(String errMsg, Object exceptionObject, HttpStatus httpStatus, String message, String errorCode) {
        WisdomLeafResponseBody wisdomLeafResponseBody = new WisdomLeafResponseBody();

        if (exceptionObject instanceof WisdomLeafServiceException){
            wisdomLeafResponseBody.setErrorMsg(((WisdomLeafServiceException) exceptionObject).getMessage());
            wisdomLeafResponseBody.setData(null);
            wisdomLeafResponseBody.setErrorCode(errorCode);
        }
        else{
            wisdomLeafResponseBody.setErrorMsg(errMsg);
            wisdomLeafResponseBody.setData(null);
            wisdomLeafResponseBody.setErrorCode(errorCode);
        }
        wisdomLeafResponseBody.setSuccess(false);
        wisdomLeafResponseBody.setStatusMsg(message);
        return new ResponseEntity<>(wisdomLeafResponseBody, httpStatus);
    }
}
