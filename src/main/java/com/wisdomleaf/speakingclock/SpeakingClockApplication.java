package com.wisdomleaf.speakingclock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpeakingClockApplication implements CommandLineRunner{

	private final Logger logger = LoggerFactory.getLogger(SpeakingClockApplication.class);

	@Value("${app.env}")
	private String env;
	public static void main(String[] args) {
		SpringApplication.run(SpeakingClockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Current Application Environment : "+env);
	}

}
