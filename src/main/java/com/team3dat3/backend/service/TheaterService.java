package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.TheaterRequest;
import com.team3dat3.backend.dto.theater.TheaterResponse;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.SeatRowRepository;
import com.team3dat3.backend.repository.TheaterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater service
 */
@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final SeatRowRepository seatRowRepository;

    public TheaterService(TheaterRepository theaterRepository, SeatRowRepository seatRowRepository) {
        this.theaterRepository = theaterRepository;
        this.seatRowRepository = seatRowRepository;
    }

    public List<TheaterResponse> getAll() {
        return theaterRepository
                .findAll()
                .stream()
                .map(TheaterResponse::new)
                .collect(Collectors.toList());
    }

    public TheaterResponse get(Long id) {
        Theater theater = theaterRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new TheaterResponse(theater);
    }

    public TheaterResponse create(TheaterRequest theaterRequest) {
        Theater theater = theaterRequest.toTheater();
        List<SeatRow> seatRows = seatRowRepository.findAllById(theaterRequest.getSeatRowIds());
        theater.setSeatRows(seatRows);
        theater = theaterRepository.save(theaterRequest.toTheater());
        return new TheaterResponse(theater);
    }

    public TheaterResponse update(TheaterRequest theaterRequest) {
        Theater theater = theaterRepository
                .findById(theaterRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        theaterRequest.copy(theater);
        List<SeatRow> seatRows = seatRowRepository.findAllById(theaterRequest.getSeatRowIds());
        theater.setSeatRows(seatRows);
        return new TheaterResponse(theaterRepository.save(theater));
    }

    public void delete(TheaterRequest theaterRequest) {
        Theater theater = theaterRepository
                .findById(theaterRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        theaterRepository.delete(theater);
    }
}
