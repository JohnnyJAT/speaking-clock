package com.wisdomleaf.speakingclock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisdomleaf.speakingclock.common.Constants;
import com.wisdomleaf.speakingclock.dto.SpeakingClockRequestDTO;
import com.wisdomleaf.speakingclock.service.ISpeakingClockService;

import lombok.var;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SpeakingClockControllerTest {

        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private ISpeakingClockService iSpeakingClockService;
        @Test
        public void testPostRequestToSpeakingClockServiceWithCorrectInput() throws Exception {
            SpeakingClockRequestDTO speakingClockRequestDTO = new SpeakingClockRequestDTO();
            speakingClockRequestDTO.setInputTimeInHoursMinutes("13:47");

            var result = mockMvc.perform( MockMvcRequestBuilders
                            .post(Constants.BASE_URL+Constants.SPEAKING_CLOCK)
                            .content(new ObjectMapper().writeValueAsString(speakingClockRequestDTO))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            Assertions.assertNotNull(result);
        }
}
