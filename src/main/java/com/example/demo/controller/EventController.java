package com.example.demo.controller;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<?> createEvt(@RequestBody EventDetailsRequest eventDetailsRequest){
        EventDetailsRequest event = eventService.createEvent(eventDetailsRequest);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/event")
    public ResponseEntity<?> updateEvt(@RequestBody EventDetailsRequest eventDetailsRequest){
        EventDetailsRequest event = eventService.updateEvent(eventDetailsRequest);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event")
    public ResponseEntity<?> deleteEvt(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("deleted...");
    }


}
