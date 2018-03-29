package com.example.demo.pojo;

import java.util.HashMap;
import java.util.Map;

public class FlattenedContent {

	private String id;
	
	private String body;
	
	//private String[] media;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/*public String[] getMedia() {
		return media;
	}

	public void setMedia(String[] media) {
		this.media = media;
	}*/

	public Map<String, String> pojoToMap(){
		 Map<String, String> mapObject = new HashMap<String, String>();
		 mapObject.put("id", this.id);
		 mapObject.put("body", this.body);
		 return mapObject;
	}
	@Override
	public String toString() {
		return "FlattenedContent [id = " + id + ", body = " + body + "]";
	}
}
