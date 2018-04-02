package com.ccm.apiengine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccm.apiengine.pojo.ContentRequest;
import com.ccm.apiengine.pojo.UnprocessedText;
import com.ccm.apiengine.service.ApiEngineService;


@RestController
public class ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	ApiEngineService apiEngineService;


	@GetMapping("/")
	public String hello() {
		return "greetings from spring-boot application!";
	}
	
	@GetMapping("/newHello")
	public String newHello() {
		return "New Hello";
	}

	@PostMapping("/publishMessage")
	public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
		//messagingGateway.sendToPubsub(message);
		return new ResponseEntity<String>("Message Published and Consumed", HttpStatus.OK);
	}

	@RequestMapping(value = "/processMessage", method = RequestMethod.POST)
	public ResponseEntity<String> getProcessedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = apiEngineService.getProcessedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}

	@RequestMapping(value = "/analyzedMessage2", method = RequestMethod.POST)
	public ResponseEntity<String> getAnalyzedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = apiEngineService.getAnalyzedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/congnitiveProfile", method = RequestMethod.POST)
	public ResponseEntity<String> createCognitiveProfile(@RequestBody ContentRequest[] contentRequests) throws Exception {

		String resposne = "We have received request and processing it";
		LOGGER.info("starting the processing for cognitive profile");
		apiEngineService.getCognitiveProfile(contentRequests);
		//String res=  sbService;
		return new ResponseEntity<String>(resposne, HttpStatus.OK);
	}
	

}
