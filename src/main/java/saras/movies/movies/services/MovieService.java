package saras.movies.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saras.movies.movies.model.Movie;
import saras.movies.movies.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
   private MovieRepository movieRepository;
    public List<Movie> allMovies() {
        List<Movie> movies = movieRepository.findAll();
     System.out.println(movies.toString());
    return movies;
    }
    public Optional<Movie> singleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
