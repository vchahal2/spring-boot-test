package com.example.demo.pojo;

public class Emotion
{
    private String disgust;

    private String sadness;

    private String joy;

    private String anger;

    private String fear;

    public String getDisgust ()
    {
        return disgust;
    }

    public void setDisgust (String disgust)
    {
        this.disgust = disgust;
    }

    public String getSadness ()
    {
        return sadness;
    }

    public void setSadness (String sadness)
    {
        this.sadness = sadness;
    }

    public String getJoy ()
    {
        return joy;
    }

    public void setJoy (String joy)
    {
        this.joy = joy;
    }

    public String getAnger ()
    {
        return anger;
    }

    public void setAnger (String anger)
    {
        this.anger = anger;
    }

    public String getFear ()
    {
        return fear;
    }

    public void setFear (String fear)
    {
        this.fear = fear;
    }

    @Override
    public String toString()
    {
        return "Emotion [disgust = "+disgust+", sadness = "+sadness+", joy = "+joy+", anger = "+anger+", fear = "+fear+"]";
    }
}