package saras.movies.server.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")

@NotNull(message = "Password cannot be null")
@Size(min = 6, message = "Password must be at least 6 characters long")
public class User {
    @Id
    private String id;

    private String username;
    private String password;
    private String email;

}
