package com.team3dat3.backend.api;

import com.team3dat3.backend.dto.theater.TheaterRequest;
import com.team3dat3.backend.dto.theater.TheaterResponse;
import com.team3dat3.backend.service.TheaterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater controller
 */
@RestController
@RequestMapping("/theaters")
public class TheaterController {

    private final TheaterService theaterService;
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping
    public List<TheaterResponse> getAll() {
        return theaterService.getAll();
    }

    @GetMapping("/{id}")
    public TheaterResponse get(@PathVariable("id") Long id) {
        return theaterService.get(id);
    }

    @PostMapping
    public TheaterResponse create(@RequestBody TheaterRequest theaterRequest) {
        return theaterService.create(theaterRequest);
    }

    @PatchMapping
    public TheaterResponse update(@RequestBody TheaterRequest theaterRequest) {
        return theaterService.update(theaterRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody TheaterRequest theaterRequest) {
        theaterService.delete(theaterRequest);
    }
}
