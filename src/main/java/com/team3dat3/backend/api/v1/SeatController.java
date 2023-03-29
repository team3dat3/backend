package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.theater.SeatRequest;
import com.team3dat3.backend.dto.theater.SeatResponse;
import com.team3dat3.backend.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat controller
 */
@RestController
@RequestMapping("/v1")
public class SeatController {
    private final SeatService seatService;
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/anonymous/seats")
    public List<SeatResponse> getAll() {
        return seatService.getAll();
    }

    @GetMapping("/anonymous/seats/{id}")
    public SeatResponse get(@PathVariable("id") Long id) {
        return seatService.get(id);
    }

    @PostMapping("/admin/seats")
    public SeatResponse create(@RequestBody SeatRequest seatRequest) {
        return seatService.create(seatRequest);
    }

    @PutMapping("/admin/seats")
    public SeatResponse update(@RequestBody SeatRequest seatRequest) {
        return seatService.update(seatRequest);
    }

    @DeleteMapping("/admin/seats")
    public void delete(@RequestBody SeatRequest seatRequest) {
        seatService.delete(seatRequest);
    }
}
