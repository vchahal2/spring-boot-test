package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbWebTestApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SbWebTestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SbWebTestApplication.class, args);
	}
}
