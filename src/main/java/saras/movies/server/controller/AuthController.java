package saras.movies.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import saras.movies.server.model.User;
import saras.movies.server.services.UserService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.authentication.BadCredentialsException;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

//ResponseEntity is a wrapper for HTTP responses. Allows you to build a response with a status code, headers and body
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) { //tells Spring to bind the body of the HTTP request to the user Object
        User registeredUser = userService.registerUser(user); //User is return type; returns a User object
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            // Generate token or session as per your authentication setup
//            return ResponseEntity.ok("User logged in successfully");
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
//        }
//    }

    // error handling more localized
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
