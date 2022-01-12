package com.example.demo;

import com.example.demo.entities.VetEntity;
import com.example.demo.models.Vet;
import com.example.demo.repositories.VetRepository;
import com.example.demo.services.VetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VetServiceTest {

    private final VetRepository vetRepository = mock(VetRepository.class);

    private final VetService sut = new VetService(vetRepository);
    // system under test

    @Test
    public void findById_whenTheVetWasFound_shouldMapAndSaveTheData() {
        // Given
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = ZonedDateTime.now();
        VetEntity vet = new VetEntity();
        vet.setId(1L);
        vet.setEnd(end);
        vet.setStart(start);
        vet.setName("Yolanda");

        // When
        when(vetRepository.findById(1L)).thenReturn(Optional.of(vet));
        Optional<Vet> vetOptional = sut.findById(1L);

        //Then
        assertThat(vetOptional).isNotEmpty();
        Vet expectedVet = new Vet(1L, "Yolanda", start, end);
        assertThat(vetOptional.get()).isEqualTo(expectedVet);
    }


    @Test
    public void findById_whenTheVetWasNotFound_shouldReturnEmptyOptional() {
        // When
        when(vetRepository.findById(any())).thenReturn(Optional.empty());

        Optional<Vet> vetOptional = sut.findById(1L);
        Optional<Vet> vet12Optional = sut.findById(12L);

        //Then
        assertThat(vetOptional).isEmpty();
        assertThat(vet12Optional).isEmpty();
    }

}

