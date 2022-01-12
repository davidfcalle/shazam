package com.example.demo.models;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vet vet = (Vet) o;
        return Objects.equals(id, vet.id) && Objects.equals(name, vet.name) && Objects.equals(start, vet.start) && Objects.equals(end, vet.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end);
    }
}
