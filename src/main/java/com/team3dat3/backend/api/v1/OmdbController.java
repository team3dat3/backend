package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.omdb.OmdbResponse;
import com.team3dat3.backend.service.OmdbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class OmdbController {

  private OmdbService omdbService;

  public OmdbController(OmdbService omdbService) {
    this.omdbService = omdbService;
  }


  @GetMapping("/admin/omdb/search/{title}")
 public List<OmdbResponse> getMovieList(@PathVariable String title){
   return omdbService.lookupAPI(title);
 }


  @GetMapping("/admin/omdb/get/{imdbId}")
  public OmdbResponse getMovie(@PathVariable String imdbId){
    return omdbService.lookupAPIId(imdbId);
  }
}
