package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.repository.SeatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat service
 */
@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatResponse> getAll() {
        return seatRepository
                .findAll()
                .stream()
                .map(SeatResponse::new)
                .collect(Collectors.toList());
    }

    public SeatResponse get(Long id) {
        Seat seat = seatRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new SeatResponse(seat);
    }

    public SeatResponse create(SeatRequest seatRequest) {
        Seat seat = seatRepository.save(seatRequest.toSeat());
        return new SeatResponse(seat);
    }

    public SeatResponse update(SeatRequest seatRequest) {
        Seat seat = seatRepository
                .findById(seatRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRequest.copy(seat);
        return new SeatResponse(seatRepository.save(seat));
    }

    public void delete(SeatRequest seatRequest) {
        Seat seat = seatRepository
                .findById(seatRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRepository.delete(seat);
    }
}
