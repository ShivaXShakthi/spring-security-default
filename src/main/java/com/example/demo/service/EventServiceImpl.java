package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;
import com.example.demo.repo.EventRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepo;


    @Override
    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest) {
        Event evt = new Event();
        BeanUtils.copyProperties(eventDetailsRequest,evt);
        Event savedEvt = eventRepo.save(evt);
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(savedEvt, evtDet);
        return evtDet;
    }

    @Override
    public EventDetailsRequest updateEvent(EventDetailsRequest eventDetailsRequest) {
        return createEvent(eventDetailsRequest);
    }

    @Override
    public void deleteEvent(Integer eventId) {
        eventRepo.deleteById(eventId);
    }
}
