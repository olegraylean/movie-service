package com.rayn_microservices.movie_service.model.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class PersonDto {
  private UUID id;
  private String type;
  private String firstName;
  private String lastName;
  private String environment;
}
