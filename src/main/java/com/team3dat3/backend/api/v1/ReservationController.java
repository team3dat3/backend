package com.team3dat3.backend.api.v1;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation controller
 */

import java.util.List;

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

    @GetMapping("/authenticated/reservations")
    public List<ReservationResponse> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/authenticated/reservations/{id}")
    public ReservationResponse find(@PathVariable("id") int id) {
        return reservationService.find(id);
    }

    @PostMapping("/authenticated/reservations")
    public ReservationResponse create(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.create(reservationRequest);
    }

    @PatchMapping("/admin/reservations")
    public ReservationResponse update(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.update(reservationRequest);
    }

    @DeleteMapping("/admin/reservations")
    public void delete(@RequestBody ReservationRequest reservationRequest) {
        reservationService.delete(reservationRequest);
    }
}
