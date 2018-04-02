package com.ccm.apiengine.pojo;

import java.io.Serializable;

public class ContentRequest implements Serializable {

	private static final long serialVersionUID = -3419819928919421780L;

	private String cmsFlattenedContentUrl;

	private String entitiesLimit;

	private String relevanceScoreForCategories;

	private String relevanceScoreForEntities;

	private String cmsNotificationUrl;

	private String categoriesLimit;

	private String cmsContentId;

	public String getCmsFlattenedContentUrl() {
		return cmsFlattenedContentUrl;
	}

	public void setCmsFlattenedContentUrl(String cmsFlattenedContentUrl) {
		this.cmsFlattenedContentUrl = cmsFlattenedContentUrl;
	}

	public String getEntitiesLimit() {
		return entitiesLimit;
	}

	public void setEntitiesLimit(String entitiesLimit) {
		this.entitiesLimit = entitiesLimit;
	}

	public String getRelevanceScoreForCategories() {
		return relevanceScoreForCategories;
	}

	public void setRelevanceScoreForCategories(String relevanceScoreForCategories) {
		this.relevanceScoreForCategories = relevanceScoreForCategories;
	}

	public String getRelevanceScoreForEntities() {
		return relevanceScoreForEntities;
	}

	public void setRelevanceScoreForEntities(String relevanceScoreForEntities) {
		this.relevanceScoreForEntities = relevanceScoreForEntities;
	}

	public String getCmsNotificationUrl() {
		return cmsNotificationUrl;
	}

	public void setCmsNotificationUrl(String cmsNotificationUrl) {
		this.cmsNotificationUrl = cmsNotificationUrl;
	}

	public String getCategoriesLimit() {
		return categoriesLimit;
	}

	public void setCategoriesLimit(String categoriesLimit) {
		this.categoriesLimit = categoriesLimit;
	}

	public String getCmsContentId() {
		return cmsContentId;
	}

	public void setCmsContentId(String cmsContentId) {
		this.cmsContentId = cmsContentId;
	}

	@Override
	public String toString() {
		return "ContentRequest [cmsFlattenedContentUrl = " + cmsFlattenedContentUrl + ", entitiesLimit = "
				+ entitiesLimit + ", relevanceScoreForCategories = " + relevanceScoreForCategories
				+ ", relevanceScoreForEntities = " + relevanceScoreForEntities + ", cmsNotificationUrl = "
				+ cmsNotificationUrl + ", categoriesLimit = " + categoriesLimit + ", cmsContentId = " + cmsContentId
				+ "]";
	}
}
