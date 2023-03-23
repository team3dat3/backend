package com.team3dat3.backend.api;

import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.SeatRowResponse;
import com.team3dat3.backend.service.SeatRowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seatrows")
public class SeatRowController {
    private final SeatRowService seatRowService;
    public SeatRowController(SeatRowService seatRowService) {
        this.seatRowService = seatRowService;
    }

    @GetMapping
    public List<SeatRowResponse> getAll() {
        return seatRowService.getAll();
    }

    @GetMapping("/{id}")
    public SeatRowResponse get(@PathVariable("id") Long id) {
        return seatRowService.get(id);
    }

    @PostMapping
    public SeatRowResponse create(@RequestBody SeatRowRequest seatRowRequest) {
        return seatRowService.create(seatRowRequest);
    }

    @PatchMapping
    public SeatRowResponse update(@RequestBody SeatRowRequest seatRowRequest) {
        return seatRowService.update(seatRowRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody SeatRowRequest seatRowRequest) {
        seatRowService.delete(seatRowRequest);
    }
}
