package saras.movies.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Review {

    private ObjectId id;

    private String body;

    // updated to allow user to review all reviews via imdbId
    private String imdbId;
    private String title;
    private String username;

    public Review(String body) {
        this.body = body;
    }
}
