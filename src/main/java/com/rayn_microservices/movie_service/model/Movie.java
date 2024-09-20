package com.rayn_microservices.movie_service.model;

import java.math.BigDecimal;
import java.util.Date;

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
public class Movie {
  private long id;
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
