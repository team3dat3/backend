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

@RequestMapping("/v1/reservations")
@RestController
public class ReservationController {
    
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ReservationResponse find(@PathVariable("id") int id) {
        return reservationService.find(id);
    }

    @PostMapping
    public ReservationResponse create(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.create(reservationRequest);
    }

    @PatchMapping
    public ReservationResponse update(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.update(reservationRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody ReservationRequest reservationRequest) {
        reservationService.delete(reservationRequest);
    }
}
