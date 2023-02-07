package com.wisdomleaf.speakingclock.service;

import com.wisdomleaf.speakingclock.dto.*;

public interface ISpeakingClockService {
    SpeakingClockResponseDTO convertTimeInWords(SpeakingClockRequestDTO speakingClockRequestDTO);
}
