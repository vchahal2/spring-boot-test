package com.ccm.apiengine.pojo;

import java.io.Serializable;

public class UnprocessedText implements Serializable{

	private static final long serialVersionUID = 2904263187594558892L;

	private String text;

	private Features features;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "UnprocessedText [text = " + text + ", features = " + features + "]";
	}

}
