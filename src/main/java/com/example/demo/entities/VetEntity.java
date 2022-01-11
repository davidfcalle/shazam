package com.example.demo.entities;


import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "vets")
public class VetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private ZonedDateTime start;

    private ZonedDateTime end;

    public static VetEntity of(String name,
                               ZonedDateTime start,
                               ZonedDateTime end) {
        VetEntity vet = new VetEntity();
        vet.setStart(start);
        vet.setEnd(end);
        vet.setName(name);
        return vet;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
