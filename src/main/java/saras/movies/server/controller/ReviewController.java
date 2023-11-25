package saras.movies.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saras.movies.server.model.Review;
import saras.movies.server.services.ReviewService;

import java.util.Map;
import java.util.List;
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("body"), payload.get("imdbId"), payload.get("title")), HttpStatus.CREATED);
    }

    //adding endpoint to find all reviews based on ImdbId
    @GetMapping("movie/{imdbId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable String imdbId) {
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


}
