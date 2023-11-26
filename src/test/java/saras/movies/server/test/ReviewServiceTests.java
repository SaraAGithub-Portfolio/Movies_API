package saras.movies.server.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


import saras.movies.server.repository.ReviewRepository;
import saras.movies.server.services.ReviewService;
import saras.movies.server.model.Review;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReviewServiceTests {
    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Test
    public void whenGetReviewsByImdbId_thenCorrectListsReturned() {
        String imdbId = "tt8760708";
        List<Review> mockReviews = Arrays.asList(new Review(), new Review());
        Mockito.when(reviewRepository.findByImdbId(imdbId)).thenReturn(mockReviews);

        List<Review> reviews = reviewService.getReviewsByImdbId(imdbId);
        assertEquals(mockReviews.size(), reviews.size());
        Mockito.verify(reviewRepository).findByImdbId(imdbId);
    }
    @Test
    public void whenGetReviewsByTitle_thenCorrectListsReturned() {
        String title = "M3GAN";
        List<Review> mockReviews = Arrays.asList(new Review(), new Review());
        Mockito.when(reviewRepository.findByTitle(title)).thenReturn(mockReviews);

        List<Review> reviews = reviewService.getReviewsByTitle(title);
        assertEquals(2, reviews.size(), "The number of reviews should match the mock");
        assertEquals(mockReviews, reviews, "The returned reviews should match the mock data");
        Mockito.verify(reviewRepository).findByTitle(title);
    }

}
