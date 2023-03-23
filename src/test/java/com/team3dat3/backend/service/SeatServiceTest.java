package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.reservation.ReservationRequest;
import com.team3dat3.backend.dto.reservation.ReservationResponse;
import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatResponse;
import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.SeatRowResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.SeatRowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeatServiceTest {

    @Autowired
    private SeatRepository seatRepository;
    private SeatService seatService;
    @Autowired
    private SeatRowRepository seatRowRepository;
    private SeatRowService seatRowService;
    @Autowired
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    SeatRow seatRow1;
    SeatRow seatRow2;
    SeatRowResponse seatRowResponse1;
    SeatRowResponse seatRowResponse2;
    ReservationResponse reservationResponse1;
    ReservationResponse reservationResponse2;

    @BeforeEach
    void setUp() {
        seatService = new SeatService(seatRepository);
        seatRowService = new SeatRowService(seatRowRepository);
        reservationService = new ReservationService(reservationRepository);
        seatRow1 = new SeatRow();
        seatRow2 = new SeatRow();

        // Save some reservations to the database
        reservationResponse1 = reservationService.create(new ReservationRequest());
        reservationResponse2 = reservationService.create(new ReservationRequest());

        // Set some seats on the SeatRow objects
        seatRow1.setSeats(Arrays.asList(new Seat(), new Seat()));
        seatRow2.setSeats(Collections.singletonList(new Seat()));

        // Save the SeatRow objects to the database
        seatRowResponse1 = seatRowService.create(new SeatRowRequest(seatRow1.getId(), seatRow1.getSeats()));
        seatRowResponse2 = seatRowService.create(new SeatRowRequest(seatRow2.getId(), seatRow2.getSeats()));

        // Create Seat objects and save them to the database
        Seat seat1 = new Seat(0L, null, seatRow1);
        Seat seat2 = new Seat(1L, null, seatRow2);
        seatRepository.save(seat1);
        seatRepository.save(seat2);
    }
    /*
    @Test
    void getAll() {
        List<SeatResponse> seatResponses = seatService.getAll();

        assertEquals(3, seatResponses.size());
    }
    */
    @Test
    void get() {
        SeatRow seatRow1 = new SeatRow();
        Seat seat = new Seat(0L, null, seatRow1);
        Seat savedSeat = seatRepository.save(seat);

        SeatResponse response = seatService.get(savedSeat.getId());

        assertEquals(14L, response.getId());
    }

    @Test
    public void testGetSeatByIdNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            seatService.get(123L);
        });
    }

    @Test
    void create() {
        SeatRequest request = new SeatRequest(3L, null, seatRow1);

        SeatResponse response = seatService.create(request);

        assertEquals(3L, response.getId());
    }

    @Test
    void update() {
        SeatRow seatRow1 = new SeatRow();
        Seat seat = new Seat(4L, null, seatRow1);
        Seat savedSeat = seatRepository.save(seat);

        SeatRequest request = new SeatRequest(savedSeat.getId(), savedSeat.getReservations(), savedSeat.getSeatRow());

        SeatResponse response = seatService.update(request);

        assertNull(response.getReservations());
    }

    @Test
    public void testUpdateSeatNotFound() {
        SeatRequest request = new SeatRequest(5L, null, null);
        request.setId(123L);

        assertThrows(ResponseStatusException.class, () -> {
            seatService.update(request);
        });
    }

    @Test
    void delete() {
        SeatRow seatRow1 = new SeatRow();
        Seat seat = new Seat(6L, null, seatRow1);
        Seat savedSeat = seatRepository.save(seat);

        SeatRequest request = new SeatRequest(savedSeat.getId(), null, null);

        seatService.delete(request);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            seatService.get(savedSeat.getId());
        });
    }

    @Test
    public void testDeleteSeatNotFound() {
        SeatRequest request = new SeatRequest(7L, null, null);
        request.setId(123L);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            seatService.delete(request);
        });
    }
}