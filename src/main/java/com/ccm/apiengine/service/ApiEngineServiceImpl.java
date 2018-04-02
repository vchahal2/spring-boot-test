package com.ccm.apiengine.service;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.ccm.apiengine.messaging.ContentIdTopicPublisher;
import com.ccm.apiengine.messaging.ContentIdTopicSubscriber;
import com.ccm.apiengine.messaging.FilteredResponseTopicSubscriber;
import com.ccm.apiengine.messaging.FlattenedContentTopicSubscriber;
import com.ccm.apiengine.messaging.UnfilteredResponseTopicSubscriber;
import com.ccm.apiengine.pojo.ContentRequest;
import com.ccm.apiengine.pojo.UnprocessedText;
import com.ccm.apiengine.util.WatsonUtil;

@Service("apiEngineService")
public class ApiEngineServiceImpl implements ApiEngineService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiEngineServiceImpl.class);
	
	@Autowired
	WatsonUtil watsonUtil;
	
	/*@Autowired
	RestTemplate restTemplate;*/
	
	@Autowired
	ContentIdTopicPublisher contentIdTopicPublisher;
	
	@Autowired
	ContentIdTopicSubscriber contentIdTopicSubscriber;
	
	@Autowired
	FilteredResponseTopicSubscriber filteredResponseTopicSubscriber;
	
	@Autowired
	UnfilteredResponseTopicSubscriber unfilteredResponseTopicSubscriber;
	
	@Autowired
	FlattenedContentTopicSubscriber flattenedContentTopicSubscriber;
	
	
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
	public String getCognitiveProfile(ContentRequest[] contentRequests) throws Exception {
		
		contentIdTopicSubscriber.startListener();
		flattenedContentTopicSubscriber.startListener();
		unfilteredResponseTopicSubscriber.startListener();
		filteredResponseTopicSubscriber.startListener();
		
		contentIdTopicPublisher.publishContentRequests(contentRequests);
		return null;
	}
	
	
	
	
}
