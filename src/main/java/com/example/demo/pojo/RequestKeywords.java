package com.example.demo.pojo;

public class RequestKeywords {

	private int limit;
	
	private boolean emotion;
	
	private boolean sentiment;
	

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isEmotion() {
		return emotion;
	}

	public void setEmotion(boolean emotion) {
		this.emotion = emotion;
	}

	public boolean isSentiment() {
		return sentiment;
	}

	public void setSentiment(boolean sentiment) {
		this.sentiment = sentiment;
	}

	@Override
	public String toString() {
		return "RequestKeywords [limit=" + limit + ", emotion=" + emotion + ", sentiment=" + sentiment + "]";
	}
	
	
}
