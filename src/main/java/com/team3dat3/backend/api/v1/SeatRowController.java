package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.SeatRowResponse;
import com.team3dat3.backend.service.SeatRowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SeatRowController {
    private final SeatRowService seatRowService;
    public SeatRowController(SeatRowService seatRowService) {
        this.seatRowService = seatRowService;
    }

    @GetMapping("/anonymous/seatrows")
    public List<SeatRowResponse> getAll() {
        return seatRowService.getAll();
    }

    @GetMapping("/anonymous/seatrows/{id}")
    public SeatRowResponse get(@PathVariable("id") Long id) {
        return seatRowService.get(id);
    }

    @PostMapping("/admin/seatrows")
    public SeatRowResponse create(@RequestBody SeatRowRequest seatRowRequest) {
        return seatRowService.create(seatRowRequest);
    }

    @PutMapping("/admin/seatrows")
    public SeatRowResponse update(@RequestBody SeatRowRequest seatRowRequest) {
        return seatRowService.update(seatRowRequest);
    }

    @DeleteMapping("/admin/seatrows")
    public void delete(@RequestBody SeatRowRequest seatRowRequest) {
        seatRowService.delete(seatRowRequest);
    }
}
