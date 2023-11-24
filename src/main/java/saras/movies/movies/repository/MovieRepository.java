package saras.movies.movies.repository;

import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import saras.movies.movies.model.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

   Optional<Movie> findMovieByImdbId(String imdbId);

   //query filters movies where genre field has specified name & release year matches specified year
   @Query("{ 'genres': { $in: [?0] }, 'releaseDate': { $regex: ?1, $options: 'i' } }")
   List<Movie> findByGenresAndReleaseDate(String genres, String releaseDate);



}
