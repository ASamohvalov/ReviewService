package mr.tips.ReviewService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponse {
    private int rating;
    private String comment;
}
