package saras.movies.movies.test;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import saras.movies.movies.repository.MovieRepository;
import saras.movies.movies.services.MovieService;
import saras.movies.movies.model.Movie;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



//aim to cover the key functionalities provided by the service
//testing behavior
@SpringBootTest

public class MovieServiceTests {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    //test if method returns correct list of movies
    @Test
    public void whenAllMovies_thenCorrectListReturned() {
        List<Movie> mockMovies = Arrays.asList(new Movie(), new Movie());
        Mockito.when(movieRepository.findAll()).thenReturn(mockMovies);

        List<Movie> movies = movieService.allMovies();
        assertEquals(mockMovies.size(), movies.size());
        Mockito.verify(movieRepository).findAll(); // Verifies findAll is called once
    }
    //test if method returns correct movie for a given IMDb ID
    @Test
    public void whenSingleMovie_withValidImbId_thenCorrectMovieReturned() {
        String imdbId = "tt8760708";
        Movie mockMovie = new Movie();
        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(mockMovie));

        Optional<Movie> movie = movieService.singleMovie(imdbId);
        assertTrue(movie.isPresent());
        assertEquals(mockMovie, movie.get());
    }
    //test the behavior when an invalid imdb id is provided
    @Test
    public void whenSingleMovie_withInvalidImdbId_thenEmptyOptionalReturned() {
        String invalidImdbId = "invalid_id";
        Mockito.when(movieRepository.findMovieByImdbId(invalidImdbId)).thenReturn(Optional.empty());

        Optional<Movie> movie = movieService.singleMovie(invalidImdbId);
        assertFalse(movie.isPresent());
    }

}
