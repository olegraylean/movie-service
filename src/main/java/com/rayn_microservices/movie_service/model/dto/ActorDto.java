package com.rayn_microservices.movie_service.model.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class ActorDto {
  private UUID id;
  private String firstName;
  private String lastName;
  private String environment;
}
