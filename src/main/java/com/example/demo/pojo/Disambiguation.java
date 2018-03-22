package com.example.demo.pojo;

public class Disambiguation {
	private String[] subtype;

	private String name;

	private String dbpedia_resource;

	public String[] getSubtype() {
		return subtype;
	}

	public void setSubtype(String[] subtype) {
		this.subtype = subtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbpedia_resource() {
		return dbpedia_resource;
	}

	public void setDbpedia_resource(String dbpedia_resource) {
		this.dbpedia_resource = dbpedia_resource;
	}

	@Override
	public String toString() {
		return "Disambiguation [subtype = " + subtype + ", name = " + name + ", dbpedia_resource = " + dbpedia_resource
				+ "]";
	}
}
