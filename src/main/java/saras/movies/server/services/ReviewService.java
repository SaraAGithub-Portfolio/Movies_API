package saras.movies.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
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
 public Review createReview(String body, String imdbId, String title) {
     Review review = new Review();
     review.setBody(body);
     review.setImdbId(imdbId);
     review.setTitle(title);

     reviewRepository.insert(review);


     mongoTemplate.update(Movie.class)
             .matching(Criteria.where("imdbId").is(imdbId))
             .apply(new Update().push("reviewIds").value(review))
             .first();

             return review;

 }

 // adding method to allow user to find all reviews using ImdbID
    public List<Review> getReviewsByImdbId(String imdbId) {
     return reviewRepository.findByImdbId(imdbId);
    }

    public List<Review> getReviewsByTitle(String title) {
     return reviewRepository.findByTitle(title);
    }
}
