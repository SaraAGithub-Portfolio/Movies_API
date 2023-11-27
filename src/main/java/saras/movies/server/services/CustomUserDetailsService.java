package saras.movies.server.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import saras.movies.server.model.User;
import saras.movies.server.services.UserService;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

 private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override // if user is found, returns UserDetails object w/ username & password
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // no roles or authorities, provide an empty list
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() // Empty list of authorities ; if there were roles, this list would contain granted authorities
        );
    }
}
