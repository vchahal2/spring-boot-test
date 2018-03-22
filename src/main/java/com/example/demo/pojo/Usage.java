package com.example.demo.pojo;

public class Usage
{
    private String text_characters;

    private String features;

    private String text_units;

    public String getText_characters ()
    {
        return text_characters;
    }

    public void setText_characters (String text_characters)
    {
        this.text_characters = text_characters;
    }

    public String getFeatures ()
    {
        return features;
    }

    public void setFeatures (String features)
    {
        this.features = features;
    }

    public String getText_units ()
    {
        return text_units;
    }

    public void setText_units (String text_units)
    {
        this.text_units = text_units;
    }

    @Override
    public String toString()
    {
        return "Usage [text_characters = "+text_characters+", features = "+features+", text_units = "+text_units+"]";
    }
}