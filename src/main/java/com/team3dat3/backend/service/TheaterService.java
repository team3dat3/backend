package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.TheaterRequest;
import com.team3dat3.backend.dto.theater.TheaterResponse;
import com.team3dat3.backend.entity.Theater;
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

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
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
        Theater theater = theaterRepository.save(theaterRequest.toTheater());
        return new TheaterResponse(theater);
    }

    public TheaterResponse update(TheaterRequest theaterRequest) {
        Theater theater = theaterRepository
                .findById(theaterRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        theaterRequest.copy(theater);
        return new TheaterResponse(theaterRepository.save(theater));
    }

    public void delete(TheaterRequest theaterRequest) {
        Theater theater = theaterRepository
                .findById(theaterRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        theaterRepository.delete(theater);
    }
}
