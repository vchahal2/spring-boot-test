package com.ccm.apiengine.pojo;

public class Categories {

	private double score;
	private String label;
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "Categories [score=" + score + ", label=" + label + "]";
	}
	
}
