package mr.tips.ReviewService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReviewRequest {
    @Min(1) @Max(5)
    private int rating;
    @NotBlank
    private String comment;
}
