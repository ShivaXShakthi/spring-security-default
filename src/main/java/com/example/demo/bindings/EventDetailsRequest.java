package com.example.demo.bindings;

public class EventDetailsRequest {

    private int eventId;


    private String mela;


    private String prasanga;


    private String place;


    private String location;


    private String eventDate;


    private String eventTime;


    private String eventType;


    private String category;


    private String image;  // S3 URL stored as a string

    // Getters and Setters

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getMela() {
        return mela;
    }

    public void setMela(String mela) {
        this.mela = mela;
    }

    public String getPrasanga() {
        return prasanga;
    }

    public void setPrasanga(String prasanga) {
        this.prasanga = prasanga;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
