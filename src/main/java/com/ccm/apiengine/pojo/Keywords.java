package com.ccm.apiengine.pojo;

public class Keywords {
	
	private String text;
	private Sentiment sentiment;
	private double relevance;
	private Emotion emotion;

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

    
	@Override
	public String toString() {
		return "Keywords [sentiment = " + sentiment + ", text = " + text + ", relevance = " + relevance
				+ ", emotion = " + emotion + "]";
	}
}