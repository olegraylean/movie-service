package com.rayn_microservices.movie_service.repository;

import com.rayn_microservices.movie_service.model.Genre;
import com.rayn_microservices.movie_service.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  List<Movie> findMoviesByGenresAndReleaseDate(Set<Genre> genre, LocalDate releaseDate);

  //Unsupported tuple comparison combination
  //because Hibernate does not support direct comparison of a list of values (like actorIds) against a tuple in JPQL queries.
  @Query("SELECT m FROM Movie m JOIN m.actorIds a WHERE a IN :actorIds")
  List<Movie> findMoviesByActorIds(@Param("actorIds") Set<UUID> actorId);
}
