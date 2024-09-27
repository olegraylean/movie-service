package com.rayn_microservices.movie_service.service.impl;

import com.rayn_microservices.movie_service.model.Genre;
import com.rayn_microservices.movie_service.model.Movie;
import com.rayn_microservices.movie_service.repository.MovieRepository;
import com.rayn_microservices.movie_service.service.MovieService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public Movie saveMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  @Override
  public List<Movie> getMoviesByWriter(Long writerId) {
    return List.of();
  }

  @Override
  public List<Movie> getMoviesByDirector(Long directorId) {
    return List.of();
  }

  @Override
  public List<Movie> getMoviesByProductionCompany(Long companyId) {
    return List.of();
  }

  @Override
  public Movie getMovieById(Long id) {
    return null;
  }

  @Override
  public Movie updateMovie(Long id, Movie movie) {
    return null;
  }

  @Override
  public void deleteMovie(Long id) {

  }

  @Override
  public List<Movie> searchMovies(String genreName, LocalDate releaseDate) {
    Set<Genre> genreSet = new HashSet<>();
    Genre genre = Genre.builder().name(genreName).build();
    genreSet.add(genre);
    return movieRepository.findMoviesByGenresAndReleaseDate(genreSet, releaseDate);
  }

  @Override
  public List<Movie> getMoviesByActor(UUID actorId) {
    Set<UUID> actorIds = new HashSet<>();
    actorIds.add(actorId);
    return movieRepository.findMoviesByActorIds(actorIds);
  }
}
