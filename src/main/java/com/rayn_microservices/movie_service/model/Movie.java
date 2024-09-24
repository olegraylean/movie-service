package com.rayn_microservices.movie_service.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Movie {

  @Id
  @GeneratedValue
  private long id;

  @Column(name = "title")
  private String title;

  @ManyToMany
  @JoinTable(
      name = "movie_to_genre", schema = "public",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
//  @JsonManagedReference
  private Set<Genre> genres;

  @ElementCollection
  @CollectionTable(name = "movie_actors", joinColumns = @JoinColumn(name = "movie_id"))
  @Column(name = "actor_id")
  private Set<UUID> actorIds = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "movie_writers", joinColumns = @JoinColumn(name = "movie_id"))
  @Column(name = "writer_id")
  private List<UUID> writerIds = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "movie_directors", joinColumns = @JoinColumn(name = "movie_id"))
  @Column(name = "director_id")
  private Set<UUID> directorIds = new HashSet<>();

  @Column(name = "release_date")
  private Date releaseDate;

  @Column(name = "budget")
  private String budget;

  @ManyToMany
  @JoinTable(
      name = "movie_to_production_companies", schema = "public",
      joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "production_company_id", referencedColumnName = "id")
  )
  private Set<ProductionCompany> productionCompanies;

}
