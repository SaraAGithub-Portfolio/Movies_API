package saras.movies.server.test;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc

public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    //test to check the status of '200' is returned when making a GET request to movies endpoint
    @Test
    public void whenGetAllMovies_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    //test to check the status of '200' is returned when retrieving a single movie with a valid ID
    @Test
    public void whenGetSingleMovie_withValidImdbId_thenStatus200() throws Exception {
        String validImdbId = "tt3915174";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/" + validImdbId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbId").value(validImdbId));
    }

    //test to check invalid ImdbId provided
    @Test
    public void whenGetSingleMovie_withInvalidImdbId_thenStatus404() throws Exception {
        String invalidImdbId = "invalid_imdbId";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/" + invalidImdbId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // test to check if params are handled correctly
   @Test
   public void whenGetMoviesByGenreAndReleaseDate_thenStatus200() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/filter")
                       .param("genres", "Action")
                       .param("releaseDate", "2022"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
       // Additional assertions to check the contents of the response
   }

   // test to check error handling
   @Test
   public void whenGetMoviesWithInvalidParameters_thenBadRequest() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/filter")
                       .param("genre", "")
                       .param("releaseYear", "2022"))
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }



}
