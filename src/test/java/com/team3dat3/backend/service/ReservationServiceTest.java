package com.team3dat3.backend.service;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation service test
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import com.team3dat3.backend.dto.reservation.ReservationRequest;
import com.team3dat3.backend.dto.reservation.ReservationResponse;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.repository.ReservationRepository;

@DataJpaTest
public class ReservationServiceTest {
    
    @Autowired
    ReservationRepository reservationRepository;

    ReservationService reservationService;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    void beforeEach() {
        reservationService = new ReservationService(reservationRepository);
        reservation1 = reservationRepository.save(new Reservation());
        reservation2 = reservationRepository.save(new Reservation());
    }

    @Test
    void testFindAll() {
        List<ReservationResponse> reservationResponses = reservationService.findAll();
        assertEquals(2, reservationResponses.size());
    }

    @Test
    void testFind() {
        ReservationResponse reservationResponse = reservationService.find(reservation1.getId());
        assertEquals(reservation1.getId(), reservationResponse.getId());
    }

    @Test
    void testCreate() {
        ReservationRequest reservationRequest = new ReservationRequest();        
        ReservationResponse reservationResponse = reservationService.create(reservationRequest);
        assertNotEquals(0, reservationResponse.getId());
    }

    @Test
    void testUpdate() {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation2.getId());
        reservationRequest.setCheckedIn(true);
        ReservationResponse reservationResponse = reservationService.update(reservationRequest);
        assertEquals(reservationRequest.getId(), reservationResponse.getId());
        assertEquals(reservationRequest.isCheckedIn(), reservationResponse.isCheckedIn());
    }

    @Test
    void testDelete() {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation2.getId());
        reservationService.delete(reservationRequest);
        assertThrows(ResponseStatusException.class, () -> {
            reservationService.find(3);});
    }
}
