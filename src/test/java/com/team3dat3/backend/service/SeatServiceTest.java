package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.repository.SeatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SeatServiceTest {

    @Autowired
    private SeatRepository seatRepository;

    private SeatService seatService;
    @BeforeEach
    void setUp() {
        seatService = new SeatService(seatRepository);
    }

    @Test
    void getAll() {
        Seat seat1 = new Seat(0L, true);
        Seat seat2 = new Seat(1L, false);
        seatRepository.save(seat1);
        seatRepository.save(seat2);

        List<SeatResponse> seats = seatService.getAll();

        assertEquals(2, seats.size());
        assertEquals(0L, seats.get(0).getId());
        assertEquals(1L, seats.get(1).getId());
    }

    @Test
    void get() {
        Seat seat = new Seat(2L, true);
        seatRepository.save(seat);

        SeatResponse response = seatService.get(seat.getId());

        assertEquals(2L, response.getId());
    }

    @Test
    public void testGetSeatByIdNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            seatService.get(123L);
        });
    }

    @Test
    void create() {
        SeatRequest request = new SeatRequest(3L, true);
        SeatResponse response = seatService.create(request);

        assertEquals(3L, response.getId());
    }

    @Test
    void update() {
        Seat seat = new Seat(4L, true);
        seatRepository.save(seat);

        SeatRequest request = new SeatRequest(4L, false);
        request.setReserved(seat.isReserved());

        SeatResponse response = seatService.update(request);

        assertFalse(response.isReserved());
    }

    @Test
    public void testUpdateSeatNotFound() {
        SeatRequest request = new SeatRequest(5L, false);
        request.setId(123L);

        assertThrows(ResponseStatusException.class, () -> {
            seatService.update(request);
        });
    }

    @Test
    void delete() {
        Seat seat = new Seat(6L, true);
        seatRepository.save(seat);

        SeatRequest request = new SeatRequest(6L, false);
        request.setReserved(seat.isReserved());

        seatService.delete(request);

        assertFalse(seatRepository.findById(seat.getId()).isPresent());
    }

    @Test
    public void testDeleteSeatNotFound() {
        SeatRequest request = new SeatRequest(7L, true);
        request.setId(123L);

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            seatService.delete(request);
        });
    }
}