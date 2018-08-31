package com.example.andrew.saultdesttourismapp;

import java.io.Serializable;

//Class built to hold the end-data (after parsing and filtering)

public class DestData implements Serializable{

    String placeId;
    String name;
    double lat = 0, lng = 0;
    int pHeight = 1, pWidth = 1;
    String photoRef = "";
    Boolean openNow = false;
    double rating = 0;
    String vicinity = "";
    int price = 0;
    String phone = "";
    String website = "";
    String address = "";
    String intlPhone = "";


    Boolean curated = false;




    String shortDesc = "";
    String desc = "";


    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    double distance = 0.0;
    String[] times = new String[7];

    Boolean openData = true, photoData = true;


    public double getDistance() {
        return distance;
    }

    public DestData(String placeId) {
        this.placeId = placeId;

    }
    public Boolean getCurated() {
        return curated;
    }

    public void setCurated(Boolean curated) {
        this.curated = curated;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntlPhone() {
        return intlPhone;
    }

    public void setIntlPhone(String intlPhone) {
        this.intlPhone = intlPhone;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getpHeight() {
        return pHeight;
    }

    public void setpHeight(int pHeight) {
        this.pHeight = pHeight;
    }

    public int getpWidth() {
        return pWidth;
    }

    public void setpWidth(int pWidth) {
        this.pWidth = pWidth;
    }

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }



}
