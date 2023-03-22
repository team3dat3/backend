package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.SeatRowResponse;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.repository.SeatRowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatRowService {
    private final SeatRowRepository seatRowRepository;

    public SeatRowService(SeatRowRepository seatRowRepository) {
        this.seatRowRepository = seatRowRepository;
    }

    public List<SeatRowResponse> getAll() {
        return seatRowRepository
                .findAll()
                .stream()
                .map(SeatRowResponse::new)
                .collect(Collectors.toList());
    }

    public SeatRowResponse get(Long id) {
        SeatRow seatRow = seatRowRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new SeatRowResponse(seatRow);
    }

    public SeatRowResponse create(SeatRowRequest seatRowRequest) {
        SeatRow seatRow = seatRowRepository.save(seatRowRequest.toSeatRow());
        return new SeatRowResponse(seatRow);
    }

    public SeatRowResponse update(SeatRowRequest seatRowRequest) {
        SeatRow seatRow = seatRowRepository
                .findById(seatRowRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRowRequest.copy(seatRow);
        return new SeatRowResponse(seatRowRepository.save(seatRow));
    }

    public void delete(SeatRowRequest seatRowRequest) {
        SeatRow seatRow = seatRowRepository
                .findById(seatRowRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        seatRowRepository.delete(seatRow);
    }
}
