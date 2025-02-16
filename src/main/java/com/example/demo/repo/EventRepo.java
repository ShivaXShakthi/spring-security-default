package com.example.demo.repo;

import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepo extends JpaRepository<Event,Integer> {

    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.prasanga) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.mela) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.location) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Event> searchEvents(String query);

}
