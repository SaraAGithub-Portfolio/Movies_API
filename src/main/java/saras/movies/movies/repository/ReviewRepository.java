package saras.movies.movies.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import saras.movies.movies.model.Review;

public interface ReviewRepository extends MongoRepository<Review,ObjectId> {


}
