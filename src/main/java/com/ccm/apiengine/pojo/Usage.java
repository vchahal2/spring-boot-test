package com.ccm.apiengine.pojo;

import com.google.gson.annotations.SerializedName;

public class Usage
{
	@SerializedName("text_characters")
    private String textCharacters;

    private String features;
    
    @SerializedName("text_units")
    private String textUnits;

    
	public String getTextCharacters() {
		return textCharacters;
	}

	public void setTextCharacters(String textCharacters) {
		this.textCharacters = textCharacters;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTextUnits() {
		return textUnits;
	}

	public void setTextUnits(String textUnits) {
		this.textUnits = textUnits;
	}

	@Override
	public String toString() {
		return "Usage [textCharacters=" + textCharacters + ", features=" + features + ", textUnits=" + textUnits + "]";
	}

}