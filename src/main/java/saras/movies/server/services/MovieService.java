package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saras.movies.server.model.Movie;
import saras.movies.server.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovie(String imdbId) {
        if (imdbId == null || imdbId.trim().isEmpty()) {
            throw new IllegalArgumentException("IMDb ID cannot be null or empty");
        }
        return movieRepository.findMovieByImdbId(imdbId);
    }
// Updated to allow user to search via genre and release date
    public List<Movie> findMoviesByGenresAndReleaseDate(String genres, String releaseDate) {
        return movieRepository.findByGenresAndReleaseDate(genres, releaseDate);
    }
    // Updated to allow user to search by Movie Name

}