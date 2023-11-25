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
    public void whenGetSingleMovie_withValidID_thenStatus200() throws Exception {
        String validId = "tt8760708";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies" + validId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbId").value(validId));
    }

}
