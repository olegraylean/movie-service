package com.rayn_microservices.movie_service.model;

import java.math.BigDecimal;

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
//@Entity
//@Table
public class Movie {
  private BigDecimal id;
  private String title;
  private String genres;
  private String cast;
  private String writers;
  private String directors;
  private String releaseDate;
  private String budget;
  private String production;
  private String companies;
}
