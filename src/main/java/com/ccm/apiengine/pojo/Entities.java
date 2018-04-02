package com.ccm.apiengine.pojo;

public class Entities {
	
	private String type;
	private String text;
	private Sentiment sentiment;
	private double relevance;
	private Emotion emotion;
	private Disambiguation disambiguation;
	private int count;

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	public Disambiguation getDisambiguation() {
		return disambiguation;
	}

	public void setDisambiguation(Disambiguation disambiguation) {
		this.disambiguation = disambiguation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	@Override
	public String toString() {
		return "Entities [sentiment = " + sentiment + ", disambiguation = " + disambiguation + ", text = " + text
				+ ", count = " + count + ", relevance = " + relevance + ", type = " + type + ", emotion = " + emotion
				+ "]";
	}
}
