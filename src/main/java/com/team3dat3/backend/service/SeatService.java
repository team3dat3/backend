package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.SeatRowRepository;

import com.team3dat3.backend.repository.TheaterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    private final SeatRowRepository seatRowRepository;
    private final TheaterRepository theaterRepository;

    public SeatService(SeatRepository seatRepository, SeatRowRepository seatRowRepository,
                       TheaterRepository theaterRepository) {
        this.seatRepository = seatRepository;
        this.seatRowRepository = seatRowRepository;
        this.theaterRepository = theaterRepository;
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
        Seat seat = seatRequest.toSeat();
        if (seatRequest.getSeatRowId() != null && seatRequest.getSeatRowId() != 0) {
            SeatRow seatRow = findSeatRow(seatRequest.getSeatRowId());
            seat.setSeatRow(seatRow);
        }
        seat = seatRepository.save(seat);
        return new SeatResponse(seat);
    }

    public SeatResponse update(SeatRequest seatRequest) {
        Seat seat = seatRepository
                .findById(seatRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRequest.copy(seat);
        if (seatRequest.getSeatRowId() != null && seatRequest.getSeatRowId() != 0) {
            SeatRow seatRow = findSeatRow(seatRequest.getSeatRowId());
            seat.setSeatRow(seatRow);
        } else seat.setSeatRow(null);
        return new SeatResponse(seatRepository.save(seat));
    }

    public void delete(SeatRequest seatRequest) {
        Seat seat = seatRepository
                .findById(seatRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRepository.delete(seat);
    }

    private SeatRow findSeatRow(Long id) {
        return seatRowRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<SeatResponse> findByTheaterId(Long theaterId){

        List<SeatRow> seatRows = seatRowRepository.findByTheaterId(theaterId);
        List<Seat> seats = new ArrayList<>();

        for (SeatRow seatRow: seatRows) {
            List<Seat> seats1 = seatRepository.findSeatsBySeatRowId(seatRow.getId());
            seats.addAll(seats1);
        }
        return seats.stream()
            .map(SeatResponse::new)
            .collect(Collectors.toList());
    }
}
