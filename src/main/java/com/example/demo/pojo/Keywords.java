package com.example.demo.pojo;

public class Keywords {
	
	private Sentiment sentiment;
	private String text;
	private String relevance;
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

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
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