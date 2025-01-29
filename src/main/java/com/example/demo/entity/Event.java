package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_details")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "mela")
    private String mela;

    @Column(name = "prasanga")
    private String prasanga;

    @Column(name = "place")
    private String place;

    @Column(name = "location")
    private String location;

    @Column(name = "event_date")
    private String eventDate;  // You can use java.time.LocalDate if you'd like a date object.

    @Column(name = "event_time")
    private String eventTime;  // Similarly, java.time.LocalTime could be used for a time object.

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "category")
    private String category;

    @Column(name = "image")
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

