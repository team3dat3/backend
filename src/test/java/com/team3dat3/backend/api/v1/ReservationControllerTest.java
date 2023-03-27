package com.team3dat3.backend.api.v1;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation controller test
 */

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

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
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.UserRepository;
import com.team3dat3.backend.service.ReservationService;

@DataJpaTest
public class ReservationControllerTest {
    
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    ReservationService reservationService;
    ReservationController reservationController;

    private Reservation reservation1;
    private Reservation reservation2;

    private Show show1;
    private User user1;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        reservationService = new ReservationService(reservationRepository, userRepository, showRepository);
        reservationController = new ReservationController(reservationService);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "87654321", new String[] {"MEMBER"}));
        show1 = showRepository.save(new Show()); 
        reservation1 = reservationRepository.save(new Reservation(user1, show1));
        reservation2 = reservationRepository.save(new Reservation(user1, show1));
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/v1/admin/reservations"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/v1/admin/reservations/" + reservation1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(reservation1.getId())));
    }

    @Test
    void testCreate() throws Exception {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setUsername(user1.getUsername());
        reservationRequest.setShowId(show1.getShowId());
        mockMvc.perform(post("/v1/admin/reservations")
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
        mockMvc.perform(patch("/v1/admin/reservations")
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
        mockMvc.perform(delete("/v1/admin/reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(reservationRequest)))
            .andExpect(status().isOk());
    }
}
