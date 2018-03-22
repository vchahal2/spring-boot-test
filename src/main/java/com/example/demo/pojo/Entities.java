package com.example.demo.pojo;

public class Entities {
	private Sentiment sentiment;

	private Disambiguation disambiguation;

	private String text;

	private String count;

	private String relevance;

	private String type;

	private Emotion emotion;

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
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
