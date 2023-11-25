package saras.movies.server.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import saras.movies.server.model.Review;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review,ObjectId> {

    //allowing user to find reviews by IMDb ID
    List<Review> findByImdbId(String imdbId);
    List<Review>findByTitle(String title);


}
