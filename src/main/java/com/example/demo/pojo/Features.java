package com.example.demo.pojo;

public class Features {

	private RequestKeywords keywords;

    private RequestEntities entities;

    public RequestKeywords getKeywords ()
    {
        return keywords;
    }

    public void setKeywords (RequestKeywords keywords)
    {
        this.keywords = keywords;
    }

    public RequestEntities getEntities ()
    {
        return entities;
    }

    public void setEntities (RequestEntities entities)
    {
        this.entities = entities;
    }

    @Override
    public String toString()
    {
        return "Features [keywords = "+keywords+", entities = "+entities+"]";
    }
}
