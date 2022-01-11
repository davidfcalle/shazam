package com.example.demo.services;

import com.example.demo.entities.VetEntity;
import com.example.demo.models.Vet;
import com.example.demo.repositories.VetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public List<Vet> findAll() {
        final List<VetEntity> dbVets = new ArrayList<>();
        vetRepository.findAll().forEach(dbVets::add);
        return dbVets.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Optional<Vet> findById(Long id) {
        return vetRepository.findById(id).map(this::toModel);
    }

    private Vet toModel(VetEntity vetEntity) {
        return new Vet(vetEntity.getId(), vetEntity.getName(), vetEntity.getStart(), vetEntity.getEnd());
    }

}
