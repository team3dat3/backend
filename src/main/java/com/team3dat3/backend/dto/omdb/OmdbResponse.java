package com.team3dat3.backend.dto.omdb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OmdbResponse {

String title;

String director;

String actors;

//becomes prodYear
int year;

//becomes ageLimit
String rated;
String genre;

//becomes description
String description;

String runtime;

String poster;

String imdbId;



/*
{"Title":"Avatar","Year":"2009","Rated":"PG-13","Released":"18 Dec 2009",
"Runtime":"162 min","Genre":"Action, Adventure, Fantasy",
"Director":"James Cameron","Writer":"James Cameron",
"Actors":"Sam Worthington, Zoe Saldana, Sigourney Weaver",
"Plot":"A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.","Language":"English, Spanish","Country":"United States",
"Awards":"Won 3 Oscars. 89 wins & 131 nominations total","Poster":"https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg",
"Ratings":[{"Source":"Internet Movie Database","Value":"7.9/10"},{"Source":"Rotten Tomatoes","Value":"82%"},
{"Source":"Metacritic","Value":"83/100"}],
"Metascore":"83","imdbRating":"7.9","imdbVotes":"1,326,849","imdbID":"tt0499549","Type":"movie","DVD":"22 Apr 2010",
"BoxOffice":"$785,221,649","Production":"N/A","Website":"N/A","Response":"True"}*/
}
