package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saras.movies.server.exception.EntityNotFoundException;
import saras.movies.server.model.Movie;
import saras.movies.server.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service // service layer, manages lifecycle of @Service classes
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
    if (genres == null || genres.trim().isEmpty() || releaseDate == null || releaseDate.trim().isEmpty()) {
        throw new IllegalArgumentException("Genres or release date cannot be null or empty");
    }
    List<Movie> movies = movieRepository.findByGenresAndReleaseDate(genres, releaseDate);
    if (movies.isEmpty()) {
        throw new EntityNotFoundException("No movies found for the specified genres and release date");
    }
    return movies;
}

    public List<Movie> findMoviesByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            throw new EntityNotFoundException("No movies found with the given title");
        }
        return movies;
    }


}
