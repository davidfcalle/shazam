package com.example.demo.repositories;

import com.example.demo.entities.VetEntity;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<VetEntity, Long> {

    VetEntity findByName(String name);

    VetEntity findByIdAndName(Long id, String name);

}
