package com.rayn_microservices.movie_service.service;

import com.rayn_microservices.movie_service.model.Director;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="person-service", url="localhost:8000")
public interface PersonProxy {
  @GetMapping("persons/director/movie/{movie}")
  public Director getDirector(@PathVariable String movie);
}
