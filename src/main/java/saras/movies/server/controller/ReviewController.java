package saras.movies.server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import saras.movies.server.model.Review;
import saras.movies.server.services.ReviewService;

import java.util.Map;
import java.util.List;
@RestController // Spring MVC controller where every method returns a domain object
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //updated post request to include username of currently authenticated user
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Review> postReview(@RequestBody Review review) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Set the username in the review
        review.setUsername(username);

        // Save the review
        Review savedReview = reviewService.createReview(review.getBody(),review.getImdbId(), review.getTitle());
        return ResponseEntity.ok(savedReview);
    }
    //adding endpoint to find all reviews based on ImdbId
    @GetMapping("movie/{imdbId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable String imdbId) { //@PathVariable extracts a value from the URI feature of Spring MVC pass to controllers
        List<Review> reviews = reviewService.getReviewsByImdbId(imdbId);
        if(reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }
    //adding endpoint to find all reviews based on title

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getReviewsByTitle(@PathVariable String title) {
        List<Review> reviews = reviewService.getReviewsByTitle(title);
        return reviews.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No reviews found for the movie titled '" + title + "'.") :
                ResponseEntity.ok(reviews);
    }
///api/v1/reviews/movie/{imdbId}, {imdbId} is a path variable. It's a placeholder that will be replaced with the actual value in the request URL

}
