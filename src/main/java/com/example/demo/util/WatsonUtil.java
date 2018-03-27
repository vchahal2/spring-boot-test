package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.ContentRequest;
import com.example.demo.pojo.UnprocessedText;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;

@Component
public class WatsonUtil {

	@Value("${watson.nlu.api.url}")
	private String watsonNLUAPIUrl;
	
	/*@Value("${ibm.developer.account.user.name}")
	private String ibmAccountUserName;
	
	
	@Value("${ibm.developer.account.password}")
	private String ibmAccountPassword;*/
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WatsonUtil.class);

	public String processMessage(String rawInput) {
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, "d7c25a66-a74a-4d20-848f-fe52238491c4",
				"Y1F2bSIaug37");

		/*String text = "IBM is an American multinational technology " + "company headquartered in Armonk, New York, "
				+ "United States, with operations in over 170 countries.";*/

		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().emotion(true).sentiment(true).limit(4).build();

		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder().emotion(true).sentiment(true).limit(4).build();

		Features features = new Features.Builder().entities(entitiesOptions).keywords(keywordsOptions).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(rawInput).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();
		//System.out.println(response);
		return response.toString();
	}
	
	public String analyzeMessage(UnprocessedText unprocessedText, String ibmAccountUserName, String ibmAccountPassword) {
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, ibmAccountUserName, ibmAccountPassword);

		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().emotion(unprocessedText.getFeatures().getEntities().isEmotion()).sentiment(unprocessedText.getFeatures().getEntities().isSentiment()).
				limit(unprocessedText.getFeatures().getEntities().getLimit()).build();

		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder().emotion(unprocessedText.getFeatures().getKeywords().isEmotion()).sentiment(unprocessedText.getFeatures().getKeywords().isSentiment()).
				limit(unprocessedText.getFeatures().getKeywords().getLimit()).build();

		Features features = new Features.Builder().entities(entitiesOptions).keywords(keywordsOptions).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(unprocessedText.getText()).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();
		return response.toString();
	}
	
	public static String getTextMetadata(String flattenedContent, ContentRequest contentRequest, String ibmAccountUserName, String ibmAccountPassword) {
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, ibmAccountUserName, ibmAccountPassword);

		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder().emotion(true).sentiment(true).
				limit(Integer.parseInt(contentRequest.getEntitiesLimit())).build();

		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder().emotion(true).sentiment(true).
				limit(Integer.parseInt(contentRequest.getCategoriesLimit())).build();

		Features features = new Features.Builder().entities(entitiesOptions).keywords(keywordsOptions).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(flattenedContent).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();
		return response.toString();
	}
}
