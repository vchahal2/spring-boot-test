package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.UnprocessedText;
import com.example.demo.service.SbService;

@RestController
public class ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	SbService sbService;

	@GetMapping("/")
	public String hello() {
		return "hello world from new web application!";
	}

	@RequestMapping(value = "/processMessage", method = RequestMethod.POST)
	public ResponseEntity<String> getProcessedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = sbService.getProcessedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}

	@RequestMapping(value = "/analyzedMessage", method = RequestMethod.POST)
	public ResponseEntity<String> getAnalyzedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = sbService.getAnalyzedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}

}
