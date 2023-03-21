package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Import size method
import static org.hamcrest.Matchers.*;

// Import print method
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.reservation.ReservationRequest;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.service.ReservationService;

@DataJpaTest
public class ReservationControllerTest {
    
    @Autowired
    ReservationRepository reservationRepository;

    ReservationService reservationService;
    ReservationController reservationController;

    private Reservation reservation1;
    private Reservation reservation2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        reservationService = new ReservationService(reservationRepository);
        reservationController = new ReservationController(reservationService);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        reservation1 = reservationRepository.save(new Reservation());
        reservation2 = reservationRepository.save(new Reservation());
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/v1/reservations"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/v1/reservations/" + reservation1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(reservation1.getId())));
    }

    @Test
    void testCreate() throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest();
        mockMvc.perform(post("/v1/reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(reservationRequest)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(not(0))));
    }

    @Test
    void testUpdate() throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation2.getId());
        reservationRequest.setCheckedIn(true);
        mockMvc.perform(patch("/v1/reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(reservationRequest)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(reservation2.getId())))
            .andExpect(jsonPath("$.checkedIn", is(true)));
    }

    @Test
    void testDelete() throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation2.getId());
        mockMvc.perform(delete("/v1/reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(reservationRequest)))
            .andExpect(status().isOk());
    }
}
