package com.example.demo.controller;

import com.example.demo.controller.vms.VetVm;
import com.example.demo.models.Vet;
import com.example.demo.services.VetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.util.stream.Collectors;

@RestController
public class VetController {

    private final VetService service;

    public VetController(VetService service) {
        this.service = service;
    }

    @GetMapping("/vets")
    public Iterable<VetVm> findAll() {
        return service.findAll()
                .stream().map(this::toVm)
                .collect(Collectors.toList());
    }

    @GetMapping("/vets/{id}")
    public VetVm findVet(@PathVariable("id") Long id) {
        return service.findById(id).map(this::toVm).orElse(null);
    }

    private VetVm toVm(Vet vet) {
        return new VetVm(vet.getId(), vet.getName(), vet.isAvailable(
                Clock.systemUTC()
        ));
    }

}
