package com.rayn_microservices.movie_service.service;

import com.rayn_microservices.movie_service.model.Director;
import com.rayn_microservices.movie_service.model.dto.ActorDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="person-service"/*, url="localhost:8000"*/)
public interface PersonProxy {

  @GetMapping("persons/director/movie/{movie}")
  public Director getDirector(@PathVariable String movie);

  @GetMapping("persons/search/actor/{name}/id/{id}")
  public ActorDto getActorsByNameAndId(@PathVariable String name, @PathVariable UUID id);
}
