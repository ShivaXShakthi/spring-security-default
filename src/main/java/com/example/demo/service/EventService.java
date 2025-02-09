package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {

    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest, MultipartFile image);
    public EventDetailsRequest updateEvent(Integer eventId,EventDetailsRequest eventDetailsRequest, MultipartFile image);
    public void deleteEvent(Integer eventId);
    public EventDetailsRequest getEvent(Integer eventId);
    public List<EventDetailsRequest> getEvents();
}
