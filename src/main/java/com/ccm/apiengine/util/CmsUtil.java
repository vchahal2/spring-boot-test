package com.ccm.apiengine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccm.apiengine.pojo.FlattenedContent;

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
	
	public FlattenedContent getFlattenedContentObject(String contentId) {
		// here we will invoke CMS Endpoint to get Flattened content
		// TODO 
		// Here assuming that we will get a Flattened Object from CMS. Using hard codes pojo currently
		FlattenedContent flattenedContent = new FlattenedContent();
		flattenedContent.setId("123");
		flattenedContent.setBody("IBM is an American multinational technology company headquartered in Armonk, New York, United States, with operations in over 170 countries");
		//String hardCodedText = "IBM is an American multinational technology company headquartered in Armonk, New York, United States, with operations in over 170 countries";
		return flattenedContent;
	}
	public String postCognitiveMetadata(Object cognitiveProfile) {
		//TODO
		String hardCodedCognitiveMetaData = "This is test cognitiveMetaData";
		return hardCodedCognitiveMetaData;
	}
	
	public String restTemplateTest() {
		return null;	
	}
	
	
}
