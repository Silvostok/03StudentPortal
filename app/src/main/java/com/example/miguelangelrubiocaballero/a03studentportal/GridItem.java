package com.example.miguelangelrubiocaballero.a03studentportal;

public class GridItem {

    // variables
    private String url;
    private String title;


    // constructor
    public GridItem(String title, String url)
    {
        this.title = title;
        this.url = url;
    }

    // getters & setters
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return title;
    }

}
