package com.example.demo.models;

import java.time.Clock;
import java.time.ZonedDateTime;

public class Vet {

    private final Long id;
    private final String name;
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    public Vet(Long id, String name, ZonedDateTime start, ZonedDateTime end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public boolean isAvailable(Clock clock) {
        ZonedDateTime currentTime = clock.instant().atZone(clock.getZone());
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }
}
