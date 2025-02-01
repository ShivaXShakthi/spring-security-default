package com.example.demo.controller;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<?> createEvt(@RequestBody EventDetailsRequest eventDetailsRequest){
        EventDetailsRequest event = eventService.createEvent(eventDetailsRequest);
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
