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
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Test to check if a 200 status is returned when a valid username is provided
    @Test
    public void whenGetUserByValidUsername_thenStatus200() throws Exception {
        String validUsername = "dogLover12345"; // Replace with an actual username that exists in your database
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + validUsername))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(validUsername));
    }
    // Test to check if a 404 status is returned for a non-existing username
    @Test
    public void whenGetUserByInvalidUsername_thenStatus404() throws Exception {
        String invalidUsername = "nonExistingUsername";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + invalidUsername))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}