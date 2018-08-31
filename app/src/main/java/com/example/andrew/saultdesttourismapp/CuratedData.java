package com.example.andrew.saultdesttourismapp;

/**
 * Created by Andrew on 2018-04-05.
 */

//Built to hold the curated data and be more easily accessible
public class CuratedData {



    String id = "";
    String shortDesc = "";
    String description = "";
    double rating = 0.0;

    CuratedData(String id, String description, String shortDesc, double rating){
        this.id = id;
        this.description = description;
        this.shortDesc = shortDesc;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
