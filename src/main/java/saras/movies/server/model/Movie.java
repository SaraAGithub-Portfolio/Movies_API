package saras.movies.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
@Document(collection = "movies") // domain object
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id // identifier of the document in MongoDB collection
    private ObjectId id;

    private String imdbId;

    private String title;

    private String releaseDate;

    private String trailerLink;

    private String poster;

    private List<String> genres;

    private List<String> backdrops;

    @DocumentReference // advanced feature of Spring Data MongoDB used to handle relationships between documents
    private List<Review> reviewIds;
}
