package mr.tips.ReviewService.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.tips.ReviewService.dto.CreateReviewRequest;
import mr.tips.ReviewService.dto.ReviewResponse;
import mr.tips.ReviewService.services.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create_review")
    public void createReview(@Valid @RequestBody CreateReviewRequest request) {
        reviewService.createReview(request);
    }

    @GetMapping("/get_review_history")
    public List<ReviewResponse> getReviewHistory() {
        return reviewService.getReviewHistory();
    }
}
