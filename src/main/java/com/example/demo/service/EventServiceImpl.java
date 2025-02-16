package com.example.demo.service;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.DefaultImage;
import com.example.demo.entity.Event;
import com.example.demo.repo.DefaultImageRepository;
import com.example.demo.repo.EventRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private MediaAssetService assetService;

    @Autowired
    private DefaultImageRepository defaultImageRepository;


    @Override
    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest, MultipartFile image) {
        Event evt = new Event();
        BeanUtils.copyProperties(eventDetailsRequest,evt);
        if(Objects.nonNull(image)){
            Map map = assetService.uploadImage(image);
            System.out.println("map > "+map);
            evt.setImage((String) map.get("secure_url"));
        }
        Event savedEvt = eventRepo.save(evt);
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(savedEvt, evtDet);
        return evtDet;
    }

    @Override
    public EventDetailsRequest updateEvent(Integer eventId,EventDetailsRequest eventDetailsRequest, MultipartFile image) {
        EventDetailsRequest existingData = getEvent(eventId);
        BeanUtils.copyProperties(eventDetailsRequest, existingData);
        Event evt = new Event();
        BeanUtils.copyProperties(eventDetailsRequest,evt);
        evt.setEventId(eventId);
        if(Objects.nonNull(image)){
            Map map = assetService.uploadImage(image);
            System.out.println("map > "+map);
            evt.setImage((String) map.get("secure_url"));
        }
        Event savedEvt = eventRepo.save(evt);
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(savedEvt, evtDet);
        return evtDet;
    }

    @Override
    public EventDetailsRequest createEvent(EventDetailsRequest eventDetailsRequest) {
        Event evt = new Event();
        if(Objects.isNull(eventDetailsRequest.getImage()) || eventDetailsRequest.getImage().isEmpty() || eventDetailsRequest.getImage().isBlank()){
            List<DefaultImage> all = defaultImageRepository.findAll();
            if(!all.isEmpty()){
                DefaultImage defaultImage = all.get(0);
                eventDetailsRequest.setImage(defaultImage.getImageUrl());
            }
        }
        BeanUtils.copyProperties(eventDetailsRequest,evt);
        Event savedEvt = eventRepo.save(evt);
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(savedEvt, evtDet);
        return evtDet;
    }

    @Override
    public Boolean createEvents(List<EventDetailsRequest> eventDetailsRequests) {
        eventDetailsRequests.forEach((eventDetailsRequest) -> createEvent(eventDetailsRequest));
        return true;
    }

    @Override
    public EventDetailsRequest updateEvent(Integer eventId, EventDetailsRequest eventDetailsRequest) {
        EventDetailsRequest existingData = getEvent(eventId);
        BeanUtils.copyProperties(eventDetailsRequest, existingData);
        Event evt = new Event();
        BeanUtils.copyProperties(eventDetailsRequest,evt);
        evt.setEventId(eventId);
        Event savedEvt = eventRepo.save(evt);
        EventDetailsRequest evtDet = new EventDetailsRequest();
        BeanUtils.copyProperties(savedEvt, evtDet);
        return evtDet;
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

    public List<EventDetailsRequest> searchEvents(String query) {
        List<Event> allEvts = eventRepo.searchEvents(query);
        List<EventDetailsRequest> evtDets = new ArrayList<>();
        allEvts.forEach((evt) -> {
            EventDetailsRequest evtDet = new EventDetailsRequest();
            BeanUtils.copyProperties(evt, evtDet);
            evtDets.add(evtDet);
        });
        return evtDets;
    }




}
