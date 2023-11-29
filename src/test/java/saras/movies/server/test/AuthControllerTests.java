package saras.movies.server.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import saras.movies.server.model.User;
import saras.movies.server.repository.UserRepository;


import java.time.Instant;

@SpringBootTest
@AutoConfigureMockMvc

//integration testing how controller interacts with the application & service layer
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    // Test registration with valid user data
    @Test
    public void whenRegisterUser_withValidData_thenStatus201() throws Exception {
        // Generate a unique username
        String uniqueUsername = "newUser" + Instant.now().toEpochMilli();

        // Create a new user object
        User user = new User();
        user.setUsername(uniqueUsername);
        user.setPassword("password123");
        user.setEmail(uniqueUsername + "@example.com");

        // Convert the user object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        // Perform a mock HTTP POST request to register the user
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isCreated()); // Assert that the status is 201
    }


    // Test registration with invalid user data (e.g., missing password)
    @Test
    public void whenRegisterUser_withInvalidData_thenStatus400() throws Exception {
        User user = new User(null, "newUser", null, "newuser@example.com");
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
