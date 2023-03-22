package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.dto.movie.MovieResponse;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
  private MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<MovieResponse> getMovies(){
    List<Movie> movies = movieRepository.findAll();

    List<MovieResponse> movieResponses = movies.stream().map(m->new MovieResponse(m)).toList();

    return movieResponses;
  }

  public MovieResponse addMovie(MovieRequest movieRequest){

    if(movieRepository.existsByTitle(movieRequest.getTitle())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Movie with this title already exists");
    }

    Movie newMovie =  MovieRequest.getMovieEntity(movieRequest);
    movieRepository.save(newMovie);

    return new MovieResponse(newMovie);
  }

  public ResponseEntity<Boolean> deleteMovie(String title){
    Movie movieToDelete = movieRepository.findById(title).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this title isn't shown in this cinema"));
    try{
      movieRepository.delete(movieToDelete);
      return ResponseEntity.ok(true);
    }
    catch (Exception e){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete movie. It might be booked in a future show.");
    }
  }

  public MovieResponse findMovieByTitle(String title){
    Movie foundMovie = movieRepository.findById(title)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this title isn't shown in this cinema"));
    return new MovieResponse(foundMovie);
  }

  public List<MovieResponse> moviesByGenre(String genre){
    List<Movie> gMovies = movieRepository.findMoviesByGenre(genre);

    List<MovieResponse> moviesByGenreList = gMovies.stream().map(m ->new MovieResponse(m)).toList();

    return moviesByGenreList;
  }

  public ResponseEntity<Boolean> updateMovie(MovieRequest body, String title){
    Movie movieToEdit = movieRepository.findById(title)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this title isn't shown in this cinema"));

    //Only sets new attributes that are added to the requestBody to the movie
    Optional.ofNullable(body.getTitle()).ifPresent(movieToEdit::setTitle);
    Optional.ofNullable(body.getDirector()).ifPresent(movieToEdit::setDirector);
    Optional.ofNullable(body.getActors()).ifPresent(movieToEdit::setActors);
    Optional.ofNullable(body.getProdYear()).ifPresent(movieToEdit::setProdYear);
    Optional.ofNullable(body.getAgeLimit()).ifPresent(movieToEdit::setAgeLimit);
    Optional.ofNullable(body.getDescription()).ifPresent(movieToEdit::setDescription);
    Optional.ofNullable(body.getGenre()).ifPresent(movieToEdit::setGenre);
    Optional.ofNullable(body.getRuntime()).ifPresent(movieToEdit::setRuntime);

    movieRepository.save(movieToEdit);

    return ResponseEntity.ok(true);
  }

  public ResponseEntity<Boolean> updateDescription(String title, String newDescription){
    Movie movieToUpdate = movieRepository.findById(title)
        .orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with this title isn't shown in this cinema"));
    movieToUpdate.setDescription(newDescription);
    movieRepository.save(movieToUpdate);
    return ResponseEntity.ok(true);
  }


}
