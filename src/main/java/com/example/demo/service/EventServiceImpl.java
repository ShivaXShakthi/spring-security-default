package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;
import com.example.demo.repo.EventRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public EventDetailsRequest getEvent(Integer eventId) {
        Optional<Event> evtOpt = eventRepo.findById(eventId);
        Event event = evtOpt.get();
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(event, evtDet);
        return evtDet;
    }

    @Override
    public List<EventDetailsRequest> getEvents() {
        List<Event> allEvts = eventRepo.findAll();
        List<EventDetailsRequest> evtDets = new ArrayList<>();
        allEvts.forEach((evt) -> {
            EventDetailsRequest evtDet = new EventDetailsRequest();
            BeanUtils.copyProperties(evt, evtDet);
            evtDets.add(evtDet);
        });
        return evtDets;
    }


}
