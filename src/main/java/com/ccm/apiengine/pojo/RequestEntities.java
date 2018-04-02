package com.ccm.apiengine.pojo;

public class RequestEntities {

	private int limit;

    private boolean sentiment;

    private boolean emotion;

    public int getLimit ()
    {
        return limit;
    }

    public void setLimit (int limit)
    {
        this.limit = limit;
    }

	public boolean isSentiment() {
		return sentiment;
	}

	public void setSentiment(boolean sentiment) {
		this.sentiment = sentiment;
	}

	public boolean isEmotion() {
		return emotion;
	}

	public void setEmotion(boolean emotion) {
		this.emotion = emotion;
	}

	@Override
	public String toString() {
		return "RequestEntities [limit=" + limit + ", sentiment=" + sentiment + ", emotion=" + emotion + "]";
	}
}
