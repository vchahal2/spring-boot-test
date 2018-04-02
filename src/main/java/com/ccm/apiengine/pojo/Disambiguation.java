package com.ccm.apiengine.pojo;

import java.util.Arrays;
import com.google.gson.annotations.SerializedName;

public class Disambiguation {
	
	@SerializedName("subtype")
	private String[] subType;

	private String name;

	@SerializedName("dbpedia_resource")
	private String dbpediaResource;

	
	public String[] getSubType() {
		return subType;
	}

	public void setSubType(String[] subType) {
		this.subType = subType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbpediaResource() {
		return dbpediaResource;
	}

	public void setDbpediaResource(String dbpediaResource) {
		this.dbpediaResource = dbpediaResource;
	}

	@Override
	public String toString() {
		return "Disambiguation [subType=" + Arrays.toString(subType) + ", name=" + name + ", dbpediaResource="
				+ dbpediaResource + "]";
	}

}
