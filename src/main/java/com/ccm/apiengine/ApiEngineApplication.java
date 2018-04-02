package com.ccm.apiengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiEngineApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiEngineApplication.class);
	
	public static void main(String[] args) {
		LOGGER.debug("starting API Engine application");
		SpringApplication.run(ApiEngineApplication.class, args);
	}
}
