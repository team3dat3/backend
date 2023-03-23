package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.omdb.OmdbResponse;
import com.team3dat3.backend.service.OmdbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/omdb")
public class OmdbController {

  private OmdbService omdbService;

  public OmdbController(OmdbService omdbService) {
    this.omdbService = omdbService;
  }

  @GetMapping("/{title}/{year}")
 public List<OmdbResponse> getMovie(@PathVariable String title, @PathVariable int year){
   return omdbService.lookupAPI(title, year);
 }
}
