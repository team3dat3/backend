package com.team3dat3.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.theater.TheaterRequest;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.TheaterRepository;
import com.team3dat3.backend.service.TheaterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater controller tests
 */
@DataJpaTest
class TheaterControllerTest {

    // make mockmvc controller tests
    @Autowired
    TheaterRepository theaterRepository;

    TheaterService theaterService;
    TheaterController theaterController;

    private Theater theater1;
    private Theater theater2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        theaterService = new TheaterService(theaterRepository);
        theaterController = new TheaterController(theaterService);
        mockMvc = MockMvcBuilders.standaloneSetup(theaterController).build();
        theater1 = theaterRepository.save(new Theater());
        theater1.setSeatRows(new ArrayList<>());
        theater2 = theaterRepository.save(new Theater());
        theater2.setSeatRows(new ArrayList<>());

    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/theaters"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/theaters/" + theater1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(theater1.getId().intValue())));
    }

/* Commented for show to be added.
    @Test
    void testCreate() throws Exception {
        TheaterRequest theaterRequest = new TheaterRequest(theater1.getId(),
                List.of(theater1.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));
        mockMvc.perform(post("/theaters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theaterRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(not(0))));
    }

    @Test
    void testUpdate() throws Exception {
        TheaterRequest theaterRequest = new TheaterRequest(theater2.getId(),
                List.of(theater2.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));
        mockMvc.perform(patch("/theaters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theaterRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(theater2.getId().intValue())));
    }

    @Test
    void testDelete() throws Exception {
        TheaterRequest theaterRequest = new TheaterRequest(theater2.getId(),
                List.of(theater2.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));
        mockMvc.perform(delete("/theaters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theaterRequest)))
                .andExpect(status().isOk());
    }
*/

}