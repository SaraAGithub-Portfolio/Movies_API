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

@SpringBootTest
@AutoConfigureMockMvc

//integration testing how controller interacts with the application & service layer
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    // Test registration with valid user data
    @Test
    public void whenRegisterUser_withValidData_thenStatus200() throws Exception {
        User user = new User(null, "newUser", "password123", "newuser@example.com");
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Test registration with invalid user data (e.g., missing password)
    @Test
    public void whenRegisterUser_withInvalidData_thenStatus400() throws Exception {
        User user = new User(null, "newUser", null, "newuser@example.com");
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
