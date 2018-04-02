package com.ccm.apiengine.service;

import java.io.IOException;

import com.ccm.apiengine.pojo.ContentRequest;
import com.ccm.apiengine.pojo.UnprocessedText;

public interface ApiEngineService {

	public String getProcessedData(UnprocessedText unprocessedText);
	
	public String getAnalyzedData(UnprocessedText unprocessedText);
	
	public String processContentRequest(ContentRequest contentRequest);
	
	public String getCognitiveProfile(ContentRequest[] contentRequests) throws IOException, InterruptedException, Exception;
	
}
