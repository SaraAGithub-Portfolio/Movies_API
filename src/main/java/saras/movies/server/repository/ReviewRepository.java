package saras.movies.server.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import saras.movies.server.model.Review;

public interface ReviewRepository extends MongoRepository<Review,ObjectId> {


}
