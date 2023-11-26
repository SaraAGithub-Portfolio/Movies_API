package saras.movies.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/api/v1/movies/**").permitAll() // Unauthenticated GET requests for movies
                        .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**").permitAll() // Unauthenticated GET requests for reviews
                        .requestMatchers("/api/v1/auth/register").permitAll() // Unauthenticated requests for user registration
                        .requestMatchers(HttpMethod.POST, "/api/v1/reviews/**").authenticated() // Only authenticated POST requests for reviews
                        .anyRequest().authenticated() // All other requests need authentication
                )
                .httpBasic(); // Use HTTP Basic Authentication

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
