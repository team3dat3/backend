package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.dto.show.ShowResponse;
import com.team3dat3.backend.dto.showDateTime.ShowDateTimeRequest;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.MovieRepository;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.TheaterRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

  private ShowRepository showRepository;
  private MovieRepository movieRepository;
  private TheaterRepository theaterRepository;
  private ShowDateTimeRepository showDateTimeRepository;

  public ShowService(
    ShowRepository showRepository,
    MovieRepository movieRepository,
    TheaterRepository theaterRepository,
    ShowDateTimeRepository showDateTimeRepository
  ) {
    this.showRepository = showRepository;
    this.movieRepository = movieRepository;
    this.theaterRepository = theaterRepository;
    this.showDateTimeRepository = showDateTimeRepository;
  }

  public List<ShowResponse> findAll() {
    List<Show> allShows = showRepository.findAll();
    List<ShowResponse> showResponses = allShows.stream().map(s->new ShowResponse(s)).collect(Collectors.toList());
    return showResponses;
  }


  public ShowResponse find(int id) {
    Show foundShow = showRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show with this id unknown"));
    return new ShowResponse(foundShow);
  }

  public ShowResponse create(ShowRequest request) {
    Show show = ShowRequest.getShowEntity(request);
    show.setMovie(findMovie(request));
    show.setTheater(findTheater(request));
    show = showRepository.save(show);
    show.setShowDates(createShowDateTimesFrom(request, show));
    showRepository.save(show);
    return new ShowResponse(show);
  }

  public ShowResponse update(ShowRequest request) {
    Show foundShow = showRepository.findById(request.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show with this id unknown"));
    request.copyTo(foundShow);
    foundShow.setMovie(findMovie(request));
    foundShow.setTheater(findTheater(request));
    List<ShowDateTime> newShowDateTimes = createShowDateTimesFrom(request, foundShow);
    if (newShowDateTimes.size() > 0) {
      List<ShowDateTime> oldShowDateTimes = findShowDateTimes(request);
      // Combine old and new show date times
      oldShowDateTimes.addAll(newShowDateTimes);
      foundShow.setShowDates(oldShowDateTimes);
    }
    return new ShowResponse(showRepository.save(foundShow));
  }

  public void delete(ShowRequest request) {
    Show show = showRepository.findById(request.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    showRepository.delete(show);
  }

  private Movie findMovie(ShowRequest request) {
    return movieRepository.findById(request.getMovieTitle())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this id unknown"));
  }

  private Theater findTheater(ShowRequest request) {
    return theaterRepository.findById(request.getTheaterId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Theater with this id unknown"));
  }

  private List<ShowDateTime> createShowDateTimesFrom(ShowRequest request, Show show) {
    List<ShowDateTime> showDateTimes = request.getShowDateTimes().stream()
        .map(s -> new ShowDateTime(s, show))
        .collect(Collectors.toList());
    // save all show date times
    showDateTimes = showDateTimeRepository.saveAll(showDateTimes);
    return showDateTimes;
  }

  private List<ShowDateTime> findShowDateTimes(ShowRequest request) {
    List<ShowDateTime> showDateTimes = showDateTimeRepository.findAllById(request.getShowDateTimesIds());
    if (showDateTimes.size() != request.getShowDateTimesIds().size()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show date time with this id unknown");
    }
    return showDateTimes;
  }
}
