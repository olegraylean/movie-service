package com.rayn_microservices.movie_service.service;

import com.rayn_microservices.movie_service.model.Movie;

public interface MovieService {
  Movie saveMovie(Movie movie);
}
