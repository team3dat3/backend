package com.team3dat3.backend.service;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation service
 */

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.team3dat3.backend.dto.reservation.*;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.TheaterRepository;
import com.team3dat3.backend.repository.UserRepository;

@Service
public class ReservationService {
    
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private SeatRepository seatRepository;
    private TheaterRepository theaterRepository;
    private ShowDateTimeRepository showDateTimeRepository;

    public ReservationService(
        ReservationRepository reservationRepository,
        UserRepository userRepository,
        ShowRepository showRepository,
        TheaterRepository theaterRepository,
        SeatRepository seatRepository,
        ShowDateTimeRepository showDateTimeRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.theaterRepository = theaterRepository;
        this.seatRepository = seatRepository;
        this.showDateTimeRepository = showDateTimeRepository;
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

    public ReservationResponse checkIn(int id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            reservation.setCheckedIn(true);
            reservation = reservationRepository.save(reservation);
            return new ReservationResponse(reservation);
    }

    public ReservationResponse create(ReservationRequest reservationRequest) {
        User user = findUser(reservationRequest.getUsername());
        Show show = findShow(reservationRequest.getShowId());
        Reservation reservation = reservationRequest.toReservation();
        reservation.setUser(user);
        reservation.setShow(show);
        reservation.setSeats(findSeats(reservationRequest.getSeatIds()));
        reservation.setShowDateTime(findShowDateTime(reservationRequest.getShowDateId()));
        reservation = reservationRepository.save(reservation);
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

    public List<ReservationResponse> findUserReservations(String username) {
        return reservationRepository
            .findUserReservations(username)
            .stream()
            .map(r->new ReservationResponse(r))
            .collect(Collectors.toList());
    }

    public ReservationResponse findUserReservation(String username, int id) {
        Reservation reservation = reservationRepository
            .findUserReservation(username, id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ReservationResponse(reservation);
    }

    private User findUser(String username) {
        return userRepository
            .findById(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Show findShow(int id) {
        return showRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private List<Seat> findSeats(List<Long> seatIds) {
        return seatRepository.findAllById(seatIds);
    }

    private ShowDateTime findShowDateTime(int showDateTimeId) {
        return showDateTimeRepository
            .findById(showDateTimeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
