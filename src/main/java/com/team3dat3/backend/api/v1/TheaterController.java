package com.team3dat3.backend.api.v1;

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
@RequestMapping("/v1")
public class TheaterController {

    private final TheaterService theaterService;
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/anonymous/theaters")
    public List<TheaterResponse> getAll() {
        return theaterService.getAll();
    }

    @GetMapping("/anonymous/theaters/{id}")
    public TheaterResponse get(@PathVariable("id") Long id) {
        return theaterService.get(id);
    }

    @PostMapping("/admin/theaters")
    public TheaterResponse create(@RequestBody TheaterRequest theaterRequest) {
        return theaterService.create(theaterRequest);
    }

    @PatchMapping("/admin/theaters")
    public TheaterResponse update(@RequestBody TheaterRequest theaterRequest) {
        return theaterService.update(theaterRequest);
    }

    @DeleteMapping("/admin/theaters")
    public void delete(@RequestBody TheaterRequest theaterRequest) {
        theaterService.delete(theaterRequest);
    }
}
