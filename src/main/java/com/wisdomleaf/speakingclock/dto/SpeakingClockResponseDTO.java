package com.wisdomleaf.speakingclock.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpeakingClockResponseDTO {
    private String inputTimeInWords;
    private String currentTimeInWords;
}
