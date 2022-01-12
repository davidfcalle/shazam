package com.example.demo;

import com.example.demo.controller.VetController;
import com.example.demo.controller.vms.VetVm;
import com.example.demo.entities.VetEntity;
import com.example.demo.repositories.VetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VetControllerComponentTest {

    @Autowired
    private VetController vetController;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void cleanAndSaveAVet() {
        vetRepository.deleteAll();
        VetEntity entity = new VetEntity();
        entity.setName("Erika");
        entity.setEnd(ZonedDateTime.now());
        entity.setStart(ZonedDateTime.now());
        vetRepository.save(entity);
    }

    @Test
    public void shouldSaveAndGet() {
        List<VetVm> allVets = vetController.findAll();

        assertThat(allVets).isNotEmpty();
    }

    @Test
    public void testingWithMockMvc() throws Exception {
        String response = mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Erika"))
                .andExpect(jsonPath("$[0].available").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        VetVm[] result = objectMapper.readValue(response, VetVm[].class);
        assertThat(result[0].getId()).isEqualTo(1L);
    }

}
