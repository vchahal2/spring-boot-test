package com.example.demo.service;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.messaging.TopicPublisher;
import com.example.demo.messaging.TopicSubscriber;
import com.example.demo.pojo.AnalyzedText;
import com.example.demo.pojo.ContentRequest;
import com.example.demo.pojo.UnprocessedText;
import com.example.demo.util.WatsonUtil;

@Service("sbservice")
public class SbServiceImpl implements SbService{

	private static final Logger LOGGER = LoggerFactory.getLogger(SbServiceImpl.class);
	
	@Autowired
	WatsonUtil watsonUtil;
	
	/*@Autowired
	RestTemplate restTemplate;*/
	
	@Autowired
	TopicSubscriber topicSubscriber;
	
	@Autowired
	TopicPublisher topicPublisher;
	
	@Value("${watson.nlu.api.url}")
	private String watsonNLUAPIUrl;
	
	@Value("${ibm.developer.account.user.name}")
	private String ibmAccountUserName;
	
	
	@Value("${ibm.developer.account.password}")
	private String ibmAccountPassword;
	
	
	public String getProcessedData(UnprocessedText unprocessedText) {
		//String processsedPayload = null;
		
		HttpHeaders headers = createHeaders();
		
		HttpEntity request = new HttpEntity(unprocessedText, headers);
		//ResponseEntity<AnalyzedText> response = restTemplate.exchange(watsonNLUAPIUrl, HttpMethod.POST, request, AnalyzedText.class);
		//AnalyzedText response2 = restTemplate.postForObject(watsonNLUAPIUrl, request, AnalyzedText.class);
		//return response2.toString();
		return null;
		
	}


	public String getAnalyzedData(UnprocessedText unprocessedText) {
		
		return watsonUtil.analyzeMessage(unprocessedText, ibmAccountUserName, ibmAccountPassword);
	}
	
	public String processContentRequest(ContentRequest contentRequest) {
		return null;
		
	}
	
	private HttpHeaders createHeaders() {
		String plainCreds = ibmAccountUserName + ":" + ibmAccountPassword;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}


	@Override
	public String getCognitiveProfile(ContentRequest[] contentRequests) throws IOException, InterruptedException {
		topicSubscriber.startListener();
		
		topicPublisher.publishContentRequests(contentRequests); 
		return null;
	}
	
	
	
	
}
