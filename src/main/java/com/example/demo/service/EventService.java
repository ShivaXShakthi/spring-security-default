package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;

import java.util.List;

public interface EventService {

    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest);
    public EventDetailsRequest updateEvent(Integer eventId,EventDetailsRequest eventDetailsRequest);
    public void deleteEvent(Integer eventId);
    public EventDetailsRequest getEvent(Integer eventId);
    public List<EventDetailsRequest> getEvents();
}
