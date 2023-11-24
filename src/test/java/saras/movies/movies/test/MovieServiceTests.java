package saras.movies.movies.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import saras.movies.movies.repository.MovieRepository;
import saras.movies.movies.services.MovieService;
import saras.movies.movies.model.Movie;
import java.util.Arrays;
import java.util.List;

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

}
