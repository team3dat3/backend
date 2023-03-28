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
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.UserRepository;

@DataJpaTest
public class ReservationServiceTest {
    
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    ReservationService reservationService;

    private Reservation reservation1;
    private Reservation reservation2;

    private Show show1;
    private User user1;

    @BeforeEach
    void beforeEach() {
        reservationService = new ReservationService(reservationRepository, userRepository, showRepository);
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "87654321", new String[] {"MEMBER"}));
        show1 = showRepository.save(new Show());  
        reservation1 = reservationRepository.save(new Reservation(user1, show1));
        reservation2 = reservationRepository.save(new Reservation(user1, show1));
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
        reservationRequest.setUsername(user1.getUsername());
        reservationRequest.setShowId(show1.getId());
        ReservationResponse reservationResponse = reservationService.create(reservationRequest);
        assertNotEquals(0, reservationResponse.getId());
        //assertEquals(user1, reservationResponse.getUser());
        //assertEquals(show1, reservationResponse.getShow());
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

    @Test
    void testCheckIn() {
        ReservationResponse reservationResponse = reservationService.checkIn(reservation1.getId());
        assertEquals(reservation1.getId(), reservationResponse.getId());
        assertEquals(true, reservationResponse.isCheckedIn());
    }

    @Test
    void testFindUserReservations() {
        List<ReservationResponse> reservationResponses = reservationService.findUserReservations(user1.getUsername());
        assertEquals(2, reservationResponses.size());
    }

    @Test
    void testFindUserReservation() {
        ReservationResponse reservationResponse = reservationService.findUserReservation(user1.getUsername(), reservation1.getId());
        assertEquals(reservation1.getId(), reservationResponse.getId());
    }
}
