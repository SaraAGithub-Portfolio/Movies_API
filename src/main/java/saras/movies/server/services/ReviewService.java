package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
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

     Review savedReview = saveReview(review);


     mongoTemplate.update(Movie.class)
             .matching(Criteria.where("imdbId").is(imdbId))
             .apply(new Update().push("reviewIds").value(savedReview))
             .first();

             return savedReview;

 }
 public Review saveReview(Review review) {
     return reviewRepository.save(review);// save or update review in the database
 }

 // adding method to allow user to find all reviews using ImdbID
    public List<Review> getReviewsByImdbId(String imdbId) {
     return reviewRepository.findByImdbId(imdbId);
    }
//allow users to find reviews by title of movie
    public List<Review> getReviewsByTitle(String title) {
     return reviewRepository.findByTitle(title);
    }
}
