package com.team3dat3.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.team3dat3.backend.dto.reservation.*;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.repository.ReservationRepository;

@Service
public class ReservationService {
    
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> findAll() {
        return reservationRepository
            .findAll()
            .stream()
            .map(r->new ReservationResponse(r))
            .collect(Collectors.toList());
    }

    public ReservationResponse find(int id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ReservationResponse(reservation);
    }

    public ReservationResponse create(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.save(
            reservationRequest.toReservation()
        );
        return new ReservationResponse(reservation);
    }

    public ReservationResponse update(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository
            .findById(reservationRequest.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationRequest.copyTo(reservation);
        return new ReservationResponse(reservationRepository.save(reservation));
    }

    public void delete(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository
            .findById(reservationRequest.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationRepository.delete(reservation);
    }
}
