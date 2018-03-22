package com.example.demo.service;

import com.example.demo.pojo.UnprocessedText;

public interface SbService {

	public String getProcessedData(UnprocessedText unprocessedText);
	
	public String getAnalyzedData(UnprocessedText unprocessedText);
}
