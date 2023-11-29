package saras.movies.server.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import saras.movies.server.controller.ReviewController;
//import saras.movies.server.model.Review;
import saras.movies.server.services.ReviewService;
import java.util.Collections;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.containsString;
//import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(ReviewController.class) // placed at class level; configures MockMvc instance
public class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private ObjectMapper objectMapper = new ObjectMapper();
//test to check if '404' status is given if movie review not found
    @Test
    @WithMockUser
    public void whenGetReviewsByNonexistentTitle_thenNotFound() throws Exception {
        String title = "Nonexistent Movie";
        Mockito.when(reviewService.getReviewsByTitle(title)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/title/" + title))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(containsString("No reviews found for the movie titled")));
    }

//    @Test
//    @WithMockUser(username = "testUser")
//    public void whenPostReview_withAuthenticatedUser_thenStatus200() throws Exception {
//
//        Review review = new Review();
//        review.setBody("Sample review body");
//        review.setImdbId("sampleImdbId");
//        review.setTitle("Sample Title");
//        review.setUsername("testUser");
//
//
//        String reviewJson = objectMapper.writeValueAsString(review);
//
//        Mockito.when(reviewService.createReview(review.getBody(), review.getImdbId(), review.getTitle())).thenReturn(review);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
//                        .contentType("application/json")
//                        .content(reviewJson))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }


}
