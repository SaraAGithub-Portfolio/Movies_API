package saras.movies.server.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import saras.movies.server.exception.ServiceException;
import saras.movies.server.model.User;
import saras.movies.server.repository.UserRepository;
import saras.movies.server.services.UserService;

import java.util.Optional;

import static org.mockito.Mockito.*; // mocks the UserRepository
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class) //integrates Mockito, enabling mock creation and injection
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService; //crates instance of the class 'UserService'

    @Test
    public void testRegisterUserSuccess() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("encodedPassword", registeredUser.getPassword());
        verify(userRepository).save(any(User.class));
    }


    @Test
    public void testRegisterUserWithEmptyUsername() {
        // Create a user with an empty username
        User user = new User();
        user.setUsername(""); // Setting the username to empty
        user.setPassword("password");

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user);
        });

        // Check the exception message
        assertEquals("Username and password cannot be null or empty", exception.getMessage());
    }
    @Test
    public void whenRegisterUser_withDuplicateUsername_thenThrowException() {
        // Given
        String username = "existingUser";
        String email = "user@example.com";
        User existingUser = new User(null, username, "password", email);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        User newUser = new User(null, username, "newPassword", "newuser@example.com");

        // When & Then
        assertThrows(ServiceException.class, () -> userService.registerUser(newUser));
    }

    @Test
    public void whenRegisterUser_withDuplicateEmail_thenThrowException() {
        // Given
        String username = "newUser";
        String email = "existing@example.com";
        User existingUser = new User(null, "existingUser", "password", email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        User newUser = new User(null, username, "newPassword", email);

        // When & Then
        assertThrows(ServiceException.class, () -> userService.registerUser(newUser));
    }


}
