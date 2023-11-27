package saras.movies.server.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUnauthenticatedUserRegistration() throws Exception {
        mockMvc.perform(post("/api/v1/auth/register"))
                .andExpect(status().isCreated());// expecting HTTP 201 created
    }

    @Test
    public void testAccessDeniedForUnauthenticatedUser() throws Exception {
        mockMvc.perform(post("/api/v1/protectedEndpoint"))
                .andExpect(status().isUnauthorized()); // Expecting HTTP 401 Unauthorized
    }
}
