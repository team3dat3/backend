package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.TheaterRequest;
import com.team3dat3.backend.dto.theater.TheaterResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.TheaterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater service tests
 */
@DataJpaTest
class TheaterServiceTest {

    @Autowired
    private TheaterRepository theaterRepository;
    private TheaterService theaterService;
    private Theater theater1;
    private Theater theater2;
    @BeforeEach
    void setUp() {
        theaterService = new TheaterService(theaterRepository);
        theater1 = theaterRepository.save(new Theater());
        theater1.setSeatRows(new ArrayList<>());
        theater2 = theaterRepository.save(new Theater());
        theater2.setSeatRows(new ArrayList<>());
    }

    @Test
    void getAll() {
        List<TheaterResponse> theaterResponses = theaterService.getAll();

        assertEquals(2, theaterResponses.size());
        assertEquals(theater1.getId(), theaterResponses.get(0).getId());
        assertEquals(theater2.getId(), theaterResponses.get(1).getId());
    }

    @Test
    void get() {
        TheaterResponse theaterResponse = theaterService.get(theater1.getId());

        assertEquals(theater1.getId(), theaterResponse.getId());
    }
/* commented for show to be added
    @Test
    void create() {
        TheaterRequest theaterRequest = new TheaterRequest(theater2.getId(),
                List.of(theater2.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));

        TheaterResponse theaterResponse = theaterService.create(theaterRequest);

        assertEquals(theaterRequest.getId(), theaterResponse.getId());
    }

    @Test
    void update() {
        TheaterRequest theaterRequest = new TheaterRequest(theater2.getId(),
                List.of(theater2.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));

        TheaterResponse theaterResponse = theaterService.update(theaterRequest);

        assertEquals(theaterRequest.getId(), theaterResponse.getId());
    }

    @Test
    void delete() {
        TheaterRequest theaterRequest = new TheaterRequest(theater1.getId(),
                List.of(theater1.getSeatRows().stream().map(SeatRow::getId).toArray(Long[]::new)));

        theaterService.delete(theaterRequest);

        assertEquals(1, theaterRepository.findAll().size());
    }*/
}