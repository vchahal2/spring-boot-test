package com.example.demo.service;

import java.io.IOException;

import com.example.demo.pojo.ContentRequest;
import com.example.demo.pojo.UnprocessedText;

public interface SbService {

	public String getProcessedData(UnprocessedText unprocessedText);
	
	public String getAnalyzedData(UnprocessedText unprocessedText);
	
	public String processContentRequest(ContentRequest contentRequest);
	
	public String getCognitiveProfile(ContentRequest[] contentRequests) throws IOException, InterruptedException, Exception;
	
}
