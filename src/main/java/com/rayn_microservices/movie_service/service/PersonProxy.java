package com.rayn_microservices.movie_service.service;

import com.rayn_microservices.movie_service.model.Director;
import com.rayn_microservices.movie_service.model.dto.PersonDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="person-service"/*, url="localhost:8000"*/)
public interface PersonProxy {

  @GetMapping("persons/director/movie/{movie}")
  public Director getDirector(@PathVariable String movie);

  @GetMapping("persons/search/{name}/id/{id}")
  public PersonDto getPersonByTypeAndNameAndId(@PathVariable String name, @PathVariable UUID id);

  @GetMapping("persons/search/{type}/{name}/id/{id}")
  public PersonDto getPersonByTypeAndNameAndId(@PathVariable String type, @PathVariable String name, @PathVariable UUID id);
}
