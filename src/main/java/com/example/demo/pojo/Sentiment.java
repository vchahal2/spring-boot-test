package com.example.demo.pojo;

public class Sentiment
{
    private String score;

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    @Override
    public String toString()
    {
        return "Sentiment [score = "+score+"]";
    }
}
