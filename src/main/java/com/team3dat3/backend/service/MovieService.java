package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.dto.movie.MovieResponse;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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

  public void deleteMovie(MovieRequest movieRequest){
    Movie movieToDelete = movieRepository.findById(movieRequest.getTitle()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this title isn't shown in this cinema"));
    try{
      movieRepository.delete(movieToDelete);
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

  public MovieResponse update(MovieRequest movieRequest){
    Movie movieToEdit = movieRepository.findById(movieRequest.getTitle())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with this title isn't shown in this cinema"));
movieRequest.copyTo(movieToEdit);
    return new MovieResponse(movieRepository.save(movieToEdit));
  }


}
