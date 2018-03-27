package com.example.demo.controller;

import java.io.IOException;

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
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.pojo.ContentRequest;
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

	@PostMapping("/publishMessage")
	public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
		//messagingGateway.sendToPubsub(message);
		return new ResponseEntity<String>("Message Published and Consumed", HttpStatus.OK);
	}

	@RequestMapping(value = "/processMessage", method = RequestMethod.POST)
	public ResponseEntity<String> getProcessedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = sbService.getProcessedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}

	@RequestMapping(value = "/analyzedMessage2", method = RequestMethod.POST)
	public ResponseEntity<String> getAnalyzedMessage(@RequestBody UnprocessedText unprocessedText) {

		String processedText = sbService.getAnalyzedData(unprocessedText);

		return new ResponseEntity<String>(processedText, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/congnitiveProfile", method = RequestMethod.POST)
	public ResponseEntity<String> createCognitiveProfile(@RequestBody ContentRequest[] contentRequests) throws Exception {

		String resposne = "We have received request and processing it";
		System.out.println("Inside cognitiveProfile Method");
		sbService.getCognitiveProfile(contentRequests);
		//String res=  sbService;
		return new ResponseEntity<String>(resposne, HttpStatus.OK);
	}
	

}
