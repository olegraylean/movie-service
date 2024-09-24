package com.rayn_microservices.movie_service;

import com.rayn_microservices.movie_service.model.Director;
import com.rayn_microservices.movie_service.model.Movie;
import com.rayn_microservices.movie_service.repository.MovieRepository;
import com.rayn_microservices.movie_service.service.MovieService;
import com.rayn_microservices.movie_service.service.PersonProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieController {

  private final PersonProxy personProxy;
  private final MovieRepository movieRepository;
  private final MovieService movieService;

  public MovieController(PersonProxy personProxy, MovieRepository movieRepository, MovieService movieService) {
    this.personProxy = personProxy;
    this.movieRepository = movieRepository;
    this.movieService = movieService;
  }

  //  @GetMapping("movie/genre/{genre}/year/{year}")
  @GetMapping("movie-feign/genre/{genre}/year/{year}")
  public Movie getMovieByYearAndGenre(@PathVariable("genre") String genre, @PathVariable("year") String year) {
    HashMap<String, String> uriVariables = new HashMap<>();
    uriVariables.put("movie", "Fear");

//    ResponseEntity<Director> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/persons/director/movie/{movie}", Director.class, uriVariables);
//    String director = responseEntity.getBody() != null ? responseEntity.getBody().getFullName() : null;
    String director = personProxy.getDirector("Fear").getFullName();

//    return Movie.builder().id(1l).genres(genre).directors(director).title("Fear").releaseDate(year).build();
    return Movie.builder().title("Fear").build();
  }

//  GET /movies?genre={genre}&year={year}
//  GET /movies/actor/{id}
//  GET /movies/director/{id}

  public Movie saveUser(Movie movie) {
    return movieRepository.save(movie);
  }

  @GetMapping("/movie")
  public ResponseEntity<List<Movie>> getAllMovies() {
    List<Movie> movies = movieRepository.findAll();
    return ResponseEntity.ok(movies);
  }


  @PostMapping("/movie")
  public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
    Movie newMovie = movieService.saveMovie(movie);
    return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
  }
}
