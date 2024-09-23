package com.rayn_microservices.movie_service.repository;

import com.rayn_microservices.movie_service.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
