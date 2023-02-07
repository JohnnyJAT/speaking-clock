package com.wisdomleaf.speakingclock.service.impl;

import com.wisdomleaf.speakingclock.common.Constants;
import com.wisdomleaf.speakingclock.exception.WisdomLeafServiceException;
import com.wisdomleaf.speakingclock.service.ISpeakingClockService;
import com.wisdomleaf.speakingclock.dto.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "ISpeakingClockService")
public class SpeakingClockServiceImpl implements ISpeakingClockService {

    private final Logger logger = LoggerFactory.getLogger(SpeakingClockServiceImpl.class);

    @Override
    public SpeakingClockResponseDTO convertTimeInWords(SpeakingClockRequestDTO speakingClockRequestDTO){

        try {
            Date currDate                   = new Date();
            String currentTime              = String.valueOf(currDate.getHours()) + ":" + String.valueOf(currDate.getMinutes());
            String regexFor24HrTimeFormat   = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
            Pattern pattern                 = Pattern.compile(regexFor24HrTimeFormat);

            SpeakingClockResponseDTO speakingClockResponseDTO = new SpeakingClockResponseDTO();
            //check if the input time is not null AND if it macthes the 24 Hr time format
            if (speakingClockRequestDTO.getInputTimeInHoursMinutes() != null
                    &&
                    pattern.matcher(speakingClockRequestDTO.getInputTimeInHoursMinutes()).matches()) {
                //split the input time based on ":"
                String[] splittedInputTime      = speakingClockRequestDTO.getInputTimeInHoursMinutes().split(":");
                //split the current time based on ":"
                String[] splittedCurrentTime    = currentTime.split(":");

                speakingClockResponseDTO.setInputTimeInWords(timeInwords(splittedInputTime));
                speakingClockResponseDTO.setCurrentTimeInWords(timeInwords(splittedCurrentTime));

            } else{
                //split the current time based on ":"
                String[] splittedCurrentTime = currentTime.split(":");
                //when input itme not available, return current time in words
                speakingClockResponseDTO.setCurrentTimeInWords(timeInwords(splittedCurrentTime));
            }
            return speakingClockResponseDTO;
        }
        catch (Exception exception){
            logger.info(exception.getLocalizedMessage());
            throw new WisdomLeafServiceException(Constants.GENERIC_ERROR_MSG);
        }
    }

    private String timeInwords(String[] splittedTime){

        Map<Integer, String> HOURS      = new HashMap<>();
        HOURS.put(0,  "twelve");
        HOURS.put(1,  "one");
        HOURS.put(2,  "two");
        HOURS.put(3,  "three");
        HOURS.put(4,  "four");
        HOURS.put(5,  "five");
        HOURS.put(6,  "six");
        HOURS.put(7,  "seven");
        HOURS.put(8,  "eight");
        HOURS.put(9,  "nine");
        HOURS.put(10, "ten");
        HOURS.put(11, "eleven");

        Map<Integer, String> ONES       = new HashMap<>();
        ONES.put(0,  "");
        ONES.put(1,  "one");
        ONES.put(2,  "two");
        ONES.put(3,  "three");
        ONES.put(4,  "four");
        ONES.put(5,  "five");
        ONES.put(6,  "six");
        ONES.put(7,  "seven");
        ONES.put(8,  "eight");
        ONES.put(9,  "nine");

        Map<Integer, String> TENS       = new HashMap<>();
        TENS.put(0,  "");
        TENS.put(1,  "");
        TENS.put(2,  "twenty");
        TENS.put(3,  "thirty");
        TENS.put(4,  "fourty");
        TENS.put(5,  "fifty");

        Map<Integer, String> TEENS      = new HashMap<>();
        TEENS.put(0,  "ten");
        TEENS.put(1,  "eleven");
        TEENS.put(2,  "twelve");
        TEENS.put(3,  "thirteen");
        TEENS.put(4,  "fourteen");
        TEENS.put(5,  "fifteen");
        TEENS.put(6,  "sixteen");
        TEENS.put(7,  "seventeen");
        TEENS.put(8,  "eighteenteen");
        TEENS.put(9,  "nineteen");

        int hour            = Integer.parseInt(splittedTime[0]);
        String hourInWords  = HOURS.get(hour % 12);

        int minutes = Integer.parseInt(splittedTime[1]);
        String minutesInWords = "";
        if (minutes < 10){
            minutesInWords = "o' " + ONES.get(minutes);
        } else if (minutes < 20) {
            minutesInWords = "o' " + TEENS.get(minutes % 10);
        } else {
            minutesInWords = "o' " + TENS.get(minutes / 10) + " " + ONES.get(minutes % 10);
        }

        String meridianOfTheDay = hour < 12 ? "AM" : "PM";

        String timeInwords = "It's " + " " + hourInWords + " " + minutesInWords + " " + meridianOfTheDay;
        return timeInwords;
    }
}
