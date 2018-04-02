package com.ccm.apiengine.pojo;

import com.ccm.apiengine.pojo.Entities;
import com.ccm.apiengine.pojo.Keywords;
import com.ccm.apiengine.pojo.Usage;

public class AnalyzedText {

	private Keywords[] keywords;

	private Usage usage;

	private String language;

	private Entities[] entities;

	public Keywords[] getKeywords() {
		return keywords;
	}

	public void setKeywords(Keywords[] keywords) {
		this.keywords = keywords;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Entities[] getEntities() {
		return entities;
	}

	public void setEntities(Entities[] entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "AnalyzedText [keywords = " + keywords + ", usage = " + usage + ", language = " + language + ", entities = "
				+ entities + "]";
	}
}