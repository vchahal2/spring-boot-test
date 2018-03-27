package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CmsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CmsUtil.class);
	
	/*@Autowired
	RestTemplate restTemplate;*/
	
	public String getFlattenedContent(String contentId) {
		// here we will invoke CMS Endpoint to get Flattened content
		// TODO
		String hardCodedText = "IBM is an American multinational technology company headquartered in Armonk, New York, United States, with operations in over 170 countries";
		return hardCodedText;
	}
	
	
	
}
