package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import saras.movies.server.exception.ServiceException;
import saras.movies.server.model.User;
import saras.movies.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be null or empty");
        }

        // Check for duplicate username or email
        Optional<User> existingUserWithUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserWithUsername.isPresent()) {
            throw new ServiceException("Username already taken");
        }

        Optional<User> existingUserWithEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserWithEmail.isPresent()) {
            throw new ServiceException("Email already in use");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ServiceException("Error registering user", e);
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // MongoRepository has built-in functionalities for these

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
