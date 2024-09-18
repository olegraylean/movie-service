package com.rayn_microservices.movie_service;

import com.rayn_microservices.movie_service.model.Movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MovieController {

  @GetMapping("movie/genre/{genre}/year/{year}")
  public Movie getMovieByYearAndGenre(@PathVariable("genre") String genre, @PathVariable("year") int year) {
    Movie movie = Movie.builder().id(BigDecimal.valueOf(1)).genres("Horror").title("Fear").build();
    return movie;
  }
}
