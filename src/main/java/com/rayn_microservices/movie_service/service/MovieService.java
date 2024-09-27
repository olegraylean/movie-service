package com.rayn_microservices.movie_service.service;

import com.rayn_microservices.movie_service.model.Movie;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MovieService {
  Movie saveMovie(Movie movie);
  List<Movie> getMoviesByWriter(Long writerId);

  List<Movie> getMoviesByDirector(Long directorId);

  List<Movie> getMoviesByProductionCompany(Long companyId);

  Movie getMovieById(Long id);

  Movie updateMovie(Long id, Movie movie);

  void deleteMovie(Long id);

  List<Movie> searchMovies(String genre, LocalDate releaseDate);

  List<Movie> getMoviesByActor(UUID actorId);
}
