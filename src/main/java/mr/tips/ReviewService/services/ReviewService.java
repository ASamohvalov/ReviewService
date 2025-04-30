package mr.tips.ReviewService.services;

import mr.tips.ReviewService.dto.CreateReviewRequest;
import mr.tips.ReviewService.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void createReview(CreateReviewRequest request);
    List<ReviewResponse> getReviewHistory();
}
