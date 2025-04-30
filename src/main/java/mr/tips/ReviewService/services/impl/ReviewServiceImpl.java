package mr.tips.ReviewService.services.impl;

import lombok.RequiredArgsConstructor;
import mr.tips.ReviewService.dto.CreateReviewRequest;
import mr.tips.ReviewService.dto.ReviewResponse;
import mr.tips.ReviewService.dto.UserImage;
import mr.tips.ReviewService.models.Review;
import mr.tips.ReviewService.repositories.ReviewRepository;
import mr.tips.ReviewService.services.ReviewService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public void createReview(CreateReviewRequest request) {
        Long userId = null;
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof UserImage) {
            userId = ((UserImage) principal).getId();
        }

        Review review = Review.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .userId(userId).build();
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewResponse> getReviewHistory() {
        Long userId = ((UserImage) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getId();
        return reviewRepository.findAllByUserId(userId).orElse(List.of()).stream()
                .map(r -> new ReviewResponse(r.getRating(), r.getComment()))
                .toList();
    }
}
