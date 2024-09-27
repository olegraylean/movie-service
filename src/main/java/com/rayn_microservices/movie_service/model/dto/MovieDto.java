package com.rayn_microservices.movie_service.model.dto;

import com.rayn_microservices.movie_service.model.Genre;
import com.rayn_microservices.movie_service.model.Movie;
import com.rayn_microservices.movie_service.model.ProductionCompany;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
  private long id;
  private String title;
  private Set<Genre> genres;
  private Set<UUID> actorIds;
  private String actorFullName;
  private List<UUID> writerIds;
  private Set<UUID> directorIds;
  private Date releaseDate;
  private String budget;
  private Set<ProductionCompany> productionCompanies;
  private String environment;

  public MovieDto(Movie movie, String actorFirstName, String actorLastName, String environment) {
    this.id = movie.getId();
    this.title = movie.getTitle();
    this.genres = movie.getGenres();
    this.actorIds = movie.getActorIds();
    this.actorFullName = actorFirstName + " " + actorLastName;
    this.writerIds = movie.getWriterIds();
    this.directorIds = movie.getDirectorIds();
    this.releaseDate = movie.getReleaseDate();
    this.budget = movie.getBudget();
    this.productionCompanies = movie.getProductionCompanies();
    this.environment = environment;
  }
}
