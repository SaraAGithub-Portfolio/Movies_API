package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder; // obtain authentication details
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import saras.movies.server.exception.EntityNotFoundException;
import saras.movies.server.model.Review;
import saras.movies.server.model.Movie;
import saras.movies.server.repository.ReviewRepository;

import java.util.List;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    //updated createReview to include the username of the user who's creating the review
    public Review createReview(String body, String imdbId, String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Review review = new Review();
        review.setBody(body);
        review.setImdbId(imdbId);
        review.setTitle(title);
        review.setUsername(username);

        Review savedReview = reviewRepository.save(review);


        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(savedReview))
                .first();

        return savedReview;

    }

    public List<Review> getReviewsByImdbId(String imdbId) {
        List<Review> reviews = reviewRepository.findByImdbId(imdbId);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("No reviews found for IMDb ID: " + imdbId);
        }
        return reviews;
    }

    public List<Review> getReviewsByTitle(String title) {
        List<Review> reviews = reviewRepository.findByTitle(title);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("No reviews found for the movie titled: " + title);
        }
        return reviews;
    }
}

