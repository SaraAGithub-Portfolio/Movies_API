package saras.movies.server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saras.movies.server.exception.EntityNotFoundException;
import saras.movies.server.model.Movie;
import saras.movies.server.services.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService; // immutability as final, encapsulation restrict access to Movie Service from outside the controller

    @Autowired //makes class easier to test and more compliant with dependency injection
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(),HttpStatus.OK);
    }

    //updated controller method to check if result is empty / present
    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable String imdbId) {
        Optional<Movie> movieOptional = movieService.singleMovie(imdbId);
        if (!movieOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(movieOptional.get());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Movie>> getMoviesByGenresAndReleaseDate(
            @RequestParam String genres,
            @RequestParam String releaseDate) {
        List<Movie> movies = movieService.findMoviesByGenresAndReleaseDate(genres, releaseDate);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //updated to allow users to find movie by title
    @GetMapping("/search")
    public List<Movie> searchMoviesByTitle(@RequestParam String title) {
        return movieService.findMoviesByTitle(title);
    }
//@RequestParam for query paramaters & @PathVariables for extract values from path URI
    // returns '400' with exception message when receiving invalid argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
