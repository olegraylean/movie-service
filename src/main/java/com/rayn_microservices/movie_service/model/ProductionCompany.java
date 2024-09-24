package com.rayn_microservices.movie_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductionCompany {
  @Id
  private int id;
  private String name;

  @ManyToMany(mappedBy = "productionCompanies")
  @JsonBackReference
  private Set<Movie> movies;
}
