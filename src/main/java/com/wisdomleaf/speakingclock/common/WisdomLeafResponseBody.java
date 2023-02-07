package com.wisdomleaf.speakingclock.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WisdomLeafResponseBody {
    private Boolean             success;
    private String              statusMsg;
    private String              errorCode;
    private String              errorMsg;
    private Object              data;
}
