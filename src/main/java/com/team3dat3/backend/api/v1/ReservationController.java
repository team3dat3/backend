package com.team3dat3.backend.api.v1;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation controller
 */

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.team3dat3.backend.dto.reservation.*;
import com.team3dat3.backend.service.ReservationService;


@RequestMapping("/v1")
@RestController
public class ReservationController {
    
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/member/reservations")
    public List<ReservationResponse> findUserReservations(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return reservationService.findUserReservations(username);
    }

    @GetMapping("/member/reservations/{id}")
    public ReservationResponse findUserReservation(@AuthenticationPrincipal Jwt jwt, @PathVariable("id") int id) {
        String username = jwt.getSubject();
        return reservationService.findUserReservation(username, id);
    }

    @PostMapping("/member/reservations")
    public ReservationResponse createUserReservation(@AuthenticationPrincipal Jwt jwt, @RequestBody ReservationRequest reservationRequest) {
        String username = jwt.getSubject();
        reservationRequest.setUsername(username);
        return reservationService.create(reservationRequest);
    }

    @GetMapping("/admin/reservations/{id}/checkin")
    public ReservationResponse checkIn(@PathVariable("id") int id) {
        return reservationService.checkIn(id);
    }

    @GetMapping("/admin/reservations")
    public List<ReservationResponse> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/admin/reservations/{id}")
    public ReservationResponse find(@PathVariable("id") int id) {
        return reservationService.find(id);
    }

    @PostMapping("/admin/reservations")
    public ReservationResponse create(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.create(reservationRequest);
    }

    @PutMapping("/admin/reservations")
    public ReservationResponse update(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.update(reservationRequest);
    }

    @DeleteMapping("/admin/reservations")
    public void delete(@RequestBody ReservationRequest reservationRequest) {
        reservationService.delete(reservationRequest);
    }
}
