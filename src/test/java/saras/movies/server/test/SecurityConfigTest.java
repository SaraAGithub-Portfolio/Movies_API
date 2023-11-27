package saras.movies.server.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import saras.movies.server.config.SecurityConfig;
import saras.movies.server.controller.AuthController;
import saras.movies.server.model.User;
import saras.movies.server.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class) // Explicitly import SecurityConfig
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void testUnauthenticatedUserRegistration() throws Exception {
        User testUser = new User(null, "testUser", "testPassword", "test@example.com");
        String userJson = new ObjectMapper().writeValueAsString(testUser);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated());
    }


    @Test
    public void testAccessDeniedForUnauthenticatedUser() throws Exception {
        mockMvc.perform(post("/api/v1/protectedEndpoint"))
                .andExpect(status().isUnauthorized()); // Expecting HTTP 401 Unauthorized
    }
}
