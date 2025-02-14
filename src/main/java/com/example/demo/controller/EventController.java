package com.example.demo.controller;

import com.example.demo.bindings.EventDetailsRequest;
import com.example.demo.service.EventService;
import com.example.demo.service.ImageUploadServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ImageUploadServiceImpl imageUploadService;

    @PostMapping(value = "/event" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvt( @RequestPart("eventDetails") String eventDetailsJson,@RequestPart("image") MultipartFile image) throws JsonProcessingException {
        // Print out for debugging
        System.out.println("Received Event Details: " + eventDetailsJson);
        System.out.println("Received Image: " + image.getOriginalFilename());
        // Deserialize event details JSON into EventDetailsRequest object
        ObjectMapper objectMapper = new ObjectMapper();
        EventDetailsRequest eventDetailsRequest = objectMapper.readValue(eventDetailsJson, EventDetailsRequest.class);
        EventDetailsRequest event = eventService.createEvent(eventDetailsRequest, image);
        return ResponseEntity.ok(event);
    }

    @PutMapping(value = "/event/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateEvt(@PathVariable Integer id, @RequestPart("eventDetails") String eventDetailsJson,@RequestPart("image") MultipartFile image) throws JsonProcessingException {
        // Print out for debugging
        System.out.println("Received Event Details: " + eventDetailsJson);
        System.out.println("Received Image: " + image.getOriginalFilename());
        // Deserialize event details JSON into EventDetailsRequest object
        ObjectMapper objectMapper = new ObjectMapper();
        EventDetailsRequest eventDetailsRequest = objectMapper.readValue(eventDetailsJson, EventDetailsRequest.class);
        EventDetailsRequest event = eventService.updateEvent(id,eventDetailsRequest, image);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/event/{id}")
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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageUploadService.uploadImage(file);
            return ResponseEntity.ok().body(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    @PostMapping("/evt")
    public ResponseEntity<?> createEvent(@RequestBody EventDetailsRequest eventDetailsRequest ) throws JsonProcessingException {
        EventDetailsRequest event = eventService.createEvent(eventDetailsRequest);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/evts")
    public ResponseEntity<?> createEvents(@RequestBody List<EventDetailsRequest> eventDetailsRequests ) throws JsonProcessingException {
        eventService.createEvents(eventDetailsRequests);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/evt/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id, @RequestBody EventDetailsRequest eventDetailsRequest) throws JsonProcessingException {
        EventDetailsRequest event = eventService.updateEvent(id,eventDetailsRequest);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/search")
    public List<EventDetailsRequest> searchEvents(@RequestParam String query) {
        return eventService.searchEvents(query);
    }


}
