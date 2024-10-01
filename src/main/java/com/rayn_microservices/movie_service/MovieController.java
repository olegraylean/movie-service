package com.rayn_microservices.movie_service;

import com.rayn_microservices.movie_service.model.Movie;
import com.rayn_microservices.movie_service.model.dto.PersonDto;
import com.rayn_microservices.movie_service.model.dto.MovieDto;
import com.rayn_microservices.movie_service.repository.MovieRepository;
import com.rayn_microservices.movie_service.service.MovieService;
import com.rayn_microservices.movie_service.service.PersonProxy;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
    return ResponseEntity.ok(movieService.getMovieById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    return ResponseEntity.ok(movieService.updateMovie(id, movie));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<List<Movie>> searchMovies(
      @RequestParam(required = false) String genre,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate) {
    return ResponseEntity.ok(movieService.searchMovies(genre, releaseDate));
  }

  @GetMapping("/actor/{actorId}")
  public ResponseEntity<List<Movie>> getMoviesByActor(@PathVariable UUID actorId) {
    return ResponseEntity.ok(movieService.getMoviesByActor(actorId));
  }

  @GetMapping("/writer/{writerId}")
  public ResponseEntity<List<Movie>> getMoviesByWriter(@PathVariable Long writerId) {
    return ResponseEntity.ok(movieService.getMoviesByWriter(writerId));
  }

  @GetMapping("/director/{directorId}")
  public ResponseEntity<List<Movie>> getMoviesByDirector(@PathVariable Long directorId) {
    return ResponseEntity.ok(movieService.getMoviesByDirector(directorId));
  }

  @GetMapping("/company/{companyId}")
  public ResponseEntity<List<Movie>> getMoviesByProductionCompany(@PathVariable Long companyId) {
    return ResponseEntity.ok(movieService.getMoviesByProductionCompany(companyId));
  }

  @GetMapping("movie/search/person/{type}/name/{name}/id/{id}")
  public ResponseEntity<List<MovieDto>> getMovieByActorNameAndId(@PathVariable("type") String type,
                                                                 @PathVariable("name") String name,
                                                                 @PathVariable("id") UUID id) {
//    ActorDto actorDto = personProxy.getActorsByNameAndId(name, id);
    PersonDto actorDto = personProxy.getPersonByTypeAndNameAndId(type, name, id);
    List<Movie> movies = movieService.getMoviesByActor(id);
    List<MovieDto> movieDto =
        movies.stream().map(movie -> new MovieDto(movie, actorDto.getFirstName(), actorDto.getLastName(), actorDto.getEnvironment())).toList();
    return ResponseEntity.ok(movieDto);
  }
}
