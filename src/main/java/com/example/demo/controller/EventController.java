package com.example.demo.controller;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/event" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvt(@RequestPart("eventDetails") EventDetailsRequest eventDetailsRequest,@RequestPart("image") MultipartFile image){
        EventDetailsRequest event = eventService.createEvent(eventDetailsRequest, image);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<?> updateEvt(@PathVariable Integer id, @RequestBody EventDetailsRequest eventDetailsRequest){
        EventDetailsRequest event = eventService.updateEvent(id,eventDetailsRequest);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event/{id}")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    public ResponseEntity<?> deleteEvt(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("deleted...");
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<?> getEvt(@PathVariable Integer id){
        EventDetailsRequest event = eventService.getEvent(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/events")
    public ResponseEntity<?> getEvts(){
        List<EventDetailsRequest> events = eventService.getEvents();
        return ResponseEntity.ok(events);
    }


}
