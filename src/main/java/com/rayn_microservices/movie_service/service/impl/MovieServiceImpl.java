package com.rayn_microservices.movie_service.service.impl;

import com.rayn_microservices.movie_service.model.Movie;
import com.rayn_microservices.movie_service.repository.MovieRepository;
import com.rayn_microservices.movie_service.service.MovieService;

import org.springframework.stereotype.Service;

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
}
