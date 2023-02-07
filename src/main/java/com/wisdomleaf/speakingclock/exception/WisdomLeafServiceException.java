package com.wisdomleaf.speakingclock.exception;

public class WisdomLeafServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public WisdomLeafServiceException(String message) {
        super(message);
    }

    public WisdomLeafServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

