package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;

public interface EventService {

    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest);
    public EventDetailsRequest updateEvent(EventDetailsRequest eventDetailsRequest);
    public void deleteEvent(Integer eventId);

}
